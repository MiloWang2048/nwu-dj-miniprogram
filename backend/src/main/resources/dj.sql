/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : dj

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 29/08/2020 16:41:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `cst_create` datetime(0) NOT NULL,
  `cst_modified` datetime(0) NOT NULL,
  `user_id` int(11) NOT NULL,
  `stu_serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exchange_record
-- ----------------------------
DROP TABLE IF EXISTS `exchange_record`;
CREATE TABLE `exchange_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `cst_create` datetime(0) NOT NULL,
  `cst_modified` datetime(0) NOT NULL,
  `job_id` int(10) UNSIGNED NOT NULL,
  `original_user_id` int(10) UNSIGNED NOT NULL,
  `target_user_id` int(10) UNSIGNED NOT NULL,
  `is_accepted` tinyint(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `cst_create` datetime(0) NOT NULL,
  `cst_modified` datetime(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  `max_job` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_record
-- ----------------------------
DROP TABLE IF EXISTS `job_record`;
CREATE TABLE `job_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `cst_create` datetime(0) NOT NULL,
  `cst_modified` datetime(0) NOT NULL,
  `job_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `is_present` tinyint(1) UNSIGNED NOT NULL,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `cst_create` datetime(0) NOT NULL,
  `cst_modified` datetime(0) NOT NULL,
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
