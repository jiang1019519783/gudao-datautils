/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : db_ggzl

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-06-07 18:30:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_aty_corp
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_corp`;
CREATE TABLE `t_aty_corp` (
  `C_ID` varchar(50) NOT NULL COMMENT '标识',
  `C_Name` varchar(300) NOT NULL COMMENT '单位名称',
  `C_PID` varchar(300) DEFAULT NULL COMMENT '父代码',
  `N_Level` tinyint(4) NOT NULL DEFAULT '4' COMMENT '单位层级',
  `C_GBM` varchar(300) DEFAULT NULL COMMENT '国标码',
  `C_Alias` varchar(300) DEFAULT NULL COMMENT '简称',
  `N_Valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `N_Order` smallint(6) NOT NULL DEFAULT '1' COMMENT '显示顺序',
  `dt_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dt_updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位表';

-- ----------------------------
-- Records of t_aty_corp
-- ----------------------------
INSERT INTO `t_aty_corp` VALUES ('1000000', '青海省', null, '1', '', null, '1', '1', '2018-06-02 12:08:33', '2018-06-02 12:08:30');

-- ----------------------------
-- Table structure for t_aty_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_dept`;
CREATE TABLE `t_aty_dept` (
  `C_ID` varchar(50) NOT NULL COMMENT '标识',
  `C_Name` varchar(300) NOT NULL COMMENT '部门名称',
  `C_PID` varchar(300) DEFAULT NULL COMMENT '父代码',
  `C_CORP` varchar(300) DEFAULT NULL COMMENT '所属单位',
  `C_Alias` varchar(300) DEFAULT NULL COMMENT '简称',
  `N_Valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `N_Order` smallint(6) NOT NULL DEFAULT '1' COMMENT '显示顺序',
  `dt_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dt_updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_aty_dept
-- ----------------------------

-- ----------------------------
-- Table structure for t_aty_right
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_right`;
CREATE TABLE `t_aty_right` (
  `C_RightKey` varchar(150) NOT NULL COMMENT '权限字',
  `C_Name` varchar(300) NOT NULL COMMENT '权限名称',
  `C_Descript` varchar(300) DEFAULT NULL COMMENT '描述',
  `N_Order` int(11) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`C_RightKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_aty_right
-- ----------------------------
INSERT INTO `t_aty_right` VALUES ('artery', '系统权限', '1', null);
INSERT INTO `t_aty_right` VALUES ('artery.console', '控制台管理', '2', null);

-- ----------------------------
-- Table structure for t_aty_role
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_role`;
CREATE TABLE `t_aty_role` (
  `C_ID` varchar(50) NOT NULL COMMENT 'ID',
  `C_Name` varchar(300) NOT NULL COMMENT '角色名称',
  `C_Descript` varchar(300) DEFAULT NULL COMMENT '描述',
  `N_XTGY` tinyint(4) DEFAULT '2' COMMENT '系统固有',
  `N_Valid` tinyint(4) DEFAULT '1' COMMENT '是否有效',
  `N_Order` smallint(6) DEFAULT '1' COMMENT '显示顺序',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_aty_role
-- ----------------------------
INSERT INTO `t_aty_role` VALUES ('role1', '安全管理员', null, '2', '1', '1');
INSERT INTO `t_aty_role` VALUES ('role2', '审计管理员', null, '2', '1', '1');

-- ----------------------------
-- Table structure for t_aty_role_right
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_role_right`;
CREATE TABLE `t_aty_role_right` (
  `C_ID` varchar(32) NOT NULL COMMENT 'ID',
  `C_RoleID` varchar(300) NOT NULL COMMENT '角色ID',
  `C_RightKey` varchar(300) NOT NULL COMMENT '权限字',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关系表';

-- ----------------------------
-- Records of t_aty_role_right
-- ----------------------------
INSERT INTO `t_aty_role_right` VALUES ('111', 'role1', 'artery.console');

-- ----------------------------
-- Table structure for t_aty_user
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_user`;
CREATE TABLE `t_aty_user` (
  `C_ID` varchar(50) NOT NULL COMMENT '帐号ID',
  `C_LoginID` varchar(300) NOT NULL COMMENT '登录标识',
  `C_Name` varchar(300) NOT NULL COMMENT '姓名',
  `C_Password` varchar(32) NOT NULL DEFAULT 'D41D8CD98F00B204E9800998ECF8427E' COMMENT '密码',
  `C_Mail` varchar(300) DEFAULT NULL COMMENT '电子邮件',
  `C_IP` varchar(300) DEFAULT NULL COMMENT 'IP',
  `C_XMJP` varchar(300) DEFAULT NULL COMMENT '姓名简拼',
  `C_CORP` varchar(300) DEFAULT NULL COMMENT '所在单位',
  `C_DEPT` varchar(300) DEFAULT NULL COMMENT '所在部门',
  `N_Valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `N_Order` smallint(6) NOT NULL DEFAULT '1' COMMENT '显示顺序',
  `dt_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dt_createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_aty_user
-- ----------------------------
INSERT INTO `t_aty_user` VALUES ('-1', 'admin', '超级管理员', 'e10adc3949ba59abbe56e057f20f883e', null, null, 'admin', null, null, '1', '1', '2018-06-04 21:49:48', '2018-06-02 12:08:49');

-- ----------------------------
-- Table structure for t_aty_user_right
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_user_right`;
CREATE TABLE `t_aty_user_right` (
  `C_ID` varchar(32) NOT NULL COMMENT 'ID',
  `C_UserID` varchar(300) NOT NULL COMMENT '帐号ID',
  `N_Type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型',
  `C_RightKey` varchar(300) DEFAULT NULL COMMENT '权限字',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帐号-权限关系表';

-- ----------------------------
-- Records of t_aty_user_right
-- ----------------------------
INSERT INTO `t_aty_user_right` VALUES ('111', '-1', '1', 'artery');

-- ----------------------------
-- Table structure for t_aty_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_aty_user_role`;
CREATE TABLE `t_aty_user_role` (
  `C_ID` varchar(32) NOT NULL,
  `C_UserID` varchar(32) DEFAULT NULL,
  `C_RoleID` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_aty_user_role
-- ----------------------------
INSERT INTO `t_aty_user_role` VALUES ('111', '-1', 'role1');
INSERT INTO `t_aty_user_role` VALUES ('222', '-1', 'role2');
