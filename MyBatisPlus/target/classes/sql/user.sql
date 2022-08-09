/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : mybatisplus

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 16/06/2022 20:22:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'zhangsan', 97, '120@qq.com', '2022-04-26 19:03:53', '2022-04-27 16:08:28', 1);
INSERT INTO `user` VALUES (2, 'Sandy', 21, 'test4@baomidou.com', NULL, '2022-04-27 16:07:42', 0);
INSERT INTO `user` VALUES (3, 'zhangsa4444n', 9447, '120444@qq.com', NULL, '2022-06-16 20:20:21', 0);
INSERT INTO `user` VALUES (4, NULL, NULL, NULL, NULL, '2022-06-16 20:20:25', 0);
INSERT INTO `user` VALUES (1518890547286216705, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1518890689187840001, '雨', 25, 'dsadsadsa', NULL, '2022-04-27 16:02:08', 0);
INSERT INTO `user` VALUES (1518902129558863873, 'shuishui', 98, '120@qq.com', NULL, '2022-04-27 15:29:11', 0);
INSERT INTO `user` VALUES (1519209566006509570, 'shui', 29, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1519209566123950082, 'shui2', 33, NULL, NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
