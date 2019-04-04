/*
Navicat MySQL Data Transfer

Source Server         : MyBatis
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-09 09:17:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commoditytype
-- ----------------------------
DROP TABLE IF EXISTS `commoditytype`;
CREATE TABLE `commoditytype` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commoditytype
-- ----------------------------
INSERT INTO `commoditytype` VALUES ('1', '书籍类');
INSERT INTO `commoditytype` VALUES ('2', '家电类');
INSERT INTO `commoditytype` VALUES ('3', '食品类');
INSERT INTO `commoditytype` VALUES ('4', '交通工具类');
INSERT INTO `commoditytype` VALUES ('5', '服饰类');
INSERT INTO `commoditytype` VALUES ('6', '生活日用品类');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `uid` int(8) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `commodities` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '文攀', '13548112146', 'wenpan', 'wenpan', '四川省成都市', '610079', '1_1_2_8_9_10_11_12');
INSERT INTO `customer` VALUES ('2', '张三', '15202869691', '张三', 'zhangsan', '北京市朝阳区', '210052', '3_4_5_6_7');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '文体部');
INSERT INTO `department` VALUES ('2', '纪检部');
INSERT INTO `department` VALUES ('3', '开发部');
INSERT INTO `department` VALUES ('4', '测试部');
INSERT INTO `department` VALUES ('5', '营销部');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '文攀', 'wenpan@qq.com', '1', '1');
