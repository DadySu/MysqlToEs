package com.dadysu.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "binlogtokafka")
public class BinlogListenerConfiguration {

    private String tableNames;
    private String mysqlHost;
    private int mysqlPort;
    private String mysqlUser;
    private String mysqlPassword;
    /**
     * mysql配置文件中设置的binglog相关的serverId
     */
    private Long serverId;
    private String topic;
}
