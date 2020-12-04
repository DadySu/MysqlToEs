# 创建数据库
CREATE DATABASE es-test;

# 创建数据库表
CREATE TABLE `binlog_test` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `renter_type` int(11) DEFAULT NULL COMMENT '用户类型：1-普通用户 2-VIP用户',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `pwd` varchar(64) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `full_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别1：男  2：女',
  `birthdate` datetime DEFAULT NULL COMMENT '出生日期',
  `head_big` varchar(255) DEFAULT NULL COMMENT '大头像',
  `head_small` varchar(255) DEFAULT NULL COMMENT '小头像',
  `id_card` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `id_card_img_head` varchar(255) DEFAULT NULL COMMENT '身份证照片（正面）URL',
  `id_card_img_back` varchar(255) DEFAULT NULL COMMENT '身份证照片（反面）URL',
  `black_state` int(11) DEFAULT NULL COMMENT '是否加入了黑名单 0-否 1-是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `audit_state` int(11) DEFAULT NULL COMMENT '审核状态：0-未提交 1-已提交未审核 2-审核通过 3-审核不通过',
  `audit_apply_time` datetime DEFAULT NULL COMMENT '审核申请提交时间',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_opinion` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='测试表';


INSERT INTO `es-test`.`binlog_test`( `renter_type`, `mobile`, `pwd`, `nick_name`, `full_name`, `gender`, `birthdate`, `head_big`, `head_small`, `id_card`, `id_card_img_head`, `id_card_img_back`, `black_state`, `remark`, `audit_state`, `audit_opinion`, `create_time`, `update_time`) VALUES ( 1, '13888888888', '123456', '昵称', '全称', 1, '2020-02-01 00:00:00', NULL, NULL, '21312313132131', '', NULL, 11, 'remark', 00000000001, 'audit_option', NULL, NULL);
