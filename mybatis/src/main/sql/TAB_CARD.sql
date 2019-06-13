/*
Navicat MySQL Data Transfer

Source Server         : 服务器
Source Server Version : 50721
Source Host           : www.wecloud.xyz:3306
Source Database       : Study

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-10 03:34:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for TAB_CARD
-- ----------------------------
DROP TABLE IF EXISTS `TAB_CARD`;
CREATE TABLE `TAB_CARD` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(11) DEFAULT NULL,
  `card_type` varchar(10) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `TAB_CARD_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `TAB_EMP` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of TAB_CARD
-- ----------------------------
INSERT INTO `TAB_CARD` VALUES ('1', '招商银行金卡', '银行卡', '1');
INSERT INTO `TAB_CARD` VALUES ('2', '二代居民身份证', '身份证', '2');
INSERT INTO `TAB_CARD` VALUES ('3', '中国联通大王卡', '电话卡', '2');
INSERT INTO `TAB_CARD` VALUES ('4', '二代居民身份证', '身份证', '1');
INSERT INTO `TAB_CARD` VALUES ('5', '中国移动大王卡', '电话卡', '3');
