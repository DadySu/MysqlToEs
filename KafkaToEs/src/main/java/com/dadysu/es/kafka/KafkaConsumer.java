package com.dadysu.es.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dadysu.es.bean.BinlogModel;
import com.dadysu.es.bean.BinlogTestModel;
import com.dadysu.es.config.BinlogListenerConfiguration;
import com.dadysu.es.constant.KafkaConstant;
import com.dadysu.es.constant.MethodConstant;
import com.dadysu.es.repository.RenterInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;

@Component
@Slf4j
public class KafkaConsumer {

    @Resource
    private BinlogListenerConfiguration configuration;
    @Resource
    private RenterInfoRepository renterInfoRepository;


    @KafkaListener(topics = KafkaConstant.BINLOG_TOPIC, groupId = KafkaConstant.BINLOG_GROUPID,
            errorHandler = "binglogErrorHandler")
    public void binglogListener(ConsumerRecord consumerRecord, Acknowledgment ack) {
        log.info("消费开始 consumerRecord:{}", JSON.toJSONString(consumerRecord));
        Object value = consumerRecord.value();
        BinlogModel binlogModel = JSONObject.parseObject(value.toString(), BinlogModel.class);
        log.info("binglog info binlogModel:{}", JSON.toJSONString(binlogModel));

        BinlogTestModel binlogTestModel = binglogToModel(binlogModel);
        modelToEs(binlogTestModel, binlogModel.getMethod());
        ack.acknowledge();
    }

    private BinlogTestModel binglogToModel(BinlogModel binlogModel) {
        String tableName = binlogModel.getTableName();
        String method = binlogModel.getMethod();
        String row = Arrays.toString(binlogModel.getRow());
        Serializable[] array = binlogModel.getRow();
        if (configuration.getTableNames().equals(tableName)) {
            BinlogTestModel binlogTestModel = new BinlogTestModel();

            // id
            binlogTestModel.setId(Integer.parseInt(array[0].toString()));
            if (array[1] != null) {
                binlogTestModel.setRenterType(Integer.parseInt(array[1].toString()));
            }
            if (array[2] != null) {
                binlogTestModel.setMobile(array[2].toString());
            }
            if (array[3] != null) {
                binlogTestModel.setPwd(array[3].toString());
            }
            if (array[4] != null) {
                binlogTestModel.setNickName(array[4].toString());
            }
            if (array[5] != null) {
                binlogTestModel.setFullName(array[5].toString());
            }
            if (array[6] != null) {
                binlogTestModel.setGender(Integer.parseInt(array[6].toString()));
            }
            if (array[7] != null) {
                //renterInfoModel.setBirthdate(DateUtil.parseDate(array[7].toString()));
            }
            if (array[8] != null) {
                binlogTestModel.setHeadBig(array[8].toString());
            }
            if (array[9] != null) {
                binlogTestModel.setHeadSmall(array[9].toString());
            }
            if (array[10] != null) {
                binlogTestModel.setIdCard(array[10].toString());
            }
            if (array[11] != null) {
                binlogTestModel.setIdCardImgHead(array[11].toString());
            }
            if (array[12] != null) {
                binlogTestModel.setIdCardImgBack(array[12].toString());
            }
            if (array[13] != null) {
                binlogTestModel.setBlackState(Integer.parseInt(array[13].toString()));
            }
            if (array[14] != null) {
                binlogTestModel.setRemark(array[14].toString());
            }
            if (array[15] != null) {
                binlogTestModel.setAuditState(Integer.parseInt(array[15].toString()));
            }
            if (array[16] != null) {
                binlogTestModel.setAuditOpinion(array[18].toString());
            }

            return binlogTestModel;
        }

        return null;
    }


    private void modelToEs(BinlogTestModel binlogTestModel, String method) {
        if (binlogTestModel == null) {
            return;
        }
        if (MethodConstant.INSERT.equals(method) || MethodConstant.UPDATE.equals(method)) {
            renterInfoRepository.save(binlogTestModel);
        } else if (MethodConstant.DELETE.equals(method)) {
            renterInfoRepository.delete(binlogTestModel);
        }

    }

}
