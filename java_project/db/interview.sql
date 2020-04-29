/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : interview

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-04-29 22:52:56
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
INSERT INTO `hibernate_sequence` VALUES ('31');

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
  `note_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('242026588160393216', '2020-04-27 11:44:18', 'Effective Java 中文第二版', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242026814623449088', '2020-04-27 11:45:12', 'Java多线程编程核心技术_完整版', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242026863134769152', '2020-04-27 11:53:02', '疯狂Android讲义_第四版', 'ANDROID', null, null);
INSERT INTO `t_book` VALUES ('242084042814521344', '2020-04-27 15:32:37', 'Head First Java', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084082685575168', '2020-04-27 15:32:46', 'Java 核心技术：卷1 基础知识', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084118966304768', '2020-04-27 15:32:55', 'Java 编程思想', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084154160709632', '2020-04-27 15:33:03', 'Java开发实战经典', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084188457533440', '2020-04-27 15:33:11', 'Effective Java', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084219424079872', '2020-04-27 15:33:19', 'Spring揭秘', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084252261285888', '2020-04-27 15:33:27', '分布式 Java 应用：基础与实践', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084284754558976', '2020-04-27 15:33:34', 'http权威指南', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084318078304256', '2020-04-27 15:33:42', '深入理解 Java 虚拟机', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084372931411968', '2020-04-27 15:33:55', 'JAVA 2核心技术 卷II：高级特性(原书第7版)', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242084422684246016', '2020-04-27 15:34:07', 'Java Web开发详解--XML+XSLT+Servlet+JSP深入剖析与实例应用', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242085184936083456', '2020-04-27 15:37:09', 'Java Concurrency in Practice', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242085215093129216', '2020-04-27 15:37:16', 'Head First Design Patterns', 'JAVA', null, null);
INSERT INTO `t_book` VALUES ('242086207528370176', '2020-04-27 15:41:13', 'JavaScript Dom编程艺术（第二版）', 'JS', null, null);
INSERT INTO `t_book` VALUES ('242086248036958208', '2020-04-27 15:41:22', 'JavaScript高级程序设计（第三版）', 'JS', null, null);
INSERT INTO `t_book` VALUES ('242086281528475648', '2020-04-27 15:41:30', 'Javascript设计模式', 'JS', null, null);
INSERT INTO `t_book` VALUES ('242086312876703744', '2020-04-27 15:41:38', 'Jquery权威指南（第二版）', 'JS', null, null);
INSERT INTO `t_book` VALUES ('242086342983417856', '2020-04-27 15:41:45', '高性能Javascript', 'JS', null, null);
INSERT INTO `t_book` VALUES ('242086896979673088', '2020-04-27 15:43:57', 'MySQL必知必会', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242086937953828864', '2020-04-27 15:44:07', 'SQL学习指南（第2版 修订版）', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242086981050302464', '2020-04-27 15:44:17', 'MySQL技术内幕——InnoDB存储引擎', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242087019520458752', '2020-04-27 15:44:26', 'Redis设计与实现', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242087077066309632', '2020-04-27 15:44:40', '高可用MySQL（第2版）', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242087141104943104', '2020-04-27 15:44:55', '高性能MySQL（第3版）', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242087202782183424', '2020-04-27 15:45:10', 'Oracle高效设计', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242087238748340224', '2020-04-27 15:45:19', 'Oracle编程艺术：深入理解数据库体系结构（第3版）', 'DB', null, null);
INSERT INTO `t_book` VALUES ('242087634581585920', '2020-04-27 15:46:53', '鸟哥的 Linux 私房菜', 'LINUX', null, null);
INSERT INTO `t_book` VALUES ('242087666529599488', '2020-04-27 15:47:01', 'The Linux Command Line', 'LINUX', null, null);
INSERT INTO `t_book` VALUES ('242087703745658880', '2020-04-27 15:47:10', '深入理解 Linux 内核', 'LINUX', null, null);
INSERT INTO `t_book` VALUES ('242087739078475776', '2020-04-27 15:47:18', 'Linux/UNIX系统编程手册', 'LINUX', null, null);
INSERT INTO `t_book` VALUES ('242087780912463872', '2020-04-27 15:47:28', 'UNIX 环境高级编程', 'LINUX', null, null);

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
  `answer` text,
  `cate_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES ('242739169346977792', 'select count(*) re from table\ngroup by field\nhaving re>1', 'DB', '2020-04-29 10:55:51', '', '查找重复数据');
INSERT INTO `t_question` VALUES ('242739547002109952', 'select Max(filed) from table group by filed', 'DB', '2020-04-29 10:57:21', '', '查找重复数据最大值');
INSERT INTO `t_question` VALUES ('242739626400284672', '存储过程是一些预存的sql语句，即编译过的代码块，执行效率高\n\n确保数据安全\n\n替代大量的T_SQL语句，降低网络通信量，提高通信效率', 'DB', '2020-04-29 10:57:40', '', '什么是存储过程？有哪些优缺点？');
INSERT INTO `t_question` VALUES ('242740306447958016', '为了加速对表中数据行的检索而创建的一个分散的存储结构\n\n是针对表而建立的，是由数据页面以外的索引页面组成的\n\n每个索引页面的行都会有逻辑指针，以便加速检索物理数据\n\n- 优点\n\n加快数据的检索速度\n\n创建唯一性索引，保证数据库表中每一行数据的唯一性\n\n加速表与表之间的连接\n\n在使用分组和排序从句查询时，减少分组和排序的时间\n\n- 缺点\n 索引需要占据物理空间\n \n 当对表中的数据进行增删改操作时，索引需要进行动态维护，降低数据的维护速度', 'DB', '2020-04-29 11:00:22', '', '索引是什么？有什么作用以及优缺点？');
INSERT INTO `t_question` VALUES ('242740500669399040', '事务是并发的基本单位\n\nACID\n\n原子性：事务是不可分割的的。\n\n隔离性：事务之间不相互受影响\n\n一致性：事务使数据库的从一个一致性状态转变到另一个一致性状态\n\n持久性：事务一旦提交，其数据的修改就是永久的', 'DB', '2020-04-29 11:01:08', '', '什么是事务？');
INSERT INTO `t_question` VALUES ('242740577634877440', '\n- 悲观锁\n假定会发生并发冲突，屏蔽一切可能会违反数据完整性的操作\n\n- 乐观锁\n假定不会发生并发冲突，只是在提交操作时检查是否违反数据完整性', 'DB', '2020-04-29 11:01:27', '', '数据库的乐观锁和悲观锁是什么？');
INSERT INTO `t_question` VALUES ('242740741850267648', 'delete和truncate只删除表的数据，不删除表结构\n\n删除速度 drop > truncate > delete\n\ndelete 是dml，操作会放到 rollback segement中，事务提交才会生效，如果有相应的trigger ,执行的时候就会触发\n\ndrop和truncate 是ddl，操作立即生效，不能回滚。操作无法触发trigger\n\n- 使用场景\n\n不再需要一张表的时候，用drop\n\n想删除部分数据行时候，用delete，并且带上where子句\n\n保留表而删除所有数据的时候用truncate', 'DB', '2020-04-29 11:02:06', '', '简单说一说drop、delete与truncate的区别');
INSERT INTO `t_question` VALUES ('242740848654024704', '视图是虚拟表，具有和物理表相同的功能。可以对视图进行增删改查操作，是从一个或多个表中导出的表\n\n只暴露部分字段给访问者，所以就建一个虚表，就是视图。\n\n查询的数据来源于不同的表，而查询者希望以统一的方式查询，这样也可以建立一个视图，把多个表查询结果联合起来，查询者只需要直接从视图中获取数据，不必考虑数据来源于不同表所带来的差异', 'DB', '2020-04-29 11:02:31', '', '什么是视图？以及视图的使用场景有哪些？');
INSERT INTO `t_question` VALUES ('242740957101948928', '1. 无重复列\n\n数据库表中，每一列都是不可分割的基本数据项\n\n2. 属性依赖于主键\n\n要求数据库表中的每一个实例或者行可以被唯一的区分\n\n3. 属性不依赖于非主键\n\n要求数据库表中不包含已在其他表中已包含的非主关键字信息', 'DB', '2020-04-29 11:02:57', '', '三大范式');
INSERT INTO `t_question` VALUES ('242741144075632640', 'UNION 操作符用于合并两个或多个 SELECT 语句的结果集。无重复\n\n(NION 内部的 SELECT 语句必须拥有相同数量的列。列也必须拥有相似的数据类型。同时，每条 SELECT 语句中的列的顺序必须相同。\n SQL UNION 语法)\n \n UNION ALL 操作符用于合并两个或多个 SELECT 语句的结果集。可重复', 'DB', '2020-04-29 11:03:42', '', 'union和union all的区别');
INSERT INTO `t_question` VALUES ('242741218839101440', '触发器是一种特殊类型的存储过程\n \n 和存储过程的区别在于触发器主要是通过事件进行触发被自动调用的，而存储过程是通过存储过程名称被调用\n \n 1. dml触发器分为instead of触发 和after触发\n \n 2. ddl触发器\n \n 3. 登录触发器', 'DB', '2020-04-29 11:04:00', '', '什么是触发器？');
INSERT INTO `t_question` VALUES ('242747091695173632', 'boolean byte short int long float double\n      1         8     16    32  64     32      64', 'JAVA', '2020-04-29 11:27:20', '', 'JAVA中的几种基本类型,各占用多少字节?');
INSERT INTO `t_question` VALUES ('242748238262697984', '不能被继承，因为String类有final修饰符，而final修饰的类是不能被继承的。', 'JAVA', '2020-04-29 11:31:53', 'https://www.cnblogs.com/nnngu/p/8231458.html', 'Java中的String类能否被继承？为什么？');
INSERT INTO `t_question` VALUES ('242792185915768832', 'String：\n\n　　1，是字符串常量，一旦创建就不能修改。对于已经存在了的String对象的修改都是重新创建一个新的对象，然后把新的值保存进去。\n\n　　2，String也是final类，不能被继承。\n\n　　3，String是对象而不是基本类型。\n\n　　4，string覆盖了equals方法和hashCode()方法。\n\nStingBuffer：\n\n　　1，是字符串可变对象，可以对字符串进行操作，修改字符串原有值时不会新建一个对象。\n\n　　2，执行效率较慢，但是线程安全\n\n　　3，StringBuffer没有覆盖equals方法和hashCode()方法。\n\n　　4，可以动态的构造字符数据，append（）方法。\n\nStringBuilder：\n\n　　1，也是字符串可变对象，同StringBuffer一样，可以对字符串进行操作，也不会新建对象。\n\n　　2，执行效率高效，但是线程不安全。', 'JAVA', '2020-04-29 14:26:31', 'https://www.cnblogs.com/jasonboren/p/11053044.html', 'String， Stringbuffer， StringBuilder 的区别');
INSERT INTO `t_question` VALUES ('242793844024803328', '1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 \n2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。 \n3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。', 'JAVA', '2020-04-29 14:33:07', 'https://www.iteye.com/blog/pengcqu-502676', 'ArrayList 和 LinkedList 有什么区别');
INSERT INTO `t_question` VALUES ('242794841207996416', '父类中static代码块，子类中的static\n顺序执行父类的普通代码块\n父类构造器\n子类普通代码块\n子类构造器，按顺序执行\n子类方法的执行', 'JAVA', '2020-04-29 14:37:04', 'https://blog.csdn.net/DuMeiLin1/article/details/79120381', '类的实例化顺序');
INSERT INTO `t_question` VALUES ('242804911031451648', '(1) HashMap：它根据键的hashCode值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，但遍历顺序却是不确定的。 HashMap最多只允许一条记录的键为null，允许多条记录的值为null。HashMap非线程安全，即任一时刻可以有多个线程同时写HashMap，可能会导致数据的不一致。如果需要满足线程安全，可以用 Collections的synchronizedMap方法使HashMap具有线程安全的能力，或者使用ConcurrentHashMap。\n\n(2) Hashtable：Hashtable是遗留类，很多映射的常用功能与HashMap类似，不同的是它承自Dictionary类，并且是线程安全的，任一时间只有一个线程能写Hashtable，并发性不如ConcurrentHashMap，因为ConcurrentHashMap引入了分段锁。Hashtable不建议在新代码中使用，不需要线程安全的场合可以用HashMap替换，需要线程安全的场合可以用ConcurrentHashMap替换。\n\n(3) LinkedHashMap：LinkedHashMap是HashMap的一个子类，保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的，也可以在构造时带参数，按照访问次序排序。\n\n(4) TreeMap：TreeMap实现SortedMap接口，能够把它保存的记录根据键排序，默认是按键值的升序排序，也可以指定排序的比较器，当用Iterator遍历TreeMap时，得到的记录是排过序的。如果使用排序的映射，建议使用TreeMap。在使用TreeMap时，key必须实现Comparable接口或者在构造TreeMap传入自定义的Comparator，否则会在运行时抛出java.lang.ClassCastException类型的异常。', 'JAVA', '2020-04-29 15:17:05', 'https://zhuanlan.zhihu.com/p/21673805', '用过哪些 Map 类，都有什么区别，HashMap 是线程安全的吗,并发下使用的 Map 是什么，他们内部原理分别是什么，比如存储方式， hashcode，扩容， 默认容量等');
INSERT INTO `t_question` VALUES ('242808166193037312', 'TreeMap和LinkedHashMap是有序的（TreeMap默认升序，LinkedHashMap则记录了插入顺序）', 'JAVA', '2020-04-29 15:30:01', 'https://www.iteye.com/blog/uule-1522291', '有没有有顺序的 Map 实现类， 如果有， 他们是怎么保证有序的');
INSERT INTO `t_question` VALUES ('242809495581884416', '1、抽象类和接口都不能直接实例化，如果要实例化，抽象类变量必须指向实现所有抽象方法的子类对象，接口变量必须指向实现所有接口方法的类对象。\n2、抽象类要被子类继承，接口要被类实现。\n3、接口只能做方法申明，抽象类中可以做方法申明，也可以做方法实现\n4、接口里定义的变量只能是公共的静态的常量，抽象类中的变量是普通变量。\n5、抽象类里的抽象方法必须全部被子类所实现，如果子类不能全部实现父类抽象方法，那么该子类只能是抽象类。同样，一个实现接口的时候，如不能全部实现接口方法，那么该类也只能为抽象类。\n6、抽象方法只能申明，不能实现。abstract void abc();不能写成abstract void abc(){}。\n7、抽象类里可以没有抽象方法\n8、如果一个类里有抽象方法，那么这个类只能是抽象类\n9、抽象方法要被实现，所以不能是静态的，也不能是私有的。\n10、接口可继承接口，并可多继承接口，但类只能单根继承。', 'JAVA', '2020-04-29 15:35:18', '', '抽象类和接口的区别，类可以继承多个类么，接口可以继承多个接口么,类可以实现多个接口么');
INSERT INTO `t_question` VALUES ('242811008219873280', 'IO(BIO)是面向流的，NIO是面向缓冲区的\nBIO：Block IO 同步阻塞式 IO，就是我们平常使用的传统 IO，它的特点是模式简单使用方便，并发处理能力低。\nNIO：New IO 同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。\nAIO：Asynchronous IO 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制。', 'JAVA', '2020-04-29 15:41:19', 'https://www.jianshu.com/p/3f703d3d804c||https://zhuanlan.zhihu.com/p/23488863', '讲讲你理解的 nio和 bio 的区别是啥，谈谈 reactor 模型');
INSERT INTO `t_question` VALUES ('242812942993588224', '1:\n    new Test();//直接初始化\n2:\n   Class.forName(\"com.Test\");//通过包名地址\n3:\n    Test.class.newInstance();//', 'JAVA', '2020-04-29 15:49:00', 'https://blog.csdn.net/yongjian1092/article/details/7364451||https://www.jianshu.com/p/3ea4a6b57f87?amp||https://blog.csdn.net/LianXu3344/article/details/82906201', '反射的原理，反射创建类实例的三种方式是什么');
INSERT INTO `t_question` VALUES ('242814231248896000', 'Class.forName(className)方法，其实调用的方法是Class.forName(className,true,classloader);注意看第2个boolean参数，它表示的意思，在loadClass后必须初始化。比较下我们前面准备jvm加载类的知识，我们可以清晰的看到在执行过此方法后，目标对象的 static块代码已经被执行，static参数也已经被初始化。\n\n再看ClassLoader.loadClass(className)方法，其实他调用的方法是ClassLoader.loadClass(className,false);还是注意看第2个 boolean参数，该参数表示目标对象被装载后不进行链接，这就意味这不会去执行该类静态块中间的内容。因此2者的区别就显而易见了。', 'JAVA', '2020-04-29 15:54:07', 'https://my.oschina.net/gpzhang/blog/486743', '反射中，Class.forName 和 ClassLoader 区别');
INSERT INTO `t_question` VALUES ('242814485356609536', 'jdk动态代理是由java内部的反射机制来实现的，cglib动态代理底层则是借助asm来实现的', 'JAVA', '2020-04-29 15:55:08', 'https://blog.csdn.net/HEYUTAO007/article/details/49738887', 'Java动态代理的两种实现方法');
INSERT INTO `t_question` VALUES ('242815963714551808', '1:懒汉模式\npublic class LHan(){\n    private static LHan instance;\n    private LHan(){};\n    public LHan getInstance(){\n        if(instance == null){\n             instance = new LHan();\n        }\n        return instance;\n    }\n}\n2:饿汉模式\nclass EHan {\n    private static  EHan instance = new EHan();\n    private EHan(){       \n    }\n    public static EHan getInstance() {\n        return instance;\n    }\n}\n3.双重锁模式\nclass DBLock {\n    private volatile static DBLock instace;\n    private DBLock() {}\n    public static DBLock getInstace() {\n        if (instace == null) {\n            synchronized (DBLock.class) {\n                if (instace == null) {\n                    instace = new DBLock();\n                }\n            }\n        }\n        return instace;\n    }\n}', 'JAVA', '2020-04-29 16:07:38', 'https://www.jianshu.com/p/3bfd916f2bb2', '写出三种单例模式实现');
INSERT INTO `t_question` VALUES ('242901052146319360', 'public： Java语言中访问限制最宽的修饰符，一般称之为“公共的”。被其修饰的类、属性以及方法不仅可以跨类访问，而且允许跨包（package）访问。\nprivate: Java语言中对访问权限限制的最窄的修饰符，一般称之为“私有的”。被其修饰的类、属性以及方法只能被该类的对象访问，其子类不能访问，更不能允许跨包访问。\nprotect: 介于public 和 private 之间的一种访问修饰符，一般称之为“保护形”。被其修饰的类、属性以及方法只能被类本身的方法及子类访问，即使子类在不同的包中也可以访问。\ndefault：即不加任何访问修饰符，通常称为\"默认访问模式\"。该模式下，只允许在同一个包中进行访问。', 'JAVA', '2020-04-29 21:39:07', '', 'public、private、protected、default作用域');
INSERT INTO `t_question` VALUES ('242901760065142784', '深拷贝和浅拷贝是只针对Object和Array这样的引用数据类型的\n\n浅拷贝只复制指向某个对象的指针，而不复制对象本身，新旧对象还是共享同一块内存。但深拷贝会另外创造一个一模一样的对象，新对象跟原对象不共享内存，修改新对象不会改到原对象', 'JAVA', '2020-04-29 21:41:56', 'https://segmentfault.com/a/1190000018874254', '深拷贝和浅拷贝区别');
INSERT INTO `t_question` VALUES ('242902386178260992', 'java.lang.Throwable是所有异常的根\n\njava.lang.Error是错误信息\n\njava.lang.Exception是异常信息', 'JAVA', '2020-04-29 21:44:25', 'https://blog.csdn.net/woshixuye/article/details/8230407', 'error 和 exception 的区别，CheckedException，RuntimeException 的区别');
INSERT INTO `t_question` VALUES ('242905725620191232', 'NullPointerException\nIndecOutOfBoundException\nClassCastException\nAricthmeticException\nIllegalArgumentException', 'JAVA', '2020-04-29 21:57:41', '', '请列出 5 个运行时异常');
