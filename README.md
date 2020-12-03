# MysqlToEs
Synchronize mysql data to ES by listening to Binlog



通过监听Mysql binlog，将数据库的变动信息发送到Kafka，消费Kafka消息，写入到elasticsearch

**使用步骤**

- 1、git clone https://github.com/DadySu/MysqlToEs.git
- 2、依次启动Zookeeper、Kafka、Elasticsearch
- 3、修改application.yml，将Mysql、Kafka、Elasticsearch相关的配置信息修改成自己的
- 4、依次启动MysqlBinlogToKafka模块、KafkaToEs模块
- 5、在Navicat、sqlyog执行写操作
- 6、在Kibana中验证是否写入成功
