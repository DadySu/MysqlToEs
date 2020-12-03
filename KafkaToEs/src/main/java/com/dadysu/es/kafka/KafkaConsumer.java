package com.dadysu.es.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dadysu.es.bean.BinlogModel;
import com.dadysu.es.bean.RenterInfoModel;
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

        RenterInfoModel renterInfoModel = binglogToModel(binlogModel);
        modelToEs(renterInfoModel, binlogModel.getMethod());
        ack.acknowledge();
    }

    private RenterInfoModel binglogToModel(BinlogModel binlogModel) {
        String tableName = binlogModel.getTableName();
        String method = binlogModel.getMethod();
        String row = Arrays.toString(binlogModel.getRow());
        Serializable[] array = binlogModel.getRow();
        if (configuration.getTableNames().equals(tableName)) {
            RenterInfoModel renterInfoModel = new RenterInfoModel();

            // id
            renterInfoModel.setId(Integer.parseInt(array[0].toString()));
            if (array[1] != null) {
                renterInfoModel.setRenterType(Integer.parseInt(array[1].toString()));
            }
            if (array[2] != null) {
                renterInfoModel.setMobile(array[2].toString());
            }
            if (array[3] != null) {
                renterInfoModel.setPwd(array[3].toString());
            }
            if (array[4] != null) {
                renterInfoModel.setNickName(array[4].toString());
            }
            if (array[5] != null) {
                renterInfoModel.setFullName(array[5].toString());
            }
            if (array[6] != null) {
                renterInfoModel.setGender(Integer.parseInt(array[6].toString()));
            }
            if (array[7] != null) {
                //renterInfoModel.setBirthdate(DateUtil.parseDate(array[7].toString()));
            }
            if (array[8] != null) {
                renterInfoModel.setHeadBig(array[8].toString());
            }
            if (array[9] != null) {
                renterInfoModel.setHeadSmall(array[9].toString());
            }
            if (array[10] != null) {
                renterInfoModel.setIdCard(array[10].toString());
            }
            if (array[11] != null) {
                renterInfoModel.setIdCardImgHead(array[11].toString());
            }
            if (array[12] != null) {
                renterInfoModel.setIdCardImgBack(array[12].toString());
            }
            if (array[13] != null) {
                renterInfoModel.setBlackState(Integer.parseInt(array[13].toString()));
            }
            if (array[14] != null) {
                renterInfoModel.setRemark(array[14].toString());
            }
            if (array[15] != null) {
                renterInfoModel.setAuditState(Integer.parseInt(array[15].toString()));
            }
            if (array[16] != null) {
                //renterInfoModel.setAuditApplyTime(DateUtil.parseDate(array[16].toString()));
            }
            if (array[17] != null) {
                //renterInfoModel.setAuditTime(DateUtil.parseDate(array[17].toString()));
            }
            if (array[18] != null) {
                renterInfoModel.setAuditOpinion(array[18].toString());
            }

            return renterInfoModel;
        }

        return null;
    }


    private void modelToEs(RenterInfoModel renterInfoModel, String method) {
        if (renterInfoModel == null) {
            return;
        }
        if (MethodConstant.INSERT.equals(method) || MethodConstant.UPDATE.equals(method)) {
            renterInfoRepository.save(renterInfoModel);
        } else if (MethodConstant.DELETE.equals(method)) {
            renterInfoRepository.delete(renterInfoModel);
        }

    }

}
