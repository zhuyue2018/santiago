/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云0917
Source Server Version : 50727
Source Host           : 106.54.105.3:3306
Source Database       : pay

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-10-24 17:20:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `version` char(5) NOT NULL COMMENT '版本：为“1.0.0”格式',
  `remark` varchar(50) DEFAULT NULL COMMENT '说明',
  `account_no` varchar(20) NOT NULL COMMENT '账户号',
  `balance` decimal(20,6) NOT NULL COMMENT '余额',
  `freeze_balance` decimal(20,6) NOT NULL COMMENT '冻结金额',
  `security_money` decimal(20,6) NOT NULL COMMENT '保证金',
  `status` char(1) NOT NULL COMMENT '账户状态：0：未激活，1：正常， 2：冻结， 3：关闭',
  `total_income` decimal(20,6) NOT NULL COMMENT '总收入',
  `total_expend` decimal(20,6) NOT NULL COMMENT '总支出',
  `today_income` decimal(20,6) NOT NULL COMMENT '今日收入',
  `today_expend` decimal(20,6) NOT NULL COMMENT '今日支出',
  `account_type` char(1) NOT NULL COMMENT '账户类型',
  `sett_amount` decimal(20,6) NOT NULL COMMENT '可结算金额',
  `merchant_no` varchar(19) NOT NULL COMMENT '商户号',
  `delete` tinyint(1) unsigned zerofill NOT NULL COMMENT '逻辑删除：0为未删除，1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资金账户表';

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for `account_check_batch`
-- ----------------------------
DROP TABLE IF EXISTS `account_check_batch`;
CREATE TABLE `account_check_batch` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL COMMENT '最后修改时间',
  `version` char(5) NOT NULL,
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `status` char(1) NOT NULL DEFAULT '' COMMENT '对账状态：0：未对账，1：已发送， 2：成功， 3：失败',
  `remark` varchar(50) DEFAULT NULL,
  `batch_no` varchar(20) NOT NULL COMMENT '对账日期，对的是哪一天的账',
  `bill_date` date NOT NULL,
  `bill_type` varchar(10) DEFAULT NULL,
  `handle_status` char(1) DEFAULT NULL,
  `bank_type` varchar(10) DEFAULT NULL COMMENT '对应recon_interface的code',
  `mistake_count` int(8) DEFAULT NULL,
  `unhandle_mistake_count` int(8) DEFAULT NULL,
  `trade_count` int(8) DEFAULT NULL,
  `bank_trade_count` int(8) DEFAULT NULL,
  `trade_amount` decimal(20,6) DEFAULT NULL,
  `bank_trade_amount` decimal(20,6) DEFAULT NULL,
  `refund_amount` decimal(20,6) DEFAULT NULL,
  `bank_refund_amount` decimal(20,6) DEFAULT NULL,
  `bank_fee` decimal(20,6) DEFAULT NULL,
  `org_check_file_path` varchar(50) DEFAULT NULL,
  `release_check_file_path` varchar(50) DEFAULT NULL,
  `release_status` char(1) DEFAULT NULL,
  `check_fail_msg` varchar(50) DEFAULT NULL,
  `bank_err_msg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='对账批次表 account_check_batch';

-- ----------------------------
-- Records of account_check_batch
-- ----------------------------

-- ----------------------------
-- Table structure for `account_check_mistake`
-- ----------------------------
DROP TABLE IF EXISTS `account_check_mistake`;
CREATE TABLE `account_check_mistake` (
  `id` bigint(19) NOT NULL,
  `version` char(5) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `editor` varchar(10) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(10) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `status` char(1) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `account_check_batch_no` varchar(20) NOT NULL,
  `bill_date` date NOT NULL,
  `bank_type` varchar(5) NOT NULL,
  `order_time` datetime DEFAULT NULL,
  `merchant_name` varchar(20) DEFAULT NULL,
  `merchant_no` varchar(20) DEFAULT NULL,
  `order_no` varchar(20) DEFAULT NULL,
  `trade_time` datetime DEFAULT NULL,
  `trx_no` varchar(20) DEFAULT NULL,
  `order_amount` decimal(20,6) DEFAULT NULL,
  `refund_amount` decimal(20,6) DEFAULT NULL,
  `trade_status` varchar(30) DEFAULT NULL,
  `fee` decimal(20,6) DEFAULT NULL,
  `bank_trade_time` datetime DEFAULT NULL,
  `bank_order_no` varchar(20) DEFAULT NULL,
  `bank_trx_no` varchar(20) DEFAULT NULL,
  `bank_trade_status` char(1) DEFAULT NULL,
  `bank_amount` decimal(20,6) DEFAULT NULL,
  `bank_refund_amount` decimal(20,6) DEFAULT NULL,
  `bank_fee` decimal(20,6) DEFAULT NULL,
  `err_type` varchar(10) NOT NULL,
  `handle_status` char(1) NOT NULL,
  `handle_value` varchar(200) DEFAULT NULL,
  `handle_remark` varchar(200) DEFAULT NULL,
  `operator_name` varchar(20) DEFAULT NULL,
  `operator_account_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='对账差错表 account_check_mistake';

-- ----------------------------
-- Records of account_check_mistake
-- ----------------------------

-- ----------------------------
-- Table structure for `account_check_mistake_scratch_pool`
-- ----------------------------
DROP TABLE IF EXISTS `account_check_mistake_scratch_pool`;
CREATE TABLE `account_check_mistake_scratch_pool` (
  `id` varchar(50) NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `gmt_create` datetime NOT NULL,
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `product_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(30) NOT NULL COMMENT '商户订单号',
  `trx_no` char(20) NOT NULL COMMENT '支付流水号',
  `bank_order_no` char(20) DEFAULT NULL COMMENT '银行订单号',
  `bank_trx_no` varchar(30) DEFAULT NULL COMMENT '银行流水号',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `plat_income` decimal(20,6) DEFAULT NULL COMMENT '平台收入',
  `fee_rate` decimal(20,6) DEFAULT NULL COMMENT '费率',
  `plat_cost` decimal(20,6) DEFAULT NULL COMMENT '平台成本',
  `plat_profit` decimal(20,6) DEFAULT NULL COMMENT '平台利润',
  `status` varchar(30) DEFAULT NULL COMMENT '状态(参考枚举:paymentrecordstatusenum)',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `pay_success_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` smallint(6) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `batch_no` varchar(50) DEFAULT NULL,
  `bill_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='差错暂存池';

-- ----------------------------
-- Records of account_check_mistake_scratch_pool
-- ----------------------------

-- ----------------------------
-- Table structure for `account_history`
-- ----------------------------
DROP TABLE IF EXISTS `account_history`;
CREATE TABLE `account_history` (
  `id` varchar(50) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount` decimal(20,6) NOT NULL,
  `balance` decimal(20,6) NOT NULL,
  `fund_direction` varchar(36) NOT NULL,
  `is_allow_sett` varchar(36) NOT NULL,
  `is_complete_sett` varchar(36) NOT NULL,
  `request_no` varchar(36) NOT NULL,
  `bank_trx_no` varchar(30) DEFAULT NULL,
  `trx_type` varchar(36) NOT NULL,
  `risk_day` int(11) DEFAULT NULL,
  `user_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资金账户流水表';

-- ----------------------------
-- Records of account_history
-- ----------------------------

-- ----------------------------
-- Table structure for `merchant_bank_account`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_bank_account`;
CREATE TABLE `merchant_bank_account` (
  `id` varchar(50) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(36) NOT NULL,
  `user_no` varchar(50) NOT NULL,
  `bank_name` varchar(200) NOT NULL,
  `bank_code` varchar(50) NOT NULL,
  `bank_account_name` varchar(100) NOT NULL,
  `bank_account_no` varchar(36) NOT NULL,
  `card_type` varchar(36) NOT NULL,
  `card_no` varchar(36) NOT NULL,
  `mobile_no` varchar(50) NOT NULL,
  `is_default` varchar(36) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `areas` varchar(20) DEFAULT NULL,
  `street` varchar(300) DEFAULT NULL,
  `bank_account_type` varchar(36) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户银行账户表';

-- ----------------------------
-- Records of merchant_bank_account
-- ----------------------------

-- ----------------------------
-- Table structure for `merchant_info`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE `merchant_info` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `status` char(1) NOT NULL,
  `merchant_no` varchar(20) DEFAULT NULL,
  `merchant_name` varchar(20) DEFAULT NULL,
  `account_no` varchar(20) NOT NULL,
  `mobile` char(11) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `pay_pwd` varchar(30) DEFAULT '123456' COMMENT '支付密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ak_key_2` (`account_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='该表用来存放用户的基本信息';

-- ----------------------------
-- Records of merchant_info
-- ----------------------------

-- ----------------------------
-- Table structure for `merchant_pay_config`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_pay_config`;
CREATE TABLE `merchant_pay_config` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `version` char(5) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `status` char(1) NOT NULL,
  `audit_status` char(1) DEFAULT NULL,
  `is_auto_sett` char(1) DEFAULT '0',
  `merchant_no` varchar(20) DEFAULT NULL,
  `risk_day` int(11) DEFAULT NULL,
  `fund_into_type` varchar(10) DEFAULT NULL,
  `security_rating` varchar(10) DEFAULT 'MD5' COMMENT '安全等级 none：不验证；ip：验证ip；sign：验证sign和ip',
  `merchant_server_ip` varchar(30) DEFAULT NULL COMMENT '商户服务器IP，可配置多个，用|分开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='支付设置表';

-- ----------------------------
-- Records of merchant_pay_config
-- ----------------------------
INSERT INTO `merchant_pay_config` VALUES ('3', '2019-10-13 00:36:53', '2019-10-13 00:36:53', '1.0.0', null, '1', null, null, '001', null, null, 'ip', '0.0.0.0');

-- ----------------------------
-- Table structure for `merchant_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_pay_info`;
CREATE TABLE `merchant_pay_info` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `version` char(5) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `status` char(1) NOT NULL,
  `app_id` varchar(20) NOT NULL,
  `app_sectet` varchar(20) DEFAULT NULL,
  `merchant_id` varchar(20) DEFAULT NULL,
  `app_type` varchar(10) DEFAULT NULL,
  `merchant_no` varchar(20) DEFAULT NULL,
  `merchant_name` varchar(20) DEFAULT NULL,
  `partner_key` varchar(50) DEFAULT NULL,
  `offline_app_id` varchar(20) DEFAULT NULL,
  `rsa_private_key` varchar(100) DEFAULT NULL,
  `rsa_public_key` varchar(100) DEFAULT NULL,
  `md5_key` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='该表用来存放用户开通的第三方支付信息';

-- ----------------------------
-- Records of merchant_pay_info
-- ----------------------------
INSERT INTO `merchant_pay_info` VALUES ('1', '2019-10-17 22:22:03', '2019-10-17 22:22:06', '1.0.0', '1', '1', '1', '1', '1', '1', '001', '001', '1', '1', '1', '1', '123456');

-- ----------------------------
-- Table structure for `merchant_pay_product`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_pay_product`;
CREATE TABLE `merchant_pay_product` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_no` varchar(20) NOT NULL,
  `pay_product_code` varchar(20) NOT NULL,
  `fee_rate` decimal(20,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant_pay_product
-- ----------------------------
INSERT INTO `merchant_pay_product` VALUES ('1', '001', '001', '0');
INSERT INTO `merchant_pay_product` VALUES ('2', '001', '002', '0');

-- ----------------------------
-- Table structure for `notify_record`
-- ----------------------------
DROP TABLE IF EXISTS `notify_record`;
CREATE TABLE `notify_record` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` char(5) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `editor` varchar(10) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(10) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `notify_times` int(2) NOT NULL,
  `limit_notify_times` int(2) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0:未通知 1:成功 2:失败',
  `url` varchar(100) NOT NULL,
  `notify_type` char(1) DEFAULT NULL COMMENT '通知类型',
  `merchant_no` varchar(20) NOT NULL,
  `merchant_order_no` varchar(20) NOT NULL,
  `order_status` char(1) DEFAULT NULL COMMENT '1:支付成功 2:支付异常',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`merchant_order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='通知记录表 notify_record';

-- ----------------------------
-- Records of notify_record
-- ----------------------------
INSERT INTO `notify_record` VALUES ('2', '1.0.0', '2019-10-13 09:30:51', null, null, '2019-10-13 09:31:09', '1', '10', '1', '', null, '001', '201910130907', '1');
INSERT INTO `notify_record` VALUES ('3', '1.0.0', '2019-10-21 13:51:00', 'job', 'test', '2019-10-21 13:59:31', '2', '10', '0', 'test', '1', '001', '123456', '1');

-- ----------------------------
-- Table structure for `notify_record_log`
-- ----------------------------
DROP TABLE IF EXISTS `notify_record_log`;
CREATE TABLE `notify_record_log` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` char(5) NOT NULL,
  `editor` varchar(10) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(10) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL,
  `notify_id` bigint(19) NOT NULL,
  `request` varchar(2000) NOT NULL,
  `response` varchar(2000) NOT NULL,
  `merchant_no` varchar(20) NOT NULL,
  `merchant_order_no` varchar(20) NOT NULL COMMENT '商户订单号',
  `http_status` char(3) NOT NULL COMMENT 'http状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='通知记录日志表 notify_record_log';

-- ----------------------------
-- Records of notify_record_log
-- ----------------------------

-- ----------------------------
-- Table structure for `pay_product`
-- ----------------------------
DROP TABLE IF EXISTS `pay_product`;
CREATE TABLE `pay_product` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT 'version',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `pay_product_code` varchar(50) DEFAULT NULL COMMENT '支付产品编号',
  `pay_way_code` varchar(50) NOT NULL COMMENT '支付方式编号',
  `pay_way_name` varchar(100) NOT NULL COMMENT '支付方式名称',
  `pay_type_code` varchar(50) NOT NULL COMMENT '支付类型编号',
  `pay_type_name` varchar(100) NOT NULL COMMENT '支付类型名称',
  `status` varchar(36) NOT NULL COMMENT '状态(100:正常状态,101非正常状态)',
  `sorts` int(11) DEFAULT '1000' COMMENT '排序(倒序排序,默认值1000)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='支付方式';

-- ----------------------------
-- Records of pay_product
-- ----------------------------
INSERT INTO `pay_product` VALUES ('1', '1', '2019-10-24 14:16:31', '2019-10-24 14:16:34', '001', '001', 'weixin', '001', 'scan', '1', '1000');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('admin', 'TPmCcG4ietxBxGnpemkzng==', 'J+i8T8YkVO3mHcib+YeEkA==', '2019-10-22 12:29:02');

-- ----------------------------
-- Table structure for `pms_menu`
-- ----------------------------
DROP TABLE IF EXISTS `pms_menu`;
CREATE TABLE `pms_menu` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL DEFAULT '0' COMMENT '账户状态：0：未激活，1：正常， 2：暂停使用， 3：关闭',
  `remark` varchar(50) DEFAULT NULL,
  `is_leaf` varchar(20) DEFAULT NULL,
  `level` smallint(6) NOT NULL COMMENT '1：一级 2：二级 3：按钮级',
  `parent_id` bigint(19) DEFAULT NULL,
  `target_name` varchar(20) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1073 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of pms_menu
-- ----------------------------
INSERT INTO `pms_menu` VALUES ('1', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '001', '权限管理', '##');
INSERT INTO `pms_menu` VALUES ('2', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '1', 'cdgl', '00101', '菜单管理', '/pms/menu/list');
INSERT INTO `pms_menu` VALUES ('3', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '1', 'qxgl', '00102', '权限管理', '/pms/permission/list');
INSERT INTO `pms_menu` VALUES ('4', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '1', 'jsgl', '00103', '角色管理', '/pms/role/list');
INSERT INTO `pms_menu` VALUES ('5', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '1', 'czygl', '00104', '操作员管理', '/pms/operator/list');
INSERT INTO `pms_menu` VALUES ('10', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '002', '账户管理', '##');
INSERT INTO `pms_menu` VALUES ('12', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '10', 'zhxx', '00201', '账户信息', '/account/list');
INSERT INTO `pms_menu` VALUES ('13', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '10', 'zhlsxx', '00202', '账户历史信息', '/account/historyList');
INSERT INTO `pms_menu` VALUES ('20', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '003', '用户管理', '##');
INSERT INTO `pms_menu` VALUES ('22', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '20', 'yhxx', '00301', '用户信息', '/user/info/list');
INSERT INTO `pms_menu` VALUES ('30', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '004', '支付管理', '##');
INSERT INTO `pms_menu` VALUES ('32', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '30', 'zfcpgl', '00401', '支付产品信息', '/pay/product/list');
INSERT INTO `pms_menu` VALUES ('33', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '30', 'yhzfpz', '00402', '用户支付配置', '/pay/config/list');
INSERT INTO `pms_menu` VALUES ('40', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '005', '交易管理', '##');
INSERT INTO `pms_menu` VALUES ('42', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '40', 'zfddgl', '00501', '支付订单管理', '/trade/order');
INSERT INTO `pms_menu` VALUES ('43', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '40', 'zfjjgl', '00502', '支付记录管理', '/trade/record');
INSERT INTO `pms_menu` VALUES ('50', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '006', '结算管理', '##');
INSERT INTO `pms_menu` VALUES ('52', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '50', 'jsjlgl', '00601', '结算记录管理', '/sett/list');
INSERT INTO `pms_menu` VALUES ('60', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'NO', '1', '0', '#', '007', '对账管理', '##');
INSERT INTO `pms_menu` VALUES ('62', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '60', 'dzcclb', '00701', '对账差错列表', '/reconciliation/list/mistake');
INSERT INTO `pms_menu` VALUES ('63', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '60', 'dzpclb', '00702', '对账批次列表', '/reconciliation/list/checkbatch');
INSERT INTO `pms_menu` VALUES ('64', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '', 'YES', '2', '60', 'dzhcclb', '00703', '对账缓冲池列表', '/reconciliation/list/scratchPool');
INSERT INTO `pms_menu` VALUES ('65', '0', 'roncoo', '2019-10-16 13:39:12', 'admin', '2019-10-16 13:39:20', 'ACTIVE', null, 'NO', '3', '2', 'AA', null, '菜单添加', '/pms/menu/insert');
INSERT INTO `pms_menu` VALUES ('66', '0', 'roncoo', '2019-10-16 13:49:22', 'admin', '2019-10-16 13:49:26', 'ACTIVE', null, 'NO', '3', '42', 'AA', null, '支付订单查询', '/trade/order/page');
INSERT INTO `pms_menu` VALUES ('67', '0', 'roncoo', '2019-10-16 19:59:30', 'admin', '2019-10-16 19:59:36', 'ACTIVE', null, 'NO', '3', '2', 'AA', null, '菜单查询', '/pms/menu/page');
INSERT INTO `pms_menu` VALUES ('1000', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '2', null, '001', '权限管理-菜单-查看', '/pms/menu/view');
INSERT INTO `pms_menu` VALUES ('1001', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '2', null, '001', '权限管理-菜单-添加', '/pms/menu/add');
INSERT INTO `pms_menu` VALUES ('1002', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '2', null, '001', '权限管理-菜单-查看', '/pms/menu/edit');
INSERT INTO `pms_menu` VALUES ('1003', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '2', null, '001', '权限管理-菜单-删除', '/pms/menu/delete');
INSERT INTO `pms_menu` VALUES ('1004', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '3', null, '001', '权限管理-权限-查看', '/pms/permission/view');
INSERT INTO `pms_menu` VALUES ('1005', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '3', null, '001', '权限管理-权限-添加', '/pms/permission/add');
INSERT INTO `pms_menu` VALUES ('1006', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '3', null, '001', '权限管理-权限-修改', '/pms/permission/edit');
INSERT INTO `pms_menu` VALUES ('1007', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '3', null, '001', '权限管理-权限-删除', '/pms/permission/delete');
INSERT INTO `pms_menu` VALUES ('1008', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '4', null, '001', '权限管理-角色-查看', '/pms/role/view');
INSERT INTO `pms_menu` VALUES ('1009', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '4', null, '001', '权限管理-角色-添加', '/pms/role/add');
INSERT INTO `pms_menu` VALUES ('1010', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '4', null, '001', '权限管理-角色-修改', '/pms/role/edit');
INSERT INTO `pms_menu` VALUES ('1011', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '4', null, '001', '权限管理-角色-删除', '/pms/role/delete');
INSERT INTO `pms_menu` VALUES ('1012', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '4', null, '001', '权限管理-角色-分配权限', '/pms/role/assignpermission');
INSERT INTO `pms_menu` VALUES ('1013', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '5', null, '001', '权限管理-操作员-查看', '/pms/operator/view');
INSERT INTO `pms_menu` VALUES ('1014', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '5', null, '001', '权限管理-操作员-添加', '/pms/operator/add');
INSERT INTO `pms_menu` VALUES ('1015', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '5', null, '001', '权限管理-操作员-查看', '/pms/operator/edit');
INSERT INTO `pms_menu` VALUES ('1016', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '5', null, '001', '权限管理-操作员-冻结与解冻', '/pms/operator/changestatus');
INSERT INTO `pms_menu` VALUES ('1017', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '5', null, '001', '权限管理-操作员-重置密码', '/pms/operator/resetpwd');
INSERT INTO `pms_menu` VALUES ('1018', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '12', null, '001', '账户管理-账户-查看', '/account/accountInfo/view');
INSERT INTO `pms_menu` VALUES ('1019', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '12', null, '001', '账户管理-账户-添加', '/account/accountInfo/add');
INSERT INTO `pms_menu` VALUES ('1027', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '12', null, '001', '账户管理-账户-编辑', '/account/accountInfo/edit');
INSERT INTO `pms_menu` VALUES ('1028', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '12', null, '001', '账户管理-账户-删除', '/account/accountInfo/delete');
INSERT INTO `pms_menu` VALUES ('1029', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '13', null, '001', '账户管理-账户历史-查看', '/account/accountHistory/view');
INSERT INTO `pms_menu` VALUES ('1030', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '22', null, '001', '商户管理-商户信息-查看', '/merchant/merchantInfo/view');
INSERT INTO `pms_menu` VALUES ('1031', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '22', null, '001', '商户管理-商户信息-添加', '/merchant/merchantInfo/add');
INSERT INTO `pms_menu` VALUES ('1032', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '22', null, '001', '商户管理-商户信息-查看', '/merchant/merchantInfo/edit');
INSERT INTO `pms_menu` VALUES ('1033', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '22', null, '001', '商户管理-商户信息-删除', '/merchant/merchantInfo/delete');
INSERT INTO `pms_menu` VALUES ('1034', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '32', null, '001', '支付管理-支付产品-查看', '/pay/product/view');
INSERT INTO `pms_menu` VALUES ('1035', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '32', null, '001', '支付管理-支付产品-添加', '/pay/product/add');
INSERT INTO `pms_menu` VALUES ('1036', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '32', null, '001', '支付管理-支付产品-查看', '/pay/product/edit');
INSERT INTO `pms_menu` VALUES ('1037', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '32', null, '001', '支付管理-支付产品-删除', '/pay/product/delete');
INSERT INTO `pms_menu` VALUES ('1038', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付方式-查看', '/pay/way/view');
INSERT INTO `pms_menu` VALUES ('1039', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付方式-添加', '/pay/way/add');
INSERT INTO `pms_menu` VALUES ('1040', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付方式-查看', '/pay/way/edit');
INSERT INTO `pms_menu` VALUES ('1041', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付方式-删除', '/pay/way/delete');
INSERT INTO `pms_menu` VALUES ('1042', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付配置-查看', '/pay/config/view');
INSERT INTO `pms_menu` VALUES ('1043', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付配置-添加', '/pay/config/add');
INSERT INTO `pms_menu` VALUES ('1044', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付配置-查看', '/pay/config/edit');
INSERT INTO `pms_menu` VALUES ('1045', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '33', null, '001', '支付管理-支付配置-删除', '/pay/config/delete');
INSERT INTO `pms_menu` VALUES ('1046', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '42', null, '001', '交易管理-订单-查看', '/trade/order/view');
INSERT INTO `pms_menu` VALUES ('1047', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '42', null, '001', '交易管理-订单-添加', '/trade/order/add');
INSERT INTO `pms_menu` VALUES ('1048', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '42', null, '001', '交易管理-订单-查看', '/trade/order/edit');
INSERT INTO `pms_menu` VALUES ('1049', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '42', null, '001', '交易管理-订单-删除', '/trade/order/delete');
INSERT INTO `pms_menu` VALUES ('1050', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '43', null, '001', '交易管理-记录-查看', '/trade/record/view');
INSERT INTO `pms_menu` VALUES ('1051', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '43', null, '001', '交易管理-记录-添加', '/trade/record/add');
INSERT INTO `pms_menu` VALUES ('1052', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '43', null, '001', '交易管理-记录-查看', '/trade/record/edit');
INSERT INTO `pms_menu` VALUES ('1053', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '43', null, '001', '交易管理-记录-删除', '/trade/record/delete');
INSERT INTO `pms_menu` VALUES ('1054', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '52', null, '001', '结算管理-记录-查看', '/sett/record/view');
INSERT INTO `pms_menu` VALUES ('1055', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '52', null, '001', '结算管理-记录-添加', '/sett/record/add');
INSERT INTO `pms_menu` VALUES ('1056', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '52', null, '001', '结算管理-记录-查看', '/sett/record/edit');
INSERT INTO `pms_menu` VALUES ('1057', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '52', null, '001', '结算管理-记录-删除', '/sett/record/delete');
INSERT INTO `pms_menu` VALUES ('1058', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '62', null, '001', '对账管理-差错-查看', '/recon/mistake/view');
INSERT INTO `pms_menu` VALUES ('1059', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '62', null, '001', '对账管理-差错-添加', '/recon/mistake/add');
INSERT INTO `pms_menu` VALUES ('1060', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '62', null, '001', '对账管理-差错-查看', '/recon/mistake/edit');
INSERT INTO `pms_menu` VALUES ('1061', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '62', null, '001', '对账管理-差错-删除', '/recon/mistake/delete');
INSERT INTO `pms_menu` VALUES ('1062', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '63', null, '001', '对账管理-批次-查看', '/recon/batch/view');
INSERT INTO `pms_menu` VALUES ('1063', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '63', null, '001', '对账管理-批次-添加', '/recon/batch/add');
INSERT INTO `pms_menu` VALUES ('1064', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '63', null, '001', '对账管理-批次-查看', '/recon/batch/edit');
INSERT INTO `pms_menu` VALUES ('1065', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '63', null, '001', '对账管理-批次-删除', '/recon/batch/delete');
INSERT INTO `pms_menu` VALUES ('1066', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '64', null, '001', '对账管理-缓冲池-查看', '/recon/scratchPool/view');
INSERT INTO `pms_menu` VALUES ('1067', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '64', null, '001', '对账管理-缓冲池-添加', '/recon/scratchPool/add');
INSERT INTO `pms_menu` VALUES ('1068', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '64', null, '001', '对账管理-缓冲池-查看', '/recon/scratchPool/edit');
INSERT INTO `pms_menu` VALUES ('1069', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '64', null, '001', '对账管理-缓冲池-删除', '/recon/scratchPool/delete');
INSERT INTO `pms_menu` VALUES ('1070', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', null, 'NO', '3', '2', null, '001', '权限管理-菜单-查询', '/pms/menu/list');
INSERT INTO `pms_menu` VALUES ('1072', '0', null, '2019-10-24 06:43:49', null, '2019-10-24 06:43:49', 'ACTIVE', null, null, '3', '32', null, null, '支付产品查询', '/pay/payProduct/list');

-- ----------------------------
-- Table structure for `pms_operator`
-- ----------------------------
DROP TABLE IF EXISTS `pms_operator`;
CREATE TABLE `pms_operator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `version` bigint(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `remark` varchar(300) DEFAULT NULL,
  `real_name` varchar(50) NOT NULL,
  `mobile_no` varchar(15) NOT NULL,
  `login_name` varchar(50) NOT NULL,
  `login_pwd` varchar(256) NOT NULL,
  `type` varchar(20) NOT NULL,
  `salt` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作员表';

-- ----------------------------
-- Records of pms_operator
-- ----------------------------
INSERT INTO `pms_operator` VALUES ('1', '2016-06-03 11:07:43', '2016-06-03 11:07:43', '0', 'ACTIVE', 'roncoo', 'admin', '超级管理员', '超级管理员', '18620936193', 'admin', '$2a$10$W7sVR75u.4Kpbjg7/lHUx.6iwQXIoeBfpip4zs8P.N0Gia2SeGvGO', 'ADMIN', '8d78869f470951332959580424d4bf4f');
INSERT INTO `pms_operator` VALUES ('2', '2016-06-03 11:07:43', '2016-06-03 11:07:43', '0', 'ACTIVE', 'roncoo', 'guest', '游客', '游客', '18926215592', 'guest', '$2a$10$W7sVR75u.4Kpbjg7/lHUx.6iwQXIoeBfpip4zs8P.N0Gia2SeGvGO', 'USER', '183d9f2f0f2ce760e98427a5603d1c73');

-- ----------------------------
-- Table structure for `pms_operator_log`
-- ----------------------------
DROP TABLE IF EXISTS `pms_operator_log`;
CREATE TABLE `pms_operator_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `operator_id` bigint(20) NOT NULL,
  `operator_name` varchar(50) NOT NULL,
  `operate_type` varchar(50) NOT NULL COMMENT '操作类型（1:增加，2:修改，3:删除，4:查询）',
  `ip` varchar(100) NOT NULL,
  `content` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限_操作员操作日志表';

-- ----------------------------
-- Records of pms_operator_log
-- ----------------------------

-- ----------------------------
-- Table structure for `pms_operator_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `pms_operator_merchant`;
CREATE TABLE `pms_operator_merchant` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `operator_id` bigint(19) NOT NULL,
  `merchant_id` bigint(19) NOT NULL,
  `merchant_no` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_operator_merchant
-- ----------------------------

-- ----------------------------
-- Table structure for `pms_operator_role`
-- ----------------------------
DROP TABLE IF EXISTS `pms_operator_role`;
CREATE TABLE `pms_operator_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `operator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`role_id`,`operator_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作员与角色关联表';

-- ----------------------------
-- Records of pms_operator_role
-- ----------------------------
INSERT INTO `pms_operator_role` VALUES ('1', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '1', '1');
INSERT INTO `pms_operator_role` VALUES ('2', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '1');
INSERT INTO `pms_operator_role` VALUES ('3', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '2');

-- ----------------------------
-- Table structure for `pms_permission`
-- ----------------------------
DROP TABLE IF EXISTS `pms_permission`;
CREATE TABLE `pms_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `permission_name` varchar(100) NOT NULL,
  `permission` varchar(100) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`permission`) USING BTREE,
  KEY `ak_key_3` (`permission_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of pms_permission
-- ----------------------------
INSERT INTO `pms_permission` VALUES ('1', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-菜单-查看', '权限管理-菜单-查看', 'pms:menu:view');
INSERT INTO `pms_permission` VALUES ('2', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-菜单-添加', '权限管理-菜单-添加', 'pms:menu:add');
INSERT INTO `pms_permission` VALUES ('3', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-菜单-查看', '权限管理-菜单-修改', 'pms:menu:edit');
INSERT INTO `pms_permission` VALUES ('4', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-菜单-删除', '权限管理-菜单-删除', 'pms:menu:delete');
INSERT INTO `pms_permission` VALUES ('11', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-权限-查看', '权限管理-权限-查看', 'pms:permission:view');
INSERT INTO `pms_permission` VALUES ('12', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-权限-添加', '权限管理-权限-添加', 'pms:permission:add');
INSERT INTO `pms_permission` VALUES ('13', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-权限-修改', '权限管理-权限-修改', 'pms:permission:edit');
INSERT INTO `pms_permission` VALUES ('14', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-权限-删除', '权限管理-权限-删除', 'pms:permission:delete');
INSERT INTO `pms_permission` VALUES ('21', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-角色-查看', '权限管理-角色-查看', 'pms:role:view');
INSERT INTO `pms_permission` VALUES ('22', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-角色-添加', '权限管理-角色-添加', 'pms:role:add');
INSERT INTO `pms_permission` VALUES ('23', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-角色-修改', '权限管理-角色-修改', 'pms:role:edit');
INSERT INTO `pms_permission` VALUES ('24', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-角色-删除', '权限管理-角色-删除', 'pms:role:delete');
INSERT INTO `pms_permission` VALUES ('25', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-角色-分配权限', '权限管理-角色-分配权限', 'pms:role:assignpermission');
INSERT INTO `pms_permission` VALUES ('31', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-操作员-查看', '权限管理-操作员-查看', 'pms:operator:view');
INSERT INTO `pms_permission` VALUES ('32', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-操作员-添加', '权限管理-操作员-添加', 'pms:operator:add');
INSERT INTO `pms_permission` VALUES ('33', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-操作员-查看', '权限管理-操作员-修改', 'pms:operator:edit');
INSERT INTO `pms_permission` VALUES ('34', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-操作员-冻结与解冻', '权限管理-操作员-冻结与解冻', 'pms:operator:changestatus');
INSERT INTO `pms_permission` VALUES ('35', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '权限管理-操作员-重置密码', '权限管理-操作员-重置密码', 'pms:operator:resetpwd');
INSERT INTO `pms_permission` VALUES ('51', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '账户管理-账户-查看', '账户管理-账户-查看', 'account:accountInfo:view');
INSERT INTO `pms_permission` VALUES ('52', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '账户管理-账户-添加', '账户管理-账户-添加', 'account:accountInfo:add');
INSERT INTO `pms_permission` VALUES ('53', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '账户管理-账户-查看', '账户管理-账户-修改', 'account:accountInfo:edit');
INSERT INTO `pms_permission` VALUES ('54', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '账户管理-账户-删除', '账户管理-账户-删除', 'account:accountInfo:delete');
INSERT INTO `pms_permission` VALUES ('61', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '账户管理-账户历史-查看', '账户管理-账户历史-查看', 'account:accountHistory:view');
INSERT INTO `pms_permission` VALUES ('71', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '用户管理-用户信息-查看', '用户管理-用户信息-查看', 'user:userInfo:view');
INSERT INTO `pms_permission` VALUES ('72', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '用户管理-用户信息-添加', '用户管理-用户信息-添加', 'user:userInfo:add');
INSERT INTO `pms_permission` VALUES ('73', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '用户管理-用户信息-查看', '用户管理-用户信息-修改', 'user:userInfo:edit');
INSERT INTO `pms_permission` VALUES ('74', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '用户管理-用户信息-删除', '用户管理-用户信息-删除', 'user:userInfo:delete');
INSERT INTO `pms_permission` VALUES ('81', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付产品-查看', '支付管理-支付产品-查看', 'pay:product:view');
INSERT INTO `pms_permission` VALUES ('82', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付产品-添加', '支付管理-支付产品-添加', 'pay:product:add');
INSERT INTO `pms_permission` VALUES ('83', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付产品-查看', '支付管理-支付产品-修改', 'pay:product:edit');
INSERT INTO `pms_permission` VALUES ('84', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付产品-删除', '支付管理-支付产品-删除', 'pay:product:delete');
INSERT INTO `pms_permission` VALUES ('85', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付方式-查看', '支付管理-支付方式-查看', 'pay:way:view');
INSERT INTO `pms_permission` VALUES ('86', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付方式-添加', '支付管理-支付方式-添加', 'pay:way:add');
INSERT INTO `pms_permission` VALUES ('87', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付方式-查看', '支付管理-支付方式-修改', 'pay:way:edit');
INSERT INTO `pms_permission` VALUES ('88', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付方式-删除', '支付管理-支付方式-删除', 'pay:way:delete');
INSERT INTO `pms_permission` VALUES ('91', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付配置-查看', '支付管理-支付配置-查看', 'pay:config:view');
INSERT INTO `pms_permission` VALUES ('92', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付配置-添加', '支付管理-支付配置-添加', 'pay:config:add');
INSERT INTO `pms_permission` VALUES ('93', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付配置-查看', '支付管理-支付配置-修改', 'pay:config:edit');
INSERT INTO `pms_permission` VALUES ('94', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '支付管理-支付配置-删除', '支付管理-支付配置-删除', 'pay:config:delete');
INSERT INTO `pms_permission` VALUES ('101', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-订单-查看', '交易管理-订单-查看', 'trade:order:view');
INSERT INTO `pms_permission` VALUES ('102', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-订单-添加', '交易管理-订单-添加', 'trade:order:add');
INSERT INTO `pms_permission` VALUES ('103', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-订单-查看', '交易管理-订单-修改', 'trade:order:edit');
INSERT INTO `pms_permission` VALUES ('104', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-订单-删除', '交易管理-订单-删除', 'trade:order:delete');
INSERT INTO `pms_permission` VALUES ('111', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-记录-查看', '交易管理-记录-查看', 'trade:record:view');
INSERT INTO `pms_permission` VALUES ('112', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-记录-添加', '交易管理-记录-添加', 'trade:record:add');
INSERT INTO `pms_permission` VALUES ('113', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-记录-查看', '交易管理-记录-修改', 'trade:record:edit');
INSERT INTO `pms_permission` VALUES ('114', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '交易管理-记录-删除', '交易管理-记录-删除', 'trade:record:delete');
INSERT INTO `pms_permission` VALUES ('121', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '结算管理-记录-查看', '结算管理-记录-查看', 'sett:record:view');
INSERT INTO `pms_permission` VALUES ('122', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '结算管理-记录-添加', '结算管理-记录-添加', 'sett:record:add');
INSERT INTO `pms_permission` VALUES ('123', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '结算管理-记录-查看', '结算管理-记录-修改', 'sett:record:edit');
INSERT INTO `pms_permission` VALUES ('124', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '结算管理-记录-删除', '结算管理-记录-删除', 'sett:record:delete');
INSERT INTO `pms_permission` VALUES ('131', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-差错-查看', '对账管理-差错-查看', 'recon:mistake:view');
INSERT INTO `pms_permission` VALUES ('132', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-差错-添加', '对账管理-差错-添加', 'recon:mistake:add');
INSERT INTO `pms_permission` VALUES ('133', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-差错-查看', '对账管理-差错-修改', 'recon:mistake:edit');
INSERT INTO `pms_permission` VALUES ('134', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-差错-删除', '对账管理-差错-删除', 'recon:mistake:delete');
INSERT INTO `pms_permission` VALUES ('141', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-批次-查看', '对账管理-批次-查看', 'recon:batch:view');
INSERT INTO `pms_permission` VALUES ('142', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-批次-添加', '对账管理-批次-添加', 'recon:batch:add');
INSERT INTO `pms_permission` VALUES ('143', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-批次-查看', '对账管理-批次-修改', 'recon:batch:edit');
INSERT INTO `pms_permission` VALUES ('144', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-批次-删除', '对账管理-批次-删除', 'recon:batch:delete');
INSERT INTO `pms_permission` VALUES ('151', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-缓冲池-查看', '对账管理-缓冲池-查看', 'recon:scratchPool:view');
INSERT INTO `pms_permission` VALUES ('152', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-缓冲池-添加', '对账管理-缓冲池-添加', 'recon:scratchPool:add');
INSERT INTO `pms_permission` VALUES ('153', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-缓冲池-查看', '对账管理-缓冲池-修改', 'recon:scratchPool:edit');
INSERT INTO `pms_permission` VALUES ('154', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '对账管理-缓冲池-删除', '对账管理-缓冲池-删除', 'recon:scratchPool:delete');

-- ----------------------------
-- Table structure for `pms_role`
-- ----------------------------
DROP TABLE IF EXISTS `pms_role`;
CREATE TABLE `pms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_code` varchar(20) NOT NULL COMMENT '角色类型（1:超级管理员角色，0:普通操作员角色）',
  `role_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of pms_role
-- ----------------------------
INSERT INTO `pms_role` VALUES ('1', '0', 'roncoo', '2016-06-03 11:07:43', 'admin', '2016-06-03 11:07:43', 'ACTIVE', '超级管理员角色', 'admin', '超级管理员角色');
INSERT INTO `pms_role` VALUES ('2', '0', 'roncoo', '2016-06-03 11:07:43', 'guest', '2016-06-03 11:07:43', 'ACTIVE', '游客角色', 'guest', '游客角色');

-- ----------------------------
-- Table structure for `pms_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `pms_role_menu`;
CREATE TABLE `pms_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1130 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限与角色关联表';

-- ----------------------------
-- Records of pms_role_menu
-- ----------------------------
INSERT INTO `pms_role_menu` VALUES ('1000', null, null, null, null, null, null, null, '1', '1');
INSERT INTO `pms_role_menu` VALUES ('1001', null, null, null, null, null, null, null, '1', '2');
INSERT INTO `pms_role_menu` VALUES ('1002', null, null, null, null, null, null, null, '1', '3');
INSERT INTO `pms_role_menu` VALUES ('1003', null, null, null, null, null, null, null, '1', '4');
INSERT INTO `pms_role_menu` VALUES ('1004', null, null, null, null, null, null, null, '1', '5');
INSERT INTO `pms_role_menu` VALUES ('1005', null, null, null, null, null, null, null, '1', '10');
INSERT INTO `pms_role_menu` VALUES ('1006', null, null, null, null, null, null, null, '1', '12');
INSERT INTO `pms_role_menu` VALUES ('1007', null, null, null, null, null, null, null, '1', '13');
INSERT INTO `pms_role_menu` VALUES ('1008', null, null, null, null, null, null, null, '1', '20');
INSERT INTO `pms_role_menu` VALUES ('1009', null, null, null, null, null, null, null, '1', '22');
INSERT INTO `pms_role_menu` VALUES ('1010', null, null, null, null, null, null, null, '1', '30');
INSERT INTO `pms_role_menu` VALUES ('1011', null, null, null, null, null, null, null, '1', '32');
INSERT INTO `pms_role_menu` VALUES ('1012', null, null, null, null, null, null, null, '1', '33');
INSERT INTO `pms_role_menu` VALUES ('1013', null, null, null, null, null, null, null, '1', '40');
INSERT INTO `pms_role_menu` VALUES ('1014', null, null, null, null, null, null, null, '1', '42');
INSERT INTO `pms_role_menu` VALUES ('1015', null, null, null, null, null, null, null, '1', '43');
INSERT INTO `pms_role_menu` VALUES ('1016', null, null, null, null, null, null, null, '1', '50');
INSERT INTO `pms_role_menu` VALUES ('1017', null, null, null, null, null, null, null, '1', '52');
INSERT INTO `pms_role_menu` VALUES ('1018', null, null, null, null, null, null, null, '1', '60');
INSERT INTO `pms_role_menu` VALUES ('1019', null, null, null, null, null, null, null, '1', '62');
INSERT INTO `pms_role_menu` VALUES ('1020', null, null, null, null, null, null, null, '1', '63');
INSERT INTO `pms_role_menu` VALUES ('1021', null, null, null, null, null, null, null, '1', '64');
INSERT INTO `pms_role_menu` VALUES ('1036', null, null, null, null, null, null, null, '2', '10');
INSERT INTO `pms_role_menu` VALUES ('1037', null, null, null, null, null, null, null, '2', '12');
INSERT INTO `pms_role_menu` VALUES ('1038', null, null, null, null, null, null, null, '2', '13');
INSERT INTO `pms_role_menu` VALUES ('1044', null, null, null, null, null, null, null, '2', '40');
INSERT INTO `pms_role_menu` VALUES ('1045', null, null, null, null, null, null, null, '2', '42');
INSERT INTO `pms_role_menu` VALUES ('1046', null, null, null, null, null, null, null, '2', '43');
INSERT INTO `pms_role_menu` VALUES ('1047', null, null, null, null, null, null, null, '2', '50');
INSERT INTO `pms_role_menu` VALUES ('1048', null, null, null, null, null, null, null, '2', '52');
INSERT INTO `pms_role_menu` VALUES ('1049', null, null, null, null, null, null, null, '1', '65');
INSERT INTO `pms_role_menu` VALUES ('1050', null, null, null, null, null, null, null, '1', '66');
INSERT INTO `pms_role_menu` VALUES ('1051', null, null, null, null, null, null, null, '1', '67');
INSERT INTO `pms_role_menu` VALUES ('1062', null, null, null, null, null, null, null, '1', '1000');
INSERT INTO `pms_role_menu` VALUES ('1063', null, null, null, null, null, null, null, '1', '1001');
INSERT INTO `pms_role_menu` VALUES ('1064', null, null, null, null, null, null, null, '1', '1002');
INSERT INTO `pms_role_menu` VALUES ('1065', null, null, null, null, null, null, null, '1', '1003');
INSERT INTO `pms_role_menu` VALUES ('1066', null, null, null, null, null, null, null, '1', '1004');
INSERT INTO `pms_role_menu` VALUES ('1067', null, null, null, null, null, null, null, '1', '1005');
INSERT INTO `pms_role_menu` VALUES ('1068', null, null, null, null, null, null, null, '1', '1006');
INSERT INTO `pms_role_menu` VALUES ('1069', null, null, null, null, null, null, null, '1', '1007');
INSERT INTO `pms_role_menu` VALUES ('1070', null, null, null, null, null, null, null, '1', '1008');
INSERT INTO `pms_role_menu` VALUES ('1071', null, null, null, null, null, null, null, '1', '1009');
INSERT INTO `pms_role_menu` VALUES ('1072', null, null, null, null, null, null, null, '1', '1010');
INSERT INTO `pms_role_menu` VALUES ('1073', null, null, null, null, null, null, null, '1', '1011');
INSERT INTO `pms_role_menu` VALUES ('1074', null, null, null, null, null, null, null, '1', '1012');
INSERT INTO `pms_role_menu` VALUES ('1075', null, null, null, null, null, null, null, '1', '1013');
INSERT INTO `pms_role_menu` VALUES ('1076', null, null, null, null, null, null, null, '1', '1014');
INSERT INTO `pms_role_menu` VALUES ('1077', null, null, null, null, null, null, null, '1', '1015');
INSERT INTO `pms_role_menu` VALUES ('1078', null, null, null, null, null, null, null, '1', '1016');
INSERT INTO `pms_role_menu` VALUES ('1079', null, null, null, null, null, null, null, '1', '1017');
INSERT INTO `pms_role_menu` VALUES ('1080', null, null, null, null, null, null, null, '1', '1018');
INSERT INTO `pms_role_menu` VALUES ('1081', null, null, null, null, null, null, null, '1', '1019');
INSERT INTO `pms_role_menu` VALUES ('1082', null, null, null, null, null, null, null, '1', '1027');
INSERT INTO `pms_role_menu` VALUES ('1083', null, null, null, null, null, null, null, '1', '1028');
INSERT INTO `pms_role_menu` VALUES ('1084', null, null, null, null, null, null, null, '1', '1029');
INSERT INTO `pms_role_menu` VALUES ('1085', null, null, null, null, null, null, null, '1', '1030');
INSERT INTO `pms_role_menu` VALUES ('1086', null, null, null, null, null, null, null, '1', '1031');
INSERT INTO `pms_role_menu` VALUES ('1087', null, null, null, null, null, null, null, '1', '1032');
INSERT INTO `pms_role_menu` VALUES ('1088', null, null, null, null, null, null, null, '1', '1033');
INSERT INTO `pms_role_menu` VALUES ('1089', null, null, null, null, null, null, null, '1', '1034');
INSERT INTO `pms_role_menu` VALUES ('1090', null, null, null, null, null, null, null, '1', '1035');
INSERT INTO `pms_role_menu` VALUES ('1091', null, null, null, null, null, null, null, '1', '1036');
INSERT INTO `pms_role_menu` VALUES ('1092', null, null, null, null, null, null, null, '1', '1037');
INSERT INTO `pms_role_menu` VALUES ('1093', null, null, null, null, null, null, null, '1', '1038');
INSERT INTO `pms_role_menu` VALUES ('1094', null, null, null, null, null, null, null, '1', '1039');
INSERT INTO `pms_role_menu` VALUES ('1095', null, null, null, null, null, null, null, '1', '1040');
INSERT INTO `pms_role_menu` VALUES ('1096', null, null, null, null, null, null, null, '1', '1041');
INSERT INTO `pms_role_menu` VALUES ('1097', null, null, null, null, null, null, null, '1', '1042');
INSERT INTO `pms_role_menu` VALUES ('1098', null, null, null, null, null, null, null, '1', '1043');
INSERT INTO `pms_role_menu` VALUES ('1099', null, null, null, null, null, null, null, '1', '1044');
INSERT INTO `pms_role_menu` VALUES ('1100', null, null, null, null, null, null, null, '1', '1045');
INSERT INTO `pms_role_menu` VALUES ('1101', null, null, null, null, null, null, null, '1', '1046');
INSERT INTO `pms_role_menu` VALUES ('1102', null, null, null, null, null, null, null, '1', '1047');
INSERT INTO `pms_role_menu` VALUES ('1103', null, null, null, null, null, null, null, '1', '1048');
INSERT INTO `pms_role_menu` VALUES ('1104', null, null, null, null, null, null, null, '1', '1049');
INSERT INTO `pms_role_menu` VALUES ('1105', null, null, null, null, null, null, null, '1', '1050');
INSERT INTO `pms_role_menu` VALUES ('1106', null, null, null, null, null, null, null, '1', '1051');
INSERT INTO `pms_role_menu` VALUES ('1107', null, null, null, null, null, null, null, '1', '1052');
INSERT INTO `pms_role_menu` VALUES ('1108', null, null, null, null, null, null, null, '1', '1053');
INSERT INTO `pms_role_menu` VALUES ('1109', null, null, null, null, null, null, null, '1', '1054');
INSERT INTO `pms_role_menu` VALUES ('1110', null, null, null, null, null, null, null, '1', '1055');
INSERT INTO `pms_role_menu` VALUES ('1111', null, null, null, null, null, null, null, '1', '1056');
INSERT INTO `pms_role_menu` VALUES ('1112', null, null, null, null, null, null, null, '1', '1057');
INSERT INTO `pms_role_menu` VALUES ('1113', null, null, null, null, null, null, null, '1', '1058');
INSERT INTO `pms_role_menu` VALUES ('1114', null, null, null, null, null, null, null, '1', '1059');
INSERT INTO `pms_role_menu` VALUES ('1115', null, null, null, null, null, null, null, '1', '1060');
INSERT INTO `pms_role_menu` VALUES ('1116', null, null, null, null, null, null, null, '1', '1061');
INSERT INTO `pms_role_menu` VALUES ('1117', null, null, null, null, null, null, null, '1', '1062');
INSERT INTO `pms_role_menu` VALUES ('1118', null, null, null, null, null, null, null, '1', '1063');
INSERT INTO `pms_role_menu` VALUES ('1119', null, null, null, null, null, null, null, '1', '1064');
INSERT INTO `pms_role_menu` VALUES ('1120', null, null, null, null, null, null, null, '1', '1065');
INSERT INTO `pms_role_menu` VALUES ('1121', null, null, null, null, null, null, null, '1', '1066');
INSERT INTO `pms_role_menu` VALUES ('1122', null, null, null, null, null, null, null, '1', '1067');
INSERT INTO `pms_role_menu` VALUES ('1123', null, null, null, null, null, null, null, '1', '1068');
INSERT INTO `pms_role_menu` VALUES ('1124', null, null, null, null, null, null, null, '1', '1069');
INSERT INTO `pms_role_menu` VALUES ('1125', null, null, null, null, null, null, null, '1', '1070');
INSERT INTO `pms_role_menu` VALUES ('1126', null, null, null, null, null, null, null, '1', '1071');
INSERT INTO `pms_role_menu` VALUES ('1127', null, null, null, null, null, null, null, '1', '1072');
INSERT INTO `pms_role_menu` VALUES ('1128', null, null, null, null, null, null, null, '1', '1073');
INSERT INTO `pms_role_menu` VALUES ('1129', null, null, null, null, null, null, null, '1', '1072');

-- ----------------------------
-- Table structure for `pms_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `pms_role_permission`;
CREATE TABLE `pms_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ak_key_2` (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1080 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限与角色关联表';

-- ----------------------------
-- Records of pms_role_permission
-- ----------------------------
INSERT INTO `pms_role_permission` VALUES ('1000', null, null, null, null, null, null, null, '1', '61');
INSERT INTO `pms_role_permission` VALUES ('1001', null, null, null, null, null, null, null, '1', '52');
INSERT INTO `pms_role_permission` VALUES ('1002', null, null, null, null, null, null, null, '1', '54');
INSERT INTO `pms_role_permission` VALUES ('1003', null, null, null, null, null, null, null, '1', '53');
INSERT INTO `pms_role_permission` VALUES ('1004', null, null, null, null, null, null, null, '1', '51');
INSERT INTO `pms_role_permission` VALUES ('1005', null, null, null, null, null, null, null, '1', '92');
INSERT INTO `pms_role_permission` VALUES ('1006', null, null, null, null, null, null, null, '1', '94');
INSERT INTO `pms_role_permission` VALUES ('1007', null, null, null, null, null, null, null, '1', '93');
INSERT INTO `pms_role_permission` VALUES ('1008', null, null, null, null, null, null, null, '1', '91');
INSERT INTO `pms_role_permission` VALUES ('1009', null, null, null, null, null, null, null, '1', '82');
INSERT INTO `pms_role_permission` VALUES ('1010', null, null, null, null, null, null, null, '1', '84');
INSERT INTO `pms_role_permission` VALUES ('1011', null, null, null, null, null, null, null, '1', '83');
INSERT INTO `pms_role_permission` VALUES ('1012', null, null, null, null, null, null, null, '1', '81');
INSERT INTO `pms_role_permission` VALUES ('1013', null, null, null, null, null, null, null, '1', '86');
INSERT INTO `pms_role_permission` VALUES ('1014', null, null, null, null, null, null, null, '1', '88');
INSERT INTO `pms_role_permission` VALUES ('1015', null, null, null, null, null, null, null, '1', '87');
INSERT INTO `pms_role_permission` VALUES ('1016', null, null, null, null, null, null, null, '1', '85');
INSERT INTO `pms_role_permission` VALUES ('1017', null, null, null, null, null, null, null, '1', '2');
INSERT INTO `pms_role_permission` VALUES ('1018', null, null, null, null, null, null, null, '1', '4');
INSERT INTO `pms_role_permission` VALUES ('1019', null, null, null, null, null, null, null, '1', '3');
INSERT INTO `pms_role_permission` VALUES ('1020', null, null, null, null, null, null, null, '1', '1');
INSERT INTO `pms_role_permission` VALUES ('1021', null, null, null, null, null, null, null, '1', '32');
INSERT INTO `pms_role_permission` VALUES ('1022', null, null, null, null, null, null, null, '1', '34');
INSERT INTO `pms_role_permission` VALUES ('1023', null, null, null, null, null, null, null, '1', '33');
INSERT INTO `pms_role_permission` VALUES ('1024', null, null, null, null, null, null, null, '1', '35');
INSERT INTO `pms_role_permission` VALUES ('1025', null, null, null, null, null, null, null, '1', '31');
INSERT INTO `pms_role_permission` VALUES ('1026', null, null, null, null, null, null, null, '1', '12');
INSERT INTO `pms_role_permission` VALUES ('1027', null, null, null, null, null, null, null, '1', '14');
INSERT INTO `pms_role_permission` VALUES ('1028', null, null, null, null, null, null, null, '1', '13');
INSERT INTO `pms_role_permission` VALUES ('1029', null, null, null, null, null, null, null, '1', '11');
INSERT INTO `pms_role_permission` VALUES ('1030', null, null, null, null, null, null, null, '1', '22');
INSERT INTO `pms_role_permission` VALUES ('1031', null, null, null, null, null, null, null, '1', '25');
INSERT INTO `pms_role_permission` VALUES ('1032', null, null, null, null, null, null, null, '1', '24');
INSERT INTO `pms_role_permission` VALUES ('1033', null, null, null, null, null, null, null, '1', '23');
INSERT INTO `pms_role_permission` VALUES ('1034', null, null, null, null, null, null, null, '1', '21');
INSERT INTO `pms_role_permission` VALUES ('1035', null, null, null, null, null, null, null, '1', '142');
INSERT INTO `pms_role_permission` VALUES ('1036', null, null, null, null, null, null, null, '1', '144');
INSERT INTO `pms_role_permission` VALUES ('1037', null, null, null, null, null, null, null, '1', '143');
INSERT INTO `pms_role_permission` VALUES ('1038', null, null, null, null, null, null, null, '1', '141');
INSERT INTO `pms_role_permission` VALUES ('1039', null, null, null, null, null, null, null, '1', '132');
INSERT INTO `pms_role_permission` VALUES ('1040', null, null, null, null, null, null, null, '1', '134');
INSERT INTO `pms_role_permission` VALUES ('1041', null, null, null, null, null, null, null, '1', '133');
INSERT INTO `pms_role_permission` VALUES ('1042', null, null, null, null, null, null, null, '1', '131');
INSERT INTO `pms_role_permission` VALUES ('1043', null, null, null, null, null, null, null, '1', '152');
INSERT INTO `pms_role_permission` VALUES ('1044', null, null, null, null, null, null, null, '1', '154');
INSERT INTO `pms_role_permission` VALUES ('1045', null, null, null, null, null, null, null, '1', '153');
INSERT INTO `pms_role_permission` VALUES ('1046', null, null, null, null, null, null, null, '1', '151');
INSERT INTO `pms_role_permission` VALUES ('1047', null, null, null, null, null, null, null, '1', '122');
INSERT INTO `pms_role_permission` VALUES ('1048', null, null, null, null, null, null, null, '1', '124');
INSERT INTO `pms_role_permission` VALUES ('1049', null, null, null, null, null, null, null, '1', '123');
INSERT INTO `pms_role_permission` VALUES ('1050', null, null, null, null, null, null, null, '1', '121');
INSERT INTO `pms_role_permission` VALUES ('1051', null, null, null, null, null, null, null, '1', '102');
INSERT INTO `pms_role_permission` VALUES ('1052', null, null, null, null, null, null, null, '1', '104');
INSERT INTO `pms_role_permission` VALUES ('1053', null, null, null, null, null, null, null, '1', '103');
INSERT INTO `pms_role_permission` VALUES ('1054', null, null, null, null, null, null, null, '1', '101');
INSERT INTO `pms_role_permission` VALUES ('1055', null, null, null, null, null, null, null, '1', '112');
INSERT INTO `pms_role_permission` VALUES ('1056', null, null, null, null, null, null, null, '1', '114');
INSERT INTO `pms_role_permission` VALUES ('1057', null, null, null, null, null, null, null, '1', '113');
INSERT INTO `pms_role_permission` VALUES ('1058', null, null, null, null, null, null, null, '1', '111');
INSERT INTO `pms_role_permission` VALUES ('1059', null, null, null, null, null, null, null, '1', '72');
INSERT INTO `pms_role_permission` VALUES ('1060', null, null, null, null, null, null, null, '1', '74');
INSERT INTO `pms_role_permission` VALUES ('1061', null, null, null, null, null, null, null, '1', '73');
INSERT INTO `pms_role_permission` VALUES ('1062', null, null, null, null, null, null, null, '1', '71');
INSERT INTO `pms_role_permission` VALUES ('1063', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '1');
INSERT INTO `pms_role_permission` VALUES ('1064', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '11');
INSERT INTO `pms_role_permission` VALUES ('1065', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '21');
INSERT INTO `pms_role_permission` VALUES ('1066', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '31');
INSERT INTO `pms_role_permission` VALUES ('1067', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '41');
INSERT INTO `pms_role_permission` VALUES ('1068', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '51');
INSERT INTO `pms_role_permission` VALUES ('1069', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '61');
INSERT INTO `pms_role_permission` VALUES ('1070', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '71');
INSERT INTO `pms_role_permission` VALUES ('1071', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '81');
INSERT INTO `pms_role_permission` VALUES ('1072', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '85');
INSERT INTO `pms_role_permission` VALUES ('1073', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '91');
INSERT INTO `pms_role_permission` VALUES ('1074', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '101');
INSERT INTO `pms_role_permission` VALUES ('1075', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '111');
INSERT INTO `pms_role_permission` VALUES ('1076', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '121');
INSERT INTO `pms_role_permission` VALUES ('1077', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '131');
INSERT INTO `pms_role_permission` VALUES ('1078', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '141');
INSERT INTO `pms_role_permission` VALUES ('1079', '0', 'roncoo', '2016-06-03 11:07:43', 'test', '2016-06-03 11:07:43', 'ACTIVE', '', '2', '151');

-- ----------------------------
-- Table structure for `recon_interface`
-- ----------------------------
DROP TABLE IF EXISTS `recon_interface`;
CREATE TABLE `recon_interface` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `code` varchar(10) NOT NULL,
  `desc` varchar(50) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0：未激活，1：正常， 2：暂停使用， 3：关闭',
  `recon_period` int(10) NOT NULL DEFAULT '1' COMMENT '对账周期，单位为天。比如1表示今天对昨天的账，2表示对前天的账',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='描述了需要对账的渠道\r\n对账由定时任务发起，每天执行';

-- ----------------------------
-- Records of recon_interface
-- ----------------------------

-- ----------------------------
-- Table structure for `refund_record`
-- ----------------------------
DROP TABLE IF EXISTS `refund_record`;
CREATE TABLE `refund_record` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `org_merchant_order_no` varchar(50) DEFAULT NULL COMMENT '原商户订单号',
  `org_trx_no` varchar(50) DEFAULT NULL COMMENT '原支付流水号',
  `org_bank_order_no` varchar(50) DEFAULT NULL COMMENT '原银行订单号',
  `org_bank_trx_no` varchar(50) DEFAULT NULL COMMENT '原银行流水号',
  `merchant_name` varchar(100) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(100) NOT NULL COMMENT '商家编号',
  `org_product_name` varchar(60) DEFAULT NULL COMMENT '原商品名称',
  `org_biz_type` varchar(30) DEFAULT NULL COMMENT '原业务类型',
  `org_payment_type` varchar(30) DEFAULT NULL COMMENT '原支付方式类型',
  `refund_amount` decimal(20,6) DEFAULT NULL COMMENT '订单退款金额',
  `refund_trx_no` varchar(50) NOT NULL COMMENT '退款流水号',
  `refund_order_no` varchar(50) NOT NULL COMMENT '退款订单号',
  `bank_refund_order_no` varchar(50) DEFAULT NULL COMMENT '银行退款订单号',
  `bank_refund_trx_no` varchar(30) DEFAULT NULL COMMENT '银行退款流水号',
  `result_notify_url` varchar(500) DEFAULT NULL COMMENT '退款结果通知url',
  `refund_status` varchar(30) DEFAULT NULL COMMENT '退款状态',
  `refund_from` varchar(30) DEFAULT NULL COMMENT '退款来源（分发平台）',
  `refund_way` varchar(30) DEFAULT NULL COMMENT '退款方式',
  `refund_request_time` datetime DEFAULT NULL COMMENT '退款请求时间',
  `refund_success_time` datetime DEFAULT NULL COMMENT ' 退款成功时间',
  `refund_complete_time` datetime DEFAULT NULL COMMENT '退款完成时间',
  `request_apply_user_id` varchar(50) DEFAULT NULL COMMENT '退款请求,申请人登录名',
  `request_apply_user_name` varchar(90) DEFAULT NULL COMMENT '退款请求,申请人姓名',
  `refund_reason` varchar(500) DEFAULT NULL COMMENT '退款原因',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ak_key_2` (`refund_trx_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='退款记录表';

-- ----------------------------
-- Records of refund_record
-- ----------------------------

-- ----------------------------
-- Table structure for `seq_table`
-- ----------------------------
DROP TABLE IF EXISTS `seq_table`;
CREATE TABLE `seq_table` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `CURRENT_VALUE` bigint(20) NOT NULL DEFAULT '1000000002',
  `INCREMENT` smallint(6) NOT NULL DEFAULT '1',
  `REMARK` varchar(100) NOT NULL,
  PRIMARY KEY (`SEQ_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of seq_table
-- ----------------------------
INSERT INTO `seq_table` VALUES ('ACCOUNT_NO_SEQ', '10000001', '1', '账户--账户编号');
INSERT INTO `seq_table` VALUES ('ACTIVITY_NO_SEQ', '10000006', '1', '活动--活动编号');
INSERT INTO `seq_table` VALUES ('BANK_ORDER_NO_SEQ', '10000768', '1', '交易—-银行订单号');
INSERT INTO `seq_table` VALUES ('RECONCILIATION_BATCH_NO_SEQ', '10000000', '1', '对账—-批次号');
INSERT INTO `seq_table` VALUES ('TRX_NO_SEQ', '10000770', '1', '交易—-支付流水号');
INSERT INTO `seq_table` VALUES ('USER_NO_SEQ', '10001114', '1', '用户--用户编号');

-- ----------------------------
-- Table structure for `sett_daily_collect`
-- ----------------------------
DROP TABLE IF EXISTS `sett_daily_collect`;
CREATE TABLE `sett_daily_collect` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `account_no` varchar(20) NOT NULL COMMENT '账户编号',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `collect_date` date NOT NULL COMMENT '汇总日期',
  `collect_type` varchar(50) NOT NULL COMMENT '汇总类型(参考枚举:settdailycollecttypeenum)',
  `total_amount` decimal(24,10) NOT NULL COMMENT '交易总金额',
  `total_count` int(11) NOT NULL COMMENT '交易总笔数',
  `sett_status` varchar(50) NOT NULL COMMENT '结算状态(参考枚举:settdailycollectstatusenum)',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `risk_day` int(11) DEFAULT NULL COMMENT '风险预存期天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='每日待结算汇总';

-- ----------------------------
-- Records of sett_daily_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `sett_record`
-- ----------------------------
DROP TABLE IF EXISTS `sett_record`;
CREATE TABLE `sett_record` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `sett_mode` varchar(50) DEFAULT NULL COMMENT '结算发起方式(参考settmodetypeenum)',
  `account_no` varchar(20) NOT NULL COMMENT '账户编号',
  `user_no` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `user_type` varchar(50) DEFAULT NULL COMMENT '用户类型',
  `sett_date` date DEFAULT NULL COMMENT '结算日期',
  `bank_code` varchar(20) DEFAULT NULL COMMENT '银行编码',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '银行名称',
  `bank_account_name` varchar(60) DEFAULT NULL COMMENT '开户名',
  `bank_account_no` varchar(20) DEFAULT NULL COMMENT '开户账户',
  `bank_account_type` varchar(50) DEFAULT NULL COMMENT '开户账户',
  `country` varchar(200) DEFAULT NULL COMMENT '开户行所在国家',
  `province` varchar(50) DEFAULT NULL COMMENT '开户行所在省份',
  `city` varchar(50) DEFAULT NULL COMMENT '开户行所在城市',
  `areas` varchar(50) DEFAULT NULL COMMENT '开户行所在区',
  `bank_account_address` varchar(300) DEFAULT NULL COMMENT '开户行全称',
  `mobile_no` varchar(20) DEFAULT NULL COMMENT '收款人手机号',
  `sett_amount` decimal(24,10) DEFAULT NULL COMMENT '结算金额',
  `sett_fee` decimal(16,6) DEFAULT NULL COMMENT '结算手续费',
  `remit_amount` decimal(16,2) DEFAULT NULL COMMENT '结算打款金额',
  `sett_status` varchar(50) DEFAULT NULL COMMENT '结算状态(参考枚举:settrecordstatusenum)',
  `remit_confirm_time` datetime DEFAULT NULL COMMENT '打款确认时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `remit_remark` varchar(200) DEFAULT NULL COMMENT '打款备注',
  `operator_loginname` varchar(50) DEFAULT NULL COMMENT '操作员登录名',
  `operator_realname` varchar(50) DEFAULT NULL COMMENT '操作员姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='结算记录';

-- ----------------------------
-- Records of sett_record
-- ----------------------------

-- ----------------------------
-- Table structure for `sett_record_annex`
-- ----------------------------
DROP TABLE IF EXISTS `sett_record_annex`;
CREATE TABLE `sett_record_annex` (
  `id` varchar(50) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `is_delete` varchar(36) NOT NULL,
  `annex_name` varchar(200) DEFAULT NULL,
  `annex_address` varchar(500) NOT NULL,
  `settlement_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sett_record_annex
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_pri_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_pri_menu`;
CREATE TABLE `sys_pri_menu` (
  `id` int(11) NOT NULL,
  `p_id` int(11) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `manu_url` varchar(255) DEFAULT NULL,
  `pri_level` varchar(255) DEFAULT NULL,
  `pri_path` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_pri_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(25) NOT NULL,
  `type` int(1) NOT NULL,
  `url` varchar(25) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_eqidcj5580iyqinupthsc3fjt` (`resource_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('12', '权限管理', '1', '/permission', null, '1');
INSERT INTO `sys_resource` VALUES ('13', '菜单管理', '2', '/resource', '12', '1');
INSERT INTO `sys_resource` VALUES ('14', '用户管理', '2', '/user', '12', '1');
INSERT INTO `sys_resource` VALUES ('16', '角色管理', '2', '/role', '12', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_key` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员');
INSERT INTO `sys_role` VALUES ('7', 'USER', '普通用户');

-- ----------------------------
-- Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`) USING BTREE,
  KEY `FKkj7e3cva1e2s3nsd0yghpbsnk` (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '12');
INSERT INTO `sys_role_resource` VALUES ('1', '13');
INSERT INTO `sys_role_resource` VALUES ('1', '14');
INSERT INTO `sys_role_resource` VALUES ('1', '16');

-- ----------------------------
-- Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `locked_flag` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '', '$2a$10$W7sVR75u.4Kpbjg7/lHUx.6iwQXIoeBfpip4zs8P.N0Gia2SeGvGO', 'admin');
INSERT INTO `sys_user` VALUES ('22', '', '$2a$10$W945sgb/2XARxnrKoN/6TuMzIS.gYG0UknAX2CKsBpMDHJsLoXUba', 'user3');
INSERT INTO `sys_user` VALUES ('23', '', '$2a$10$PFf12Fq04xOcFvYykNT/r.Y1ee2FoSb2KJJLTDVw0YVi9c54oM1.W', 'user');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('27', '22', '1');
INSERT INTO `sys_user_role` VALUES ('28', '23', '7');

-- ----------------------------
-- Table structure for `trade_order`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `version` char(5) NOT NULL DEFAULT '0' COMMENT '版本号',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(20) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime NOT NULL COMMENT '最后修改时间',
  `status` char(1) NOT NULL COMMENT '状态(参考枚举:orderstatusenum)',
  `product_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(30) NOT NULL COMMENT '商户订单号',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `merchant_name` varchar(20) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(20) NOT NULL COMMENT '商户编号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_date` date DEFAULT NULL COMMENT '下单日期',
  `order_ip` varchar(30) DEFAULT NULL COMMENT '下单ip(客户端ip,在网关页面获取)',
  `order_referer_url` varchar(50) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `return_url` varchar(50) DEFAULT NULL COMMENT '页面回调通知url',
  `notify_url` varchar(50) DEFAULT NULL COMMENT '后台异步通知url',
  `cancel_reason` varchar(50) DEFAULT NULL COMMENT '订单撤销原因',
  `order_period` smallint(6) DEFAULT NULL COMMENT '订单有效期(单位分钟)',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `pay_product_code` char(3) DEFAULT NULL COMMENT '支付方式编号',
  `pay_product_name` varchar(20) DEFAULT NULL COMMENT '支付方式名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '支付备注',
  `trx_type` char(3) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `trx_no` varchar(20) DEFAULT NULL COMMENT '支付流水号',
  `fund_into_type` varchar(10) DEFAULT NULL COMMENT '资金流入类型',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `field1` varchar(500) DEFAULT NULL,
  `field2` varchar(500) DEFAULT NULL,
  `field3` varchar(500) DEFAULT NULL,
  `field4` varchar(500) DEFAULT NULL,
  `field5` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ak_key_2` (`merchant_order_no`,`merchant_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=805 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='roncoo pay 龙果支付 支付订单表';

-- ----------------------------
-- Records of trade_order
-- ----------------------------
INSERT INTO `trade_order` VALUES ('783', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '11571796057502', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000749', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('784', '1.0.0', '2019-10-23 02:00:57', null, null, '2019-10-23 02:00:57', '0', 'benz', '51571796057441', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000750', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('785', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '11571796057694', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000751', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('786', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '41571796057735', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000752', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('787', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '21571796057712', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000753', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('788', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '51571796057810', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000754', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('789', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '31571796057762', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000755', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('790', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '11571796057826', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000756', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('791', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '41571796057894', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000757', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('792', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '21571796057959', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000758', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('793', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '51571796058049', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000759', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('794', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '31571796058005', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000760', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('795', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '21571796058126', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000761', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('796', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '11571796058110', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000762', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('797', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '41571796058067', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000763', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('798', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '51571796058201', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000764', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('799', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '31571796058327', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000765', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('800', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '51571796058350', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000766', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('801', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '41571796058368', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000767', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('802', '1.0.0', '2019-10-23 02:00:58', null, null, '2019-10-23 02:00:58', '0', 'benz', '31571796058457', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000768', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('803', '1.0.0', '2019-10-23 02:00:59', null, null, '2019-10-23 02:00:59', '0', 'benz', '41571796058518', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000769', null, null, null, null, null, null, null, null, null);
INSERT INTO `trade_order` VALUES ('804', '1.0.0', '2019-10-23 02:00:59', null, null, '2019-10-23 02:00:59', '0', 'benz', '31571796058584', '99.990000', null, '001', '001', '2019-10-17 12:37:00', null, '0.0.0.0', '', 'returnUrl', 'notifyUrl', null, '30', '2019-10-17 13:07:00', '001', null, 'test', null, '77772019102310000770', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `trade_record`
-- ----------------------------
DROP TABLE IF EXISTS `trade_record`;
CREATE TABLE `trade_record` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `version` char(5) NOT NULL DEFAULT '0' COMMENT '版本号',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `status` char(1) DEFAULT NULL COMMENT '状态(参考枚举:paymentrecordstatusenum)',
  `editor` varchar(10) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(10) DEFAULT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `product_name` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(20) NOT NULL COMMENT '商户订单号',
  `trx_no` varchar(20) NOT NULL COMMENT '支付流水号',
  `bank_order_no` varchar(20) DEFAULT NULL COMMENT '银行订单号',
  `bank_trx_no` varchar(20) DEFAULT NULL COMMENT '银行流水号',
  `merchant_name` varchar(20) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(20) NOT NULL COMMENT '商家编号',
  `payer_user_no` varchar(20) DEFAULT NULL COMMENT '付款人编号',
  `payer_name` varchar(20) DEFAULT NULL COMMENT '付款人名称',
  `payer_pay_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方支付金额',
  `payer_fee` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方手续费',
  `payer_account_type` varchar(10) DEFAULT NULL COMMENT '付款方账户类型(参考账户类型枚举:accounttypeenum)',
  `receiver_user_no` varchar(20) DEFAULT NULL COMMENT '收款人编号',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收款人名称',
  `receiver_pay_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方支付金额',
  `receiver_fee` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方手续费',
  `receiver_account_type` varchar(10) DEFAULT NULL COMMENT '收款方账户类型(参考账户类型枚举:accounttypeenum)',
  `order_ip` varchar(30) DEFAULT NULL COMMENT '下单ip(客户端ip,从网关中获取)',
  `order_referer_url` varchar(50) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `plat_income` decimal(20,6) DEFAULT NULL COMMENT '平台收入',
  `fee_rate` decimal(20,6) DEFAULT NULL COMMENT '费率',
  `plat_cost` decimal(20,6) DEFAULT NULL COMMENT '平台成本',
  `plat_profit` decimal(20,6) DEFAULT NULL COMMENT '平台利润',
  `return_url` varchar(50) DEFAULT NULL COMMENT '页面回调通知url',
  `notify_url` varchar(50) DEFAULT NULL COMMENT '后台异步通知url',
  `pay_way_code` varchar(10) DEFAULT NULL COMMENT '支付方式编号',
  `pay_way_name` varchar(20) DEFAULT NULL COMMENT '支付方式名称',
  `pay_success_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `is_refund` char(3) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `trx_type` varchar(10) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `order_from` varchar(10) DEFAULT NULL COMMENT '订单来源',
  `pay_type_code` varchar(10) DEFAULT NULL COMMENT '支付类型编号',
  `pay_type_name` varchar(20) DEFAULT NULL COMMENT '支付类型名称',
  `fund_into_type` varchar(20) DEFAULT NULL COMMENT '资金流入类型',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `field1` varchar(50) DEFAULT NULL,
  `field2` varchar(50) DEFAULT NULL,
  `field3` varchar(50) DEFAULT NULL,
  `field4` varchar(50) DEFAULT NULL,
  `field5` varchar(50) DEFAULT NULL,
  `bank_return_msg` varchar(200) DEFAULT NULL COMMENT '银行返回信息',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ak_key_2` (`trx_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=797 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='支付记录表';

-- ----------------------------
-- Records of trade_record
-- ----------------------------
INSERT INTO `trade_record` VALUES ('776', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '51571796057441', '77772019102310000750', '66662019102310000748', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('777', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '11571796057694', '77772019102310000751', '66662019102310000749', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('778', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '41571796057735', '77772019102310000752', '66662019102310000750', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('779', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '21571796057712', '77772019102310000753', '66662019102310000751', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('780', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '31571796057762', '77772019102310000755', '66662019102310000752', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('781', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '51571796057810', '77772019102310000754', '66662019102310000753', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('782', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '41571796057894', '77772019102310000757', '66662019102310000754', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('783', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '11571796057826', '77772019102310000756', '66662019102310000755', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('784', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '21571796057959', '77772019102310000758', '66662019102310000756', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('785', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '51571796058049', '77772019102310000759', '66662019102310000757', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('786', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '21571796058126', '77772019102310000761', '66662019102310000758', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('787', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '31571796058005', '77772019102310000760', '66662019102310000759', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('788', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '51571796058201', '77772019102310000764', '66662019102310000760', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('789', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '41571796058067', '77772019102310000763', '66662019102310000761', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('790', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '11571796058110', '77772019102310000762', '66662019102310000762', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('791', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '31571796058327', '77772019102310000765', '66662019102310000763', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('792', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '51571796058350', '77772019102310000766', '66662019102310000764', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('793', '1.0.0', '2019-10-23 02:00:58', '0', null, null, '2019-10-23 02:00:58', 'benz', '41571796058368', '77772019102310000767', '66662019102310000765', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('794', '1.0.0', '2019-10-23 02:00:59', '0', null, null, '2019-10-23 02:00:59', 'benz', '31571796058457', '77772019102310000768', '66662019102310000766', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('795', '1.0.0', '2019-10-23 02:00:59', '0', null, null, '2019-10-23 02:00:59', 'benz', '41571796058518', '77772019102310000769', '66662019102310000767', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
INSERT INTO `trade_record` VALUES ('796', '1.0.0', '2019-10-23 02:00:59', '0', null, null, '2019-10-23 02:00:59', 'benz', '31571796058584', '77772019102310000770', '66662019102310000768', null, '001', '001', null, null, '99.990000', '5.999400', null, null, null, null, null, null, null, null, '99.990000', '93.990600', '0.060000', null, null, null, '/channel/receiveNotify', null, null, null, null, null, null, null, null, null, null, null, null, 'test', null, null, null, null, null, null);
