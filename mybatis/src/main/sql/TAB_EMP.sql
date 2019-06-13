/*
Navicat MySQL Data Transfer

Source Server         : 服务器
Source Server Version : 50721
Source Host           : www.wecloud.xyz:3306
Source Database       : Study

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-10 03:35:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for TAB_EMP
-- ----------------------------
DROP TABLE IF EXISTS `TAB_EMP`;
CREATE TABLE `TAB_EMP` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(11) DEFAULT NULL,
  `emp_salary` double(10,2) DEFAULT NULL,
  `emp_age` int(11) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `dep_id` (`dep_id`),
  CONSTRAINT `TAB_EMP_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `TAB_DEP` (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of TAB_EMP
-- ----------------------------
INSERT INTO `TAB_EMP` VALUES ('1', '老王', '6000.00', '88', '1');
INSERT INTO `TAB_EMP` VALUES ('2', '老李', '9000.00', '88', '3');
INSERT INTO `TAB_EMP` VALUES ('3', '老李', '9000.00', '88', '2');
