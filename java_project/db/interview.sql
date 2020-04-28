/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : interview

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-04-28 17:44:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('242026588160393216', '2020-04-27 11:44:18', 'Effective Java 中文第二版', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242026814623449088', '2020-04-27 11:45:12', 'Java多线程编程核心技术_完整版', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242026863134769152', '2020-04-27 11:53:02', '疯狂Android讲义_第四版', 'ANDROID', null);
INSERT INTO `t_book` VALUES ('242084042814521344', '2020-04-27 15:32:37', 'Head First Java', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084082685575168', '2020-04-27 15:32:46', 'Java 核心技术：卷1 基础知识', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084118966304768', '2020-04-27 15:32:55', 'Java 编程思想', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084154160709632', '2020-04-27 15:33:03', 'Java开发实战经典', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084188457533440', '2020-04-27 15:33:11', 'Effective Java', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084219424079872', '2020-04-27 15:33:19', 'Spring揭秘', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084252261285888', '2020-04-27 15:33:27', '分布式 Java 应用：基础与实践', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084284754558976', '2020-04-27 15:33:34', 'http权威指南', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084318078304256', '2020-04-27 15:33:42', '深入理解 Java 虚拟机', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084372931411968', '2020-04-27 15:33:55', 'JAVA 2核心技术 卷II：高级特性(原书第7版)', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242084422684246016', '2020-04-27 15:34:07', 'Java Web开发详解--XML+XSLT+Servlet+JSP深入剖析与实例应用', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242085184936083456', '2020-04-27 15:37:09', 'Java Concurrency in Practice', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242085215093129216', '2020-04-27 15:37:16', 'Head First Design Patterns', 'JAVA', null);
INSERT INTO `t_book` VALUES ('242086207528370176', '2020-04-27 15:41:13', 'JavaScript Dom编程艺术（第二版）', 'JS', null);
INSERT INTO `t_book` VALUES ('242086248036958208', '2020-04-27 15:41:22', 'JavaScript高级程序设计（第三版）', 'JS', null);
INSERT INTO `t_book` VALUES ('242086281528475648', '2020-04-27 15:41:30', 'Javascript设计模式', 'JS', null);
INSERT INTO `t_book` VALUES ('242086312876703744', '2020-04-27 15:41:38', 'Jquery权威指南（第二版）', 'JS', null);
INSERT INTO `t_book` VALUES ('242086342983417856', '2020-04-27 15:41:45', '高性能Javascript', 'JS', null);
INSERT INTO `t_book` VALUES ('242086896979673088', '2020-04-27 15:43:57', 'MySQL必知必会', 'DB', null);
INSERT INTO `t_book` VALUES ('242086937953828864', '2020-04-27 15:44:07', 'SQL学习指南（第2版 修订版）', 'DB', null);
INSERT INTO `t_book` VALUES ('242086981050302464', '2020-04-27 15:44:17', 'MySQL技术内幕——InnoDB存储引擎', 'DB', null);
INSERT INTO `t_book` VALUES ('242087019520458752', '2020-04-27 15:44:26', 'Redis设计与实现', 'DB', null);
INSERT INTO `t_book` VALUES ('242087077066309632', '2020-04-27 15:44:40', '高可用MySQL（第2版）', 'DB', null);
INSERT INTO `t_book` VALUES ('242087141104943104', '2020-04-27 15:44:55', '高性能MySQL（第3版）', 'DB', null);
INSERT INTO `t_book` VALUES ('242087202782183424', '2020-04-27 15:45:10', 'Oracle高效设计', 'DB', null);
INSERT INTO `t_book` VALUES ('242087238748340224', '2020-04-27 15:45:19', 'Oracle编程艺术：深入理解数据库体系结构（第3版）', 'DB', null);
INSERT INTO `t_book` VALUES ('242087634581585920', '2020-04-27 15:46:53', '鸟哥的 Linux 私房菜', 'LINUX', null);
INSERT INTO `t_book` VALUES ('242087666529599488', '2020-04-27 15:47:01', 'The Linux Command Line', 'LINUX', null);
INSERT INTO `t_book` VALUES ('242087703745658880', '2020-04-27 15:47:10', '深入理解 Linux 内核', 'LINUX', null);
INSERT INTO `t_book` VALUES ('242087739078475776', '2020-04-27 15:47:18', 'Linux/UNIX系统编程手册', 'LINUX', null);
INSERT INTO `t_book` VALUES ('242087780912463872', '2020-04-27 15:47:28', 'UNIX 环境高级编程', 'LINUX', null);

-- ----------------------------
-- Table structure for `t_book_chapter`
-- ----------------------------
DROP TABLE IF EXISTS `t_book_chapter`;
CREATE TABLE `t_book_chapter` (
  `id` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `book_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvp58j65meenako4mx5bnnfus` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book_chapter
-- ----------------------------
INSERT INTO `t_book_chapter` VALUES ('242101628583280640', '2020-04-27 17:26:58', '1', '第2章 创建和销毁对象', '4', '2', null, null, '242026588160393216');
INSERT INTO `t_book_chapter` VALUES ('242117145331236864', '2020-04-27 17:44:09', '1', '第3章 对于所有对象都通用的方法', '28', '3', null, null, '242026588160393216');
INSERT INTO `t_book_chapter` VALUES ('242117291242684416', '2020-04-27 17:44:44', '1', '第4章 类和接口', '58', '4', null, null, '242026588160393216');
INSERT INTO `t_book_chapter` VALUES ('242118012558114816', '2020-04-27 17:47:36', '1', '第5章 泛型', '97', '5', null, null, '242026588160393216');
INSERT INTO `t_book_chapter` VALUES ('242118387914768384', '2020-04-27 17:49:05', '2', '第1条：考虑用静态工厂方法代替构造器', '4', '1', null, '242101628583280640', '242026588160393216');
INSERT INTO `t_book_chapter` VALUES ('242120666529136640', '2020-04-27 17:58:08', '2', '  第3条：用私有构造器或者枚举类型强化Singleton属性', '14', '3', null, '242101628583280640', '242026588160393216');
INSERT INTO `t_book_chapter` VALUES ('242120216551620608', '2020-04-27 17:56:21', '2', '第2条：遇到多个构造器参数时要考虑用构建器', '9', '2', null, '242101628583280640', '242026588160393216');

-- ----------------------------
-- Table structure for `t_book_content`
-- ----------------------------
DROP TABLE IF EXISTS `t_book_content`;
CREATE TABLE `t_book_content` (
  `id` varchar(32) NOT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `degree` int(2) DEFAULT NULL,
  `level` int(2) DEFAULT NULL,
  `think` varchar(1024) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `book_id` varchar(32) DEFAULT NULL,
  `chapter_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf219ejarasm93fylpavhjow1c` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book_content
-- ----------------------------
INSERT INTO `t_book_content` VALUES ('242442971604058112', '示例:\npublic static Boolean valueOf(boolean b){\n    return b?Boolean.TRUE:Boolean:FALSE;\n}', '2020-04-28 15:30:51', '1', null, '优势:\n- 1 : 有名称\n- 2 : 不必在每次调用的时候都创建一个新对象\n- 3 : 可以返回原返回类型的任何子类型对象\n- 4 : 在创建参数化类型的时候,使代码变得简洁\n缺点:\n- 1 : 类如果不含有公有的或者受保护的构造器,就不能够被子类化\n- 2 : 和静态方法没什么区别', '静态工厂方法', null, '242026588160393216', '242118387914768384');
INSERT INTO `t_book_content` VALUES ('242447347848577024', 'valueOf - 类型装换方法\nof - valueOf的简洁版\ngetInstance - 返回唯一的实例\nnewInstance - 返回新的实例\ngetType - 返回对象类型\nnewType - 返回对象类型', '2020-04-28 15:36:15', '1', null, '按照习惯', '静态工厂方法的惯用名称', null, '242026588160393216', '242118387914768384');

-- ----------------------------
-- Table structure for `t_question`
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` varchar(32) NOT NULL,
  `answer` varchar(2048) DEFAULT NULL,
  `cate_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------
