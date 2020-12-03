package com.dadysu.es.binlog;

import com.alibaba.fastjson.JSON;
import com.dadysu.es.bean.BinlogModel;
import com.dadysu.es.config.BinlogListenerConfiguration;
import com.dadysu.es.constant.MethodConstant;
import com.dadysu.es.kafka.KafkaSender;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

@Component
@Slf4j
public class BinlogListener implements ApplicationRunner {

    @Resource
    private BinlogListenerConfiguration configuration;
    @Resource
    private KafkaSender kafkaSender;

    /**
     * 监听binlog指定表，写入kafka
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("进入binlog监听");
        // 获取监听数据表数组
        List<String> databaseList = Arrays.asList(configuration.getTableNames().split(","));
        HashMap<Long, String> tableMap = new HashMap();
        // 创建binlog监听客户端
        BinaryLogClient client = new BinaryLogClient(configuration.getMysqlHost(), configuration.getMysqlPort(),
                configuration.getMysqlUser(), configuration.getMysqlPassword());
        client.setServerId(configuration.getServerId());
        // 监听binlog
        client.registerEventListener((event -> {
            // binlog事件
            EventData data = event.getData();
            if (data != null) {
                if (data instanceof TableMapEventData) {
                    TableMapEventData tableMapEventData = (TableMapEventData) data;
                    tableMap.put(tableMapEventData.getTableId(), tableMapEventData.getDatabase() + "." + tableMapEventData.getTable());
                }
                // update
                if (data instanceof UpdateRowsEventData) {
                    UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) data;
                    String databaseAndTableName = tableMap.get(updateRowsEventData.getTableId());
                    if (databaseAndTableName != null && databaseList.contains(databaseAndTableName)) {
                        for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                            String msg = JSON.toJSONString(new BinlogModel(databaseAndTableName, MethodConstant.UPDATE, row.getValue()));
                            kafkaSender.send(configuration.getTopic(), UUID.randomUUID().toString(), msg);
                        }
                    }
                }
                // insert
                else if (data instanceof WriteRowsEventData) {
                    WriteRowsEventData writeRowsEventData = (WriteRowsEventData) data;
                    String databaseAndTableName = tableMap.get(writeRowsEventData.getTableId());
                    if (databaseAndTableName != null && databaseList.contains(databaseAndTableName)) {
                        for (Serializable[] row : writeRowsEventData.getRows()) {
                            String msg = JSON.toJSONString(new BinlogModel(databaseAndTableName, MethodConstant.INSERT, row));
                            kafkaSender.send(configuration.getTopic(), UUID.randomUUID().toString(), msg);
                        }
                    }
                }
                // delete
                else if (data instanceof DeleteRowsEventData) {
                    DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) data;
                    String databaseAndTableName = tableMap.get(deleteRowsEventData.getTableId());
                    if (databaseAndTableName != null && databaseList.contains(databaseAndTableName)) {
                        for (Serializable[] row : deleteRowsEventData.getRows()) {
                            String msg = JSON.toJSONString(new BinlogModel(databaseAndTableName, MethodConstant.DELETE, row));
                            kafkaSender.send(configuration.getTopic(), UUID.randomUUID().toString(), msg);
                        }
                    }
                }
            }
        }));
        client.connect();

    }
}
