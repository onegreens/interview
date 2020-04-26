/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : interview

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-04-26 18:17:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('10');
INSERT INTO `hibernate_sequence` VALUES ('10');
INSERT INTO `hibernate_sequence` VALUES ('10');

-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqe7xx3t3udo4b28t3lkavo3wd` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('3', '2020-04-26 08:25:37', '1', '《亿级流量电商详情页系统实战（第二版）：缓存架构+高可用服务架构+微服务架构》', '0', '0', null, null);
INSERT INTO `t_book` VALUES ('4', '2020-04-26 08:38:13', '1', '《EffectiveJava中文版（原书第3版）》', '0', '0', null, null);
INSERT INTO `t_book` VALUES ('5', '2020-04-26 08:42:56', '1', 'Java核心技术（卷2）：高级特性（原书第9版）', '0', '0', null, null);
INSERT INTO `t_book` VALUES ('6', '2020-04-26 08:50:08', '1', 'SpringBoot实战', '0', '0', null, null);
INSERT INTO `t_book` VALUES ('7', '2020-04-26 08:58:07', '1', '疯狂Android讲义_第四版', '0', '0', null, null);
INSERT INTO `t_book` VALUES ('8', '2020-04-26 08:59:08', '1', 'android应用与开发环境', '1', '1', null, '7');
INSERT INTO `t_book` VALUES ('9', '2020-04-26 10:04:49', '1', 'Effective.Java', '0', '0', null, null);

-- ----------------------------
-- Table structure for `t_book_content`
-- ----------------------------
DROP TABLE IF EXISTS `t_book_content`;
CREATE TABLE `t_book_content` (
  `id` int(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `degree` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `think` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `book_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiaxegmyut7mqatvrvbo06r24m` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book_content
-- ----------------------------

-- ----------------------------
-- Table structure for `t_question`
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` int(20) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `cate_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------
