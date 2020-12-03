package com.dadysu.es.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class KafkaSender {


    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String id, Object data) {
        log.info("消费消息,topic:{},id:{},data:{}", topic, id, data);
        kafkaTemplate.send(topic, id, data);
    }
}
