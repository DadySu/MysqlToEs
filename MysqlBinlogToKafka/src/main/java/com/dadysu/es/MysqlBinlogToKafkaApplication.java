package com.dadysu.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlBinlogToKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlBinlogToKafkaApplication.class, args);
    }

}
