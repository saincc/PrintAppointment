/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3308
 Source Schema         : print_appointment

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 14/01/2021 19:52:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `date` date NOT NULL,
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `needs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `other_needs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 1, 1, '2020-12-23', 'D:\\Code\\project\\PrintAppointment\\print\\src\\main\\resources\\files\\1-s2.0-S1877050917327849-main.pdf', '黑白单面:56, 黑白双面:12, 彩印单面:null, 彩印双面:null', 'unprint', '请帮我装订一下，谢谢');
INSERT INTO `orders` VALUES (2, 1, 1, 1, '2020-12-20', 'D:\\Code\\project\\PrintAppointment\\print\\src\\main\\resources\\files\\test.jpg', '黑白单面:10, 黑白双面:null, 彩印单面:null, 彩印双面:null', 'done', '无');

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (1, 2, '希望界面可以更美观一点');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '东华大学');
INSERT INTO `school` VALUES (2, '上海大学');

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `shopname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `support` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `credit` double NOT NULL,
  `sales` int(11) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES (1, 2, 1, '易打印', '123', '[单面, 黑白, 彩印]', 4, 1, '易打印pro');
INSERT INTO `shops` VALUES (2, 3, 1, '红房子打印店', '123', '[黑白, 单面, 彩印, 双面]', 5, 0, '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sain', '123456', 'user');
INSERT INTO `user` VALUES (2, 'yiprint', '123456', 'admin');
INSERT INTO `user` VALUES (3, 'redhouse', '123456', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
