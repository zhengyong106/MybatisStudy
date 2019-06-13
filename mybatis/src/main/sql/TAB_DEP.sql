/*
Navicat MySQL Data Transfer

Source Server         : 服务器
Source Server Version : 50721
Source Host           : www.wecloud.xyz:3306
Source Database       : Study

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-10 03:34:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for TAB_DEP
-- ----------------------------
DROP TABLE IF EXISTS `TAB_DEP`;
CREATE TABLE `TAB_DEP` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(11) DEFAULT NULL,
  `dep_ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of TAB_DEP
-- ----------------------------
INSERT INTO `TAB_DEP` VALUES ('1', '销售部', '2011-03-15 00:00:00');
INSERT INTO `TAB_DEP` VALUES ('2', '研发部', '2007-11-26 00:00:00');
INSERT INTO `TAB_DEP` VALUES ('3', '公关部', '1999-05-31 00:00:00');
