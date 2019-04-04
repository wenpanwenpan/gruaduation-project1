/*
Navicat MySQL Data Transfer

Source Server         : MyBatis
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-09 09:16:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `name` varchar(255) NOT NULL,
  `price` double(10,2) NOT NULL,
  `pid` int(8) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `count` int(8) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `tid` int(8) DEFAULT NULL,
  `author_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('oracle教程', '89.90', '1', '一本学习oracle的好书', '10', null, '1', '1');
INSERT INTO `commodity` VALUES ('二手自行车', '500.00', '2', '8层新自行车', '1', 'bicycle.jpg', '4', '1');
INSERT INTO `commodity` VALUES ('二手联想笔记本', '2999.90', '3', '7层新，i5+15g内存', '2', null, '2', '2');
INSERT INTO `commodity` VALUES ('二手球鞋', '1999.90', '4', '9层新AJ', '1', 'shoe.png', '5', '2');
INSERT INTO `commodity` VALUES ('二手摩托车', '5999.00', '5', '日本哈雷摩托', '1', 'motuo.jpg', '4', '2');
INSERT INTO `commodity` VALUES ('二手java书籍', '36.00', '6', '出售一本java开发实战', '1', null, '1', '2');
INSERT INTO `commodity` VALUES ('二手华为手机', '900.00', '7', '二手华为荣耀10一台', '4', 'rongyao10.jpg', '6', '2');
INSERT INTO `commodity` VALUES ('二手hadoop权威指南', '60.00', '8', '正品hadoop权威指南第四版', '1', 'hadoopbook.jpg', '1', '1');
INSERT INTO `commodity` VALUES ('文攀', '100.00', '9', '好人', '1', '19216800100120190222204216858332.jpg', '3', '1');
INSERT INTO `commodity` VALUES ('保温杯', '160.00', '10', '适合泡枸杞的保温杯', '1', '19216800100120190224150813047408.jpg', '6', '1');
INSERT INTO `commodity` VALUES ('二手oracle从入门到精通', '50.00', '11', 'oracle入门必读书籍', '3', '19216800100120190224153214588147.jpg', '1', '1');
INSERT INTO `commodity` VALUES ('黑客攻防从入门到精通', '69.90', '12', '黑客攻防从入门到入狱书籍', '5', '19216800100120190224153959820309.jpg', '1', '1');
