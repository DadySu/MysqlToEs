# MysqlToEs
Synchronize mysql data to ES by listening to Binlog



通过监听Mysql binlog，将数据库的变动信息发送到Kafka，消费Kafka消息，写入到elasticsearch