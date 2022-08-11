/*
 Navicat Premium Data Transfer

 Source Server         : 124.70.13.254
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : 124.70.13.254:3306
 Source Schema         : sincolink_v1.1

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 20/06/2022 15:48:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_staff_salary
-- ----------------------------
DROP TABLE IF EXISTS `bus_staff_salary`;
CREATE TABLE `bus_staff_salary` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `id` varchar(32) NOT NULL,
  `staff_id` varchar(32) DEFAULT NULL COMMENT '员工id',
  `salary` varchar(50) DEFAULT NULL COMMENT '基本工资',
  `post_wage` decimal(20,2) DEFAULT NULL COMMENT '岗位工资',
  `merit_pay` decimal(20,2) DEFAULT NULL COMMENT '绩效工资',
  `meal_subsidy` decimal(20,2) DEFAULT NULL COMMENT '餐补',
  `other` decimal(20,2) DEFAULT NULL COMMENT '其他',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_dept_id` bigint(20) DEFAULT NULL,
  `create_dept_path` varchar(100) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_dept_id` bigint(20) DEFAULT NULL,
  `update_dept_path` varchar(100) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT NULL,
  `seniority_pay` decimal(20,2) DEFAULT NULL COMMENT '工龄工资',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='信科联员工-薪资构成';

-- ----------------------------
-- Records of bus_staff_salary
-- ----------------------------
BEGIN;
INSERT INTO `bus_staff_salary` VALUES (377, '3d7315674c559cbd1f1c008208ea14f3', '3bcd8f022f33382eb58ddb76fdd147f0', '111', 111.00, 11.00, 1.00, 1.00, '15810215782', '2022-06-15 11:29:10', 209, '0,100,206,209', NULL, NULL, NULL, NULL, '0', 1.00);
INSERT INTO `bus_staff_salary` VALUES (380, '4fb9cf9e625bc262c500e68cb55021da', '4d7d7ea10d0ab691594f0432a86315a2', '2000', 0.00, 1000.00, 0.00, 0.00, 'admin', '2022-06-15 15:37:44', 100, '0,100', '15810215782', '2022-06-16 18:03:27', 209, '0,100,206,209', '0', 2.00);
INSERT INTO `bus_staff_salary` VALUES (380, '5f333cf4f915614b33922194532be11a', '4d7d7ea10d0ab691594f0432a86315a2', '2000', 0.00, 1000.00, 0.00, 0.00, 'admin', '2022-06-15 15:36:30', 100, '0,100', '15810215782', '2022-06-16 18:03:27', 209, '0,100,206,209', '0', 3.00);
INSERT INTO `bus_staff_salary` VALUES (381, '6e46860b30c8353e79f87a9cb754f56f', '0d6b936502726084dfe304459fb1aef1', '1000', 0.00, 1000.00, 1.00, 1.00, 'admin', '2022-06-16 11:16:55', 100, '0,100', 'admin', '2022-06-16 18:07:43', 100, '0,100', '0', NULL);
INSERT INTO `bus_staff_salary` VALUES (380, '7f248dba691cd9a3b94302dd164820c0', '4d7d7ea10d0ab691594f0432a86315a2', '2000', 0.00, 1000.00, 0.00, 0.00, 'admin', '2022-06-15 14:16:29', 100, '0,100', '15810215782', '2022-06-16 18:03:27', 209, '0,100,206,209', '0', 4.00);
INSERT INTO `bus_staff_salary` VALUES (379, 'b16d40855a06ef699c144728e6e380ba', '178c24d2ba01bd7c89ce559c2888d132', '32432', 432423.00, 324324.00, 3243243.00, 43242.00, '15810215782', '2022-06-15 14:05:43', 209, '0,100,206,209', NULL, NULL, NULL, NULL, '0', 5.00);
INSERT INTO `bus_staff_salary` VALUES (378, 'd417af80345598ab391ef5909e2202ce', 'a37c62519e9f1ceade3f895870889930', '111111', 111111.00, 111111.00, 343.00, 43.00, '15810215782', '2022-06-15 13:55:43', 209, '0,100,206,209', '15810215782', '2022-06-16 16:31:36', 209, '0,100,206,209', '0', 6.00);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
