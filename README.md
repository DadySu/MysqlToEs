# MysqlToEs
Synchronize mysql data to ES by listening to Binlog



通过监听Mysql binlog，将数据库的变动信息发送到Kafka，消费Kafka消息，写入到elasticsearch

**使用步骤**

- 1、git clone https://github.com/DadySu/MysqlToEs.git
- 2、将db目录下的sql.db复制到自己的数据库中执行
- 3、依次启动Zookeeper、Kafka、Elasticsearch
- 4、修改application.yml，将Mysql、Kafka、Elasticsearch相关的配置信息修改成自己的
- 5、依次启动MysqlBinlogToKafka模块、KafkaToEs模块
- 6、在Navicat、sqlyog执行写操作
- 7、在Kibana中验证是否写入成功

**演示效果**
- 1、![mysql中执行](pic\insert.png)
- 2、![kibana中查询](pic\kibana1.png)
