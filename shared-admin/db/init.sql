
CREATE DATABASE grc_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
Use grc_admin;
/*
 Navicat Premium Data Transfer

 Source Server         : grc-platform
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : ag_admin

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/25/2017 19:38:40 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_resource
-- ----------------------------
DROP TABLE IF EXISTS `base_resource`;
CREATE TABLE `base_resource` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_resource
-- ----------------------------
BEGIN;
INSERT INTO `base_resource` VALUES ('3', 'baseUserManager:btn_add', 'button', '新增', '/back/sys/baseUser', '1', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('4', 'baseUserManager:btn_edit', 'button', '编辑', '/back/sys/baseUser', '1', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('5', 'baseUserManager:btn_del	', 'button', '删除', '/back/sys/baseUser', '1', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('9', 'baseMenuManager:resource', 'uri', '按钮页面', '/admin/sys/baseResource', '6', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('10', 'baseMenuManager:btn_add', 'button', '新增', '/back/sys/baseMenu', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('11', 'baseMenuManager:btn_edit', 'button', '编辑', '/back/sys/baseMenu', '6', '', '', 'PUT', '', '2017-06-24 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_resource` VALUES ('12', 'baseMenuManager:btn_del	', 'button', '删除', '/back/sys/baseMenu', '6', '', '', 'DELETE', '', '2017-06-24 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_resource` VALUES ('13', 'baseMenuManager:btn_baseResource_add', 'button', '新增元素', '/back/sys/baseResource', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('14', 'baseMenuManager:btn_baseRresource_edit', 'button', '编辑元素', '/back/sys/baseResource', '6', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('15', 'btn_baseResource_del', 'button', '删除元素', '/back/sys/baseResource', '6', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('16', 'baseGroupManager:btn_add', 'button', '新增', '/back/sys/baseGroup', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('17', 'baseGroupManager:btn_edit', 'button', '编辑', '/back/sys/baseGroup', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('18', 'baseGroupManager:btn_del', 'button', '删除', '/back/sys/baseGroup', '7', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('19', 'baseGroupManager:btn_baseUserManager', 'button', '分配用户', '/back/sys/baseGroup/{*}/user', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('20', 'baseGroupManager:btn_baseResourceManager', 'button', '分配权限', '/back/sys/baseGroup/{*}/authority', '7', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('21', 'baseGroupManager:menu', 'uri', '分配菜单', '/back/sys/baseGroup/{*}/authority/menu', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('22', 'baseGroupManager:resource', 'uri', '分配资源', '/back/sys/baseGroup/{*}/authority/resource', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('23', 'baseUserManager:view	', 'uri', '查看', '/back/sys/baseUser', '1', '', '', 'GET', '', '2017-06-26 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_resource` VALUES ('24', 'baseMenuManager:view', 'uri', '查看', '/back/sys/baseMenu', '6', '', '', 'GET', '', '2017-06-26 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_resource` VALUES ('27', 'baseMenuManager:baseResource_view', 'uri', '查看', '/admin/sys/baseResource', '6', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('28', 'baseGroupManager:view', 'uri', '查看', '/back/sys/baseGroup', '7', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('30', 'adminAPI:view', 'uri', '查看', '/back/swagger', '23', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('31', 'adminAPI:swagger', 'uri', '查看', '/back/v2', '23', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('32', 'baseGroupTypeManager:view', 'uri', '查看', '/back/sys/baseGroupType', '8', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('33', 'baseGroupTypeManager:btn_add', 'button', '新增', '/back/sys/baseGroupType', '8', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('34', 'baseGroupTypeManager:btn_edit', 'button', '编辑', '/back/sys/baseGroupType', '8', null, null, 'PUT', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('35', 'baseGroupTypeManager:btn_del', 'button', '删除', '/back/sys/baseGroupType', '8', null, null, 'DELETE', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('40', 'adminAPI:swagger_resources', 'uri', '查看', '/back/swagger-resources', '23', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('41', 'gateLogManager:view', 'button', '查看', '/back/sys/gateLog', '27', null, null, 'GET', '', '2017-07-01 00:00:00', '1', 'admin', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
INSERT INTO `base_resource` VALUES ('47', 'api:test', 'uri', '测试api权限', '/api/testapigatesecurity/username/{*}', '-1', null, null, 'GET', '', '2017-08-30 17:39:06', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_group
-- ----------------------------
DROP TABLE IF EXISTS `base_group`;
CREATE TABLE `base_group` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` BIGINT(20) NOT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `group_type` BIGINT(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group
-- ----------------------------
BEGIN;
INSERT INTO `base_group` VALUES ('1', 'adminRole', '管理员', '-1', '/adminRole', null, '1', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('3', 'testGroup', '体验组', '-1', '/testGroup', null, '1', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('4', 'visitorRole', '游客', '3', '/testGroup/visitorRole', null, '1', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('6', 'company', 'AG集团', '-1', '/company', null, '2', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('7', 'financeDepart', '财务部', '6', '/company/financeDepart', null, '2', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('8', 'hrDepart', '人力资源部', '6', '/company/hrDepart', null, '2', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('9', 'blogAdmin', '博客管理员', '-1', '/blogAdmin', null, '1', '', '2017-07-16 16:59:30', '1', 'Mr.AG', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_group_leader
-- ----------------------------
DROP TABLE IF EXISTS `base_group_leader`;
CREATE TABLE `base_group_leader` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group_leader
-- ----------------------------
BEGIN;
INSERT INTO `base_group_leader` VALUES ('4', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('6', '9', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_group_member
-- ----------------------------
DROP TABLE IF EXISTS `base_group_member`;
CREATE TABLE `base_group_member` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group_member
-- ----------------------------
BEGIN;
INSERT INTO `base_group_member` VALUES ('2', '4', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_member` VALUES ('6', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_member` VALUES ('7', '1', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_member` VALUES ('9', '9', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_group_type
-- ----------------------------
DROP TABLE IF EXISTS `base_group_type`;
CREATE TABLE `base_group_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group_type
-- ----------------------------
BEGIN;
INSERT INTO `base_group_type` VALUES ('1', null, '角色类型', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_type` VALUES ('2', 'depart', '部门类型', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_type` VALUES ('3', null, '自定义类型', '123', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `parent_id` BIGINT(20) NOT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `order_num` BIGINT(20) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
BEGIN;
INSERT INTO `base_menu` VALUES ('1', 'baseUserManager', '用户管理', '5', '/admin/sys/baseUser', 'fa fa-user', null, '0', '', '/adminSys/baseManager/baseUserManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('5', 'baseManager', '基础配置管理', '13', '', 'fa fa-cog fa-spin', null, '0', '用户', '/adminSys/baseManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('6', 'baseMenuManager', '菜单管理', '5', '/admin/sys/baseMenu', 'fa fa-list', null, '0', '', '/adminSys/baseManager/baseMenuManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('7', 'baseGroupManager', '角色组管理', '5', '/admin/sys/baseGroup', 'fa fa-users', null, '0', '', '/adminSys/baseManager/baseGroupManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('8', 'baseGroupTypeManager', '角色类型管理', '5', '/admin/sys/baseGroupType', 'fa fa-address-card-o', null, '0', '', '/adminSys/baseManager/baseGroupTypeManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('9', 'monitorManager', '系统监控', '13', '', 'fa fa-area-chart', null, '0', '', '/adminSys/monitorManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('10', 'bootMonitor', 'Spring-Boot监控', '9', 'http://localhost:8764', 'fa fa-line-chart', null, '0', '', '/adminSys/monitorManager/bootMonitor', null, null, null, null, null, '2017-07-25 19:38:11', '1', 'Mr.AG', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('11', 'hystrixMonitor', 'Hystrix监控', '9', 'http://localhost:8764/hystrix', 'fa fa-bar-chart', null, '0', '', '/adminSys/monitorManager/hystrixMonitor', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('13', 'adminSys', '权限管理系统', '-1', '', 'fa fa-terminal', null, '0', '', '/adminSys', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('21', 'dictManager', '数据字典', '5', '', 'fa fa-book', null, '0', '', '/adminSys/baseManager/dictManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('22', 'apiManager', '服务端api文档', '13', '', 'fa fa-folder', null, '0', '', '/adminSys/apiManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('23', 'adminAPI', 'Admin Rest API', '22', '/back/swagger-ui.html', 'fa fa-file-code-o', null, '0', '', '/adminSys/apiManager/adminAPI', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('24', 'druidMonitor', 'Admin Druid数据监控', '9', '/back/druid/datasource.html', 'fa fa-line-chart', null, '0', '', '/adminSys/monitorManager/druidMonitor', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('25', 'gateWayManager', '网关管理', '13', '', 'fa fa-bug', null, '0', '', '/adminSys/gateWayManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('26', 'gateClientManager', '客户端管理', '25', '/admin/sys/gateClient', 'fa fa-laptop', null, '0', '', '/adminSys/gateWayManager/gataClientManager', null, null, null, null, null, '2017-08-30 14:32:41', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('27', 'gateLogManager', '操作日志', '5', '/admin/sys/gateLog', 'fa fa-book', null, '0', '', '/adminSys/baseManager/gateLogManager', null, '2017-07-01 00:00:00', '1', 'admin', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('28', 'gateServiceManger', '服务注册', '25', '/admin/sys/gateService', 'fa fa-exchange', null, '0', '', '/adminSys/gateWayManager/gateServiceManger', null, '2017-07-03 20:43:01', '1', '¡X', '0:0:0:0:0:0:0:1', '2017-09-01 12:39:52', '1', 'Mr.AG', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_resource_authority
-- ----------------------------
DROP TABLE IF EXISTS `base_resource_authority`;
CREATE TABLE `base_resource_authority` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `authority_id` varchar(255) DEFAULT NULL,
  `authority_type` varchar(255) DEFAULT NULL,
  `resource_id` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=800 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_resource_authority
-- ----------------------------
BEGIN;
INSERT INTO `base_resource_authority` VALUES ('285', '1', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('286', '1', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('287', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('288', '1', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('289', '1', 'group', '10', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('290', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('291', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('292', '1', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('293', '1', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('294', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('295', '1', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('296', '1', 'group', '10', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('297', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('298', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('299', '1', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('300', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('301', '1', 'group', '10', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('302', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('303', '1', 'group', '13', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('304', '1', 'group', '14', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('305', '1', 'group', '15', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('306', '1', 'group', '10', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('307', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('308', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('309', '1', 'group', '13', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('310', '1', 'group', '14', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('311', '1', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('312', '1', 'group', '15', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('313', '1', 'group', '16', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('314', '1', 'group', '17', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('315', '1', 'group', '18', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('316', '1', 'group', '19', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('317', '1', 'group', '20', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('318', '1', 'group', '21', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('319', '1', 'group', '22', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('349', '4', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('371', '1', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('372', '1', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('373', '1', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('374', '1', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('375', '1', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('376', '1', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('377', '1', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('378', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('379', '1', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('380', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('381', '1', 'group', '14', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('382', '1', 'group', '13', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('383', '1', 'group', '15', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('384', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('385', '1', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('386', '1', 'group', '10', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('387', '1', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('388', '1', 'group', '16', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('389', '1', 'group', '18', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('390', '1', 'group', '17', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('391', '1', 'group', '19', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('392', '1', 'group', '20', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('393', '1', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('394', '1', 'group', '22', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('395', '1', 'group', '21', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('396', '4', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('397', '4', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('398', '4', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('399', '4', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('400', '4', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('401', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('402', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('403', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('421', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('422', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('423', '4', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('424', '4', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('436', '1', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('437', '1', 'group', '33', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('438', '1', 'group', '34', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('439', '1', 'group', '35', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('440', '4', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('464', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('465', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('466', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('467', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('468', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('469', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('470', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('471', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('472', '1', 'group', '40', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('492', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('493', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('494', '1', 'group', '40', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('516', '4', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('517', '4', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('518', '4', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('519', '4', 'group', '40', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('611', '4', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('612', '4', 'group', '36', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('666', '1', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('689', '1', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('690', '1', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('691', '1', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('692', '1', 'group', '45', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('710', '9', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('711', '9', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('712', '9', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('713', '9', 'group', '45', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('714', '9', 'group', '14', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('715', '9', 'group', '20', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('716', '9', 'group', '17', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('717', '9', 'group', '18', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('718', '9', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('719', '9', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('720', '9', 'group', '45', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('721', '9', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('725', '4', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('726', '4', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('727', '4', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('728', '4', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('729', '4', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('730', '4', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('731', '4', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('732', '4', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('733', '4', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('734', '4', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('735', '4', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('736', '4', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('737', '4', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('738', '4', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('739', '4', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('740', '4', 'group', '13', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('741', '4', 'group', '5', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('742', '4', 'group', '1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('743', '4', 'group', '27', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('744', '4', 'group', '9', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('745', '4', 'group', '24', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('746', '4', 'group', '22', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('747', '4', 'group', '23', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('748', '1', 'group', '16', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('749', '1', 'group', '17', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('750', '1', 'group', '18', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('751', '1', 'group', '20', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('752', '1', 'group', '21', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('753', '1', 'group', '19', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('754', '1', 'group', '22', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('755', '1', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('756', '1', 'group', '34', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('757', '1', 'group', '33', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('758', '1', 'group', '35', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('759', '1', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('760', '1', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('780', '1', 'group', '13', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('781', '1', 'group', '5', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('782', '1', 'group', '1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('783', '1', 'group', '6', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('784', '1', 'group', '7', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('785', '1', 'group', '8', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('786', '1', 'group', '27', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('787', '1', 'group', '9', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('788', '1', 'group', '10', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('789', '1', 'group', '11', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('790', '1', 'group', '24', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('791', '1', 'group', '22', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('792', '1', 'group', '23', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('793', '1', 'group', '25', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('794', '1', 'group', '26', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('795', '1', 'group', '28', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('796', '1', 'group', '14', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('797', '1', 'group', '20', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('798', '1', 'group', '17', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('799', '1', 'group', '18', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_user
-- ----------------------------
BEGIN;
INSERT INTO `base_user` VALUES ('1', 'admin', '$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie', 'Mr.AG', '', null, '', null, '', '男', null, null, '', null, null, null, null, '2017-07-25 14:54:21', '1', 'Mr.AG', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
INSERT INTO `base_user` VALUES ('2', 'test', '$2a$12$zWe6knO6rGp15UVfdWTTxu.Ykt.k3QnD5FPoj6a1cnL63csHY2A1S', '测试账户', '', null, '', null, '', '男', null, null, '', null, null, null, null, '2017-07-15 19:18:07', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
INSERT INTO `base_user` VALUES ('4', 'blog', '$2a$12$rZ5JwfciD6bcparL6NgsSeq1BcGwUyhgrW3DrliW.6aOv8oIJw4MW', 'Mr.AG', '', null, '', null, '', '男', null, null, '', null, null, null, null, '2017-09-01 12:36:05', '1', 'Mr.AG', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for gate_client
-- ----------------------------
DROP TABLE IF EXISTS `gate_client`;
CREATE TABLE `gate_client` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `locked` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_client
-- ----------------------------
BEGIN;
INSERT INTO `gate_client` VALUES ('5', 'admin-ui', '$2a$12$nxviSEyCHPwQyy.OGdCZLuRvj2kIHESy0bONzEQb5YBwyZKtOS/nK', 'admin-ui', '0', '', '2017-08-30 17:37:56', 'null', 'null', 'null', null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
-- ----------------------------
-- Table structure for base_form
-- ----------------------------
DROP TABLE IF EXISTS `base_form`;
CREATE TABLE `base_form` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `form_name` varchar(255) NOT NULL DEFAULT '' COMMENT '表单名称',
  `form_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '表单描述',
  `content` text DEFAULT NULL COMMENT '表单原html模板未经处理的',
  `content_parse` text DEFAULT NULL COMMENT '表单替换的模板 经过处理',
  `content_data` text DEFAULT NULL COMMENT '表单中的字段数据',
  `fields` smallint(5) unsigned DEFAULT NULL DEFAULT '0' COMMENT '字段总数',
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gate_log
-- ----------------------------
DROP TABLE IF EXISTS `gate_log`;
CREATE TABLE `gate_log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `menu` varchar(255) DEFAULT NULL,
  `opt` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gate_client_service
-- ----------------------------
DROP TABLE IF EXISTS `gate_client_service`;
CREATE TABLE `gate_client_service` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_client_service
-- ----------------------------
BEGIN;
INSERT INTO `gate_client_service` VALUES ('21', '46', '4', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('22', '46', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('23', '47', '5', null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;
SET FOREIGN_KEY_CHECKS = 1;

