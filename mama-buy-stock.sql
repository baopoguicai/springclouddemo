/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : mama-buy-stock

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-09-27 15:49:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `lock_stock` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock
-- ----------------------------

-- ----------------------------
-- Table structure for t_stock_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_flow`;
CREATE TABLE `t_stock_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) DEFAULT NULL,
  `sku_id` bigint(20) DEFAULT NULL,
  `stock_before` int(11) DEFAULT NULL,
  `stock_after` int(11) DEFAULT NULL,
  `stock_change` int(11) DEFAULT NULL,
  `lock_stock_before` int(11) DEFAULT NULL,
  `lock_stock_after` int(11) DEFAULT NULL,
  `lock_stock_change` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock_flow
-- ----------------------------
