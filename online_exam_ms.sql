/*
Navicat MySQL Data Transfer

Source Server         : 58.87.113.82
Source Server Version : 50173
Source Host           : 58.87.113.82:3306
Source Database       : online_exam_ms

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-06-08 21:24:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_exam
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam`;
CREATE TABLE `tb_exam` (
  `e_id` int(11) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(16) DEFAULT NULL,
  `tea_id` int(11) NOT NULL DEFAULT '0',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `paper_path` varchar(100) DEFAULT NULL,
  `paper_anwser_path` varchar(100) DEFAULT '',
  `status` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam
-- ----------------------------
INSERT INTO `tb_exam` VALUES ('1', '数据结构', '2', '2019-04-13 08:00:00', '2019-04-13 10:00:00', 'paper\\1_2_程胜_1_数据结构\\', 'anwser\\1_2_程胜_1_数据结构\\', 'complete');
INSERT INTO `tb_exam` VALUES ('3', '离散数学', '2', '2019-06-07 20:30:00', '2019-06-07 22:00:00', 'paper\\3_2_程胜_1_离散数学\\', 'anwser\\3_2_程胜_1_离散数学\\', 'complete');
INSERT INTO `tb_exam` VALUES ('5', 'kaoshi', '2', '2019-04-13 08:00:00', '2019-04-13 10:00:00', 'paper\\5_2_程胜_1_kaoshi\\', 'anwser\\5_2_程胜_1_kaoshi\\', 'complete');
INSERT INTO `tb_exam` VALUES ('6', '不是的呀', '2', '2019-04-13 08:00:00', '2019-04-13 10:00:00', null, null, 'complete');
INSERT INTO `tb_exam` VALUES ('7', '真·听龚少讲道理', '2', '2019-06-13 08:00:00', '2019-06-13 10:00:00', 'paper\\7_2_程胜_2_真·听龚少讲道理\\', 'anwser\\7_2_程胜_2_真·听龚少讲道理\\', 'ready');
INSERT INTO `tb_exam` VALUES ('9', '语文', '3', '2019-06-08 20:00:00', '2019-06-08 22:00:00', 'paper\\9_3_张一山_1_语文\\', 'anwser\\9_3_张一山_1_语文\\', 'running');

-- ----------------------------
-- Table structure for tb_exam_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_arrange`;
CREATE TABLE `tb_exam_arrange` (
  `arra_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL,
  `e_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`arra_id`),
  KEY `e_id` (`e_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam_arrange
-- ----------------------------
INSERT INTO `tb_exam_arrange` VALUES ('17', '9', '1');
INSERT INTO `tb_exam_arrange` VALUES ('18', '10', '1');
INSERT INTO `tb_exam_arrange` VALUES ('19', '11', '1');
INSERT INTO `tb_exam_arrange` VALUES ('20', '12', '1');
INSERT INTO `tb_exam_arrange` VALUES ('21', '13', '1');
INSERT INTO `tb_exam_arrange` VALUES ('22', '14', '1');
INSERT INTO `tb_exam_arrange` VALUES ('23', '15', '1');
INSERT INTO `tb_exam_arrange` VALUES ('24', '16', '1');
INSERT INTO `tb_exam_arrange` VALUES ('25', '9', '3');
INSERT INTO `tb_exam_arrange` VALUES ('26', '10', '3');
INSERT INTO `tb_exam_arrange` VALUES ('27', '11', '3');
INSERT INTO `tb_exam_arrange` VALUES ('28', '12', '3');
INSERT INTO `tb_exam_arrange` VALUES ('29', '13', '3');
INSERT INTO `tb_exam_arrange` VALUES ('30', '14', '3');
INSERT INTO `tb_exam_arrange` VALUES ('31', '15', '3');
INSERT INTO `tb_exam_arrange` VALUES ('32', '16', '3');
INSERT INTO `tb_exam_arrange` VALUES ('33', '9', '5');
INSERT INTO `tb_exam_arrange` VALUES ('34', '10', '5');
INSERT INTO `tb_exam_arrange` VALUES ('35', '11', '5');
INSERT INTO `tb_exam_arrange` VALUES ('36', '12', '5');
INSERT INTO `tb_exam_arrange` VALUES ('37', '13', '5');
INSERT INTO `tb_exam_arrange` VALUES ('38', '14', '5');
INSERT INTO `tb_exam_arrange` VALUES ('39', '15', '5');
INSERT INTO `tb_exam_arrange` VALUES ('40', '16', '5');
INSERT INTO `tb_exam_arrange` VALUES ('41', '41', '3');
INSERT INTO `tb_exam_arrange` VALUES ('43', '9', '9');
INSERT INTO `tb_exam_arrange` VALUES ('44', '10', '9');
INSERT INTO `tb_exam_arrange` VALUES ('45', '11', '9');
INSERT INTO `tb_exam_arrange` VALUES ('46', '12', '9');
INSERT INTO `tb_exam_arrange` VALUES ('47', '13', '9');
INSERT INTO `tb_exam_arrange` VALUES ('48', '14', '9');
INSERT INTO `tb_exam_arrange` VALUES ('49', '15', '9');
INSERT INTO `tb_exam_arrange` VALUES ('50', '16', '9');
INSERT INTO `tb_exam_arrange` VALUES ('51', '42', '9');
INSERT INTO `tb_exam_arrange` VALUES ('52', '42', '9');
INSERT INTO `tb_exam_arrange` VALUES ('53', '51', '9');
INSERT INTO `tb_exam_arrange` VALUES ('54', '54', '9');
INSERT INTO `tb_exam_arrange` VALUES ('55', '55', '9');

-- ----------------------------
-- Table structure for tb_exam_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_info`;
CREATE TABLE `tb_exam_info` (
  `in_id` int(11) NOT NULL AUTO_INCREMENT,
  `e_id` int(11) NOT NULL,
  `all_number` int(11) NOT NULL DEFAULT '0',
  `is_download` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  `info` text,
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam_info
-- ----------------------------
INSERT INTO `tb_exam_info` VALUES ('1', '1', '0', '0', null);
INSERT INTO `tb_exam_info` VALUES ('3', '3', '9', '0', null);
INSERT INTO `tb_exam_info` VALUES ('5', '5', '0', '0', null);
INSERT INTO `tb_exam_info` VALUES ('6', '6', '0', '0', null);
INSERT INTO `tb_exam_info` VALUES ('7', '7', '0', '0', null);
INSERT INTO `tb_exam_info` VALUES ('8', '9', '1', '0', null);

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `stu_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_number` varchar(16) DEFAULT NULL,
  `stu_name` varchar(8) DEFAULT NULL,
  `stu_password` varchar(16) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  `commitInfo` varchar(255) DEFAULT NULL,
  `stu_class` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('9', '1610130001', '衬衫2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('10', '1610130002', '大2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('11', '1610130003', '撒打算2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('12', '1610130004', '大萨达2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('13', '1610130005', '多大事2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('14', '1610130006', '很难接受2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('15', '1610130007', '节点2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('16', '1610130008', '发送2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('17', '1610130001', '衬衫2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('18', '1610130002', '大2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('19', '1610130003', '撒打算2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('20', '1610130004', '大萨达2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('21', '1610130005', '多大事2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('22', '1610130006', '很难接受2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('23', '1610130007', '节点2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('24', '1610130008', '发送2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('25', '1610130001', '衬衫2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('26', '1610130002', '大2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('27', '1610130003', '撒打算2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('28', '1610130004', '大萨达2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('29', '1610130005', '多大事2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('30', '1610130006', '很难接受2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('31', '1610130007', '节点2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('32', '1610130008', '发送2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('33', '1610130001', '衬衫2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('34', '1610130002', '大2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('35', '1610130003', '撒打算2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('36', '1610130004', '大萨达2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('37', '1610130005', '多大事2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('38', '1610130006', '很难接受2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('39', '1610130007', '节点2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('40', '1610130008', '发送2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('43', '1610130001', '衬衫2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('44', '1610130002', '大2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('45', '1610130003', '撒打算2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('46', '1610130004', '大萨达2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('47', '1610130005', '多大事2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('48', '1610130006', '很难接受2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('49', '1610130007', '节点2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('50', '1610130008', '发送2', null, null, null, '1');
INSERT INTO `tb_student` VALUES ('54', '1610120016', 'aaa', null, '127.0.0.1', null, '1');
INSERT INTO `tb_student` VALUES ('55', '1610120017', 'aaa', null, '127.0.0.1', null, '1');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `tea_id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_number` varchar(16) DEFAULT NULL,
  `tea_name` varchar(8) DEFAULT NULL,
  `tea_password` varchar(32) DEFAULT NULL,
  `is_admin` int(11) DEFAULT '0' COMMENT '0为教师，1为管理员',
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('2', '123', '程胜', '698d51a19d8a121ce581499d7b701668', '0');
INSERT INTO `tb_teacher` VALUES ('3', '124', '张一山', '698d51a19d8a121ce581499d7b701668', '0');
INSERT INTO `tb_teacher` VALUES ('4', '125', '李白', '698d51a19d8a121ce581499d7b701668', '0');
