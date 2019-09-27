/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : mama-buy-user

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-09-26 11:24:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message_event_process
-- ----------------------------
DROP TABLE IF EXISTS `message_event_process`;
CREATE TABLE `message_event_process` (
  `id` bigint(20) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `payload` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_event_process
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `uuid` bigint(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for user_bonus_points
-- ----------------------------
DROP TABLE IF EXISTS `user_bonus_points`;
CREATE TABLE `user_bonus_points` (
  `id` int(20) NOT NULL,
  `user_uuid` bigint(11) NOT NULL,
  `points` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_bonus_points
-- ----------------------------
