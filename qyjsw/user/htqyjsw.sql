/*
Navicat MySQL Data Transfer

Source Server         : 192.168.18.128
Source Server Version : 50729
Source Host           : 192.168.18.128:3306
Source Database       : htqyjsw

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-04-14 19:59:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门内码',
  `dept_name` varchar(64) NOT NULL COMMENT '部门名称',
  `dept_introduce` varchar(100) DEFAULT NULL COMMENT '部门职能',
  `dept_add` varchar(100) NOT NULL COMMENT '办公地址',
  `dept_head` varchar(64) NOT NULL COMMENT '负责人',
  `dept_tel` varchar(20) NOT NULL COMMENT '联系电话',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '质检部', '测试产品质量', '漳州市', '小李', '18892446591');
INSERT INTO `t_dept` VALUES ('2', '大数据研发部', '开发航天大数据产品', '北京市西城区月坛北街国家医疗保障局', '何老师', '15101043442');
INSERT INTO `t_dept` VALUES ('3', '网络安全部', '网络信息安全检查', '北京市海淀区大黄庄', '赵云飞', '18792443591');

-- ----------------------------
-- Table structure for t_dept_user_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_dept_user_rel`;
CREATE TABLE `t_dept_user_rel` (
  `du_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`du_id`),
  KEY `FK_Reference_1` (`dept_id`),
  KEY `FK_Reference_2` (`user_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`dept_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept_user_rel
-- ----------------------------
INSERT INTO `t_dept_user_rel` VALUES ('1', '1', '15');
INSERT INTO `t_dept_user_rel` VALUES ('2', '2', '16');
INSERT INTO `t_dept_user_rel` VALUES ('25', '2', '17');
INSERT INTO `t_dept_user_rel` VALUES ('26', '3', '19');
INSERT INTO `t_dept_user_rel` VALUES ('27', '1', '18');

-- ----------------------------
-- Table structure for t_filedname
-- ----------------------------
DROP TABLE IF EXISTS `t_filedname`;
CREATE TABLE `t_filedname` (
  `field_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `table_name` varchar(64) NOT NULL COMMENT '表名',
  `field_name` varchar(64) NOT NULL COMMENT '表字段名',
  `fname_zh` varchar(64) DEFAULT NULL COMMENT '字段中文名',
  `fname_en` varchar(64) DEFAULT NULL COMMENT '字段英文名',
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of t_filedname
-- ----------------------------

-- ----------------------------
-- Table structure for t_filedvalue
-- ----------------------------
DROP TABLE IF EXISTS `t_filedvalue`;
CREATE TABLE `t_filedvalue` (
  `value_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `field_id` bigint(20) NOT NULL COMMENT 'fk',
  `field_name` varchar(64) NOT NULL COMMENT '表字段名',
  `field_value` varchar(64) NOT NULL COMMENT '原生字段值',
  `fvalue_zh` varchar(64) DEFAULT NULL COMMENT '中文值',
  `fvalue_en` varchar(64) DEFAULT NULL COMMENT '英文值',
  PRIMARY KEY (`value_id`),
  KEY `FK_Reference_10` (`field_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`field_id`) REFERENCES `t_filedname` (`field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of t_filedvalue
-- ----------------------------

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `fun_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '功能id',
  `fun_subsystem_name` varchar(64) NOT NULL COMMENT '子系统名称',
  `fun_name` varchar(64) NOT NULL COMMENT '功能名称（增，删，改，查）',
  PRIMARY KEY (`fun_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='功能表';

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES ('1', 'sys', 'user');
INSERT INTO `t_function` VALUES ('2', 'sys', 'role');
INSERT INTO `t_function` VALUES ('3', 'sys', 'right');
INSERT INTO `t_function` VALUES ('4', 'sys', 'menu');
INSERT INTO `t_function` VALUES ('5', 'sys', 'dept');
INSERT INTO `t_function` VALUES ('6', 'sys', 'page');
INSERT INTO `t_function` VALUES ('7', 'sys', 'function');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(64) NOT NULL COMMENT '菜单名称',
  `menu_parent` bigint(20) DEFAULT NULL COMMENT '父级菜单id',
  `menu_level` int(2) NOT NULL,
  `menu_url` varchar(64) DEFAULT NULL COMMENT '页面url',
  `menu_icon` varchar(64) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '用户管理', '8', '1', '/sys/user', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('2', '部门管理', '8', '1', '/sys/dept', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('3', '角色管理', '8', '1', ' /sys/role', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('4', '功能管理', '8', '1', '/sys/function', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('5', '菜单管理', '8', '1', ' /sys/menu', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('6', '页面管理', '8', '1', '/sys/page', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('7', '资源管理', '8', '1', '/sys/resource', 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('8', '系统管理', null, '0', null, 'el-icon-setting');
INSERT INTO `t_menu` VALUES ('9', '统计查询', null, '0', '/menu/**', 'el-icon-setting');

-- ----------------------------
-- Table structure for t_page
-- ----------------------------
DROP TABLE IF EXISTS `t_page`;
CREATE TABLE `t_page` (
  `page_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面id',
  `page_url` varchar(64) NOT NULL COMMENT '页面url',
  `page_desc` varchar(200) DEFAULT NULL COMMENT '页面功能',
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='页面表';

-- ----------------------------
-- Records of t_page
-- ----------------------------
INSERT INTO `t_page` VALUES ('1', '/user/addUser', '添加用户');
INSERT INTO `t_page` VALUES ('2', '/user/updateUser', '更新用户');
INSERT INTO `t_page` VALUES ('3', '/user/findByPage', '分页查询用户');
INSERT INTO `t_page` VALUES ('5', '/user/delete', '删除用户');
INSERT INTO `t_page` VALUES ('6', '/user/findAll', '查询所有用户');
INSERT INTO `t_page` VALUES ('7', '/user//findById', '通过ID查询用户');
INSERT INTO `t_page` VALUES ('8', '/user/findByName', '通过名称查询用户');
INSERT INTO `t_page` VALUES ('9', '/user/findUserRole', '查询用户角色信息');
INSERT INTO `t_page` VALUES ('10', '/dept/addDept', '添加部门');
INSERT INTO `t_page` VALUES ('11', '/dept/deleteDept', '删除部门');
INSERT INTO `t_page` VALUES ('12', '/dept/queryDetail', '查询部门详情');
INSERT INTO `t_page` VALUES ('13', '/dept/findByName', '通过名称查询部门');
INSERT INTO `t_page` VALUES ('14', '/dept/findByPage', '分页查询部门');
INSERT INTO `t_page` VALUES ('15', '/dept/updateDept', '更新部门');
INSERT INTO `t_page` VALUES ('16', '/dept/findAll', '查询所有部门');
INSERT INTO `t_page` VALUES ('17', '/role/addRole', '添加角色');
INSERT INTO `t_page` VALUES ('18', '/role/deleteRole', '删除角色');
INSERT INTO `t_page` VALUES ('19', '/role/updateRole', '更新角色');
INSERT INTO `t_page` VALUES ('20', '/roel/queryDetail', '查看角色详情');
INSERT INTO `t_page` VALUES ('21', '/right/addRight', '添加权限');
INSERT INTO `t_page` VALUES ('22', '/right/deleteRight', '删除权限');
INSERT INTO `t_page` VALUES ('23', '/right/updateRight', '更新权限');
INSERT INTO `t_page` VALUES ('24', '/right/findByPage', '分页查询权限');
INSERT INTO `t_page` VALUES ('25', '/right/findById', '通过ID查询权限');
INSERT INTO `t_page` VALUES ('27', '/menu/addMenu', '添加菜单');
INSERT INTO `t_page` VALUES ('28', '/menu/deleteMenu', '删除菜单');
INSERT INTO `t_page` VALUES ('29', '/menu/updateMenu', '更新菜单');
INSERT INTO `t_page` VALUES ('30', '/page/addPage', '添加页面');
INSERT INTO `t_page` VALUES ('31', '/page/deletePage', '删除页面');
INSERT INTO `t_page` VALUES ('32', '/page/updatePage', '更新页面');
INSERT INTO `t_page` VALUES ('33', '/role/findByPage', '分页查询角色');

-- ----------------------------
-- Table structure for t_right
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right` (
  `right_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `right_name` varchar(64) NOT NULL COMMENT '权限名称（可见，不可见，可操作，不可操作）',
  `right_type` int(2) NOT NULL COMMENT '权限类型（1:功能权限，2:页面，3:菜单，4:资源）',
  PRIMARY KEY (`right_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES ('1', '可见', '2');
INSERT INTO `t_right` VALUES ('2', '不可见', '2');
INSERT INTO `t_right` VALUES ('3', '激活', '2');
INSERT INTO `t_right` VALUES ('4', '未激活', '2');
INSERT INTO `t_right` VALUES ('5', 'add', '1');
INSERT INTO `t_right` VALUES ('6', 'edit', '1');
INSERT INTO `t_right` VALUES ('7', 'delete', '1');
INSERT INTO `t_right` VALUES ('8', 'view', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `role_creater` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', 'admin', '2020-04-09 17:48:08');
INSERT INTO `t_role` VALUES ('2', '管理员', 'admin', '2020-04-30 17:48:34');
INSERT INTO `t_role` VALUES ('3', '研究人员', 'admin', '2020-02-17 17:48:40');
INSERT INTO `t_role` VALUES ('4', '审假人员', 'admin', '2020-04-28 17:48:47');
INSERT INTO `t_role` VALUES ('5', '机关参谋', 'admin', '2020-04-09 17:48:50');
INSERT INTO `t_role` VALUES ('6', '安检人员', 'admin', '2020-04-09 17:48:52');
INSERT INTO `t_role` VALUES ('7', '首长', 'admin', '2020-04-09 17:48:55');
INSERT INTO `t_role` VALUES ('8', '型号认证员', 'admin', '2020-04-18 17:48:57');
INSERT INTO `t_role` VALUES ('26', '主任', 'admin', '2020-04-09 05:05:07');

-- ----------------------------
-- Table structure for t_role_right_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_role_right_rel`;
CREATE TABLE `t_role_right_rel` (
  `rr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `ele_id` bigint(20) NOT NULL COMMENT '要素id（功能，菜单，页面，资源表的主键）',
  `ele_type` int(2) NOT NULL COMMENT '要素类型（1::功能，2:菜单，3:页面，4:资源）',
  `right_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`rr_id`),
  KEY `FK_Reference_5` (`role_id`),
  KEY `FK_Reference_6` (`right_id`),
  KEY `FK_Reference_9` (`ele_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`right_id`) REFERENCES `t_right` (`right_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of t_role_right_rel
-- ----------------------------
INSERT INTO `t_role_right_rel` VALUES ('1', '1', '1', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('2', '1', '2', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('3', '1', '3', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('4', '1', '4', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('5', '1', '5', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('6', '1', '6', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('7', '1', '7', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('8', '1', '8', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('9', '1', '9', '2', '1');
INSERT INTO `t_role_right_rel` VALUES ('10', '1', '1', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('11', '1', '1', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('12', '1', '1', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('13', '1', '1', '1', '8');
INSERT INTO `t_role_right_rel` VALUES ('14', '1', '2', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('15', '1', '2', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('16', '1', '2', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('17', '1', '2', '1', '8');
INSERT INTO `t_role_right_rel` VALUES ('18', '1', '3', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('19', '1', '3', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('20', '1', '3', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('21', '1', '3', '1', '8');
INSERT INTO `t_role_right_rel` VALUES ('22', '1', '4', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('23', '1', '4', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('24', '1', '4', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('25', '1', '4', '1', '8');
INSERT INTO `t_role_right_rel` VALUES ('26', '1', '5', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('27', '1', '5', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('28', '1', '5', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('29', '1', '5', '1', '8');
INSERT INTO `t_role_right_rel` VALUES ('30', '1', '6', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('31', '1', '6', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('32', '1', '6', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('33', '1', '6', '1', '8');
INSERT INTO `t_role_right_rel` VALUES ('34', '1', '6', '1', '5');
INSERT INTO `t_role_right_rel` VALUES ('35', '1', '6', '1', '6');
INSERT INTO `t_role_right_rel` VALUES ('36', '1', '6', '1', '7');
INSERT INTO `t_role_right_rel` VALUES ('37', '1', '6', '1', '8');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_acc` varchar(36) NOT NULL COMMENT '账号',
  `user_pwd` varchar(36) NOT NULL COMMENT '密码',
  `user_nickname` varchar(36) DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_age` int(3) DEFAULT NULL COMMENT '年龄',
  `user_gender` varchar(4) DEFAULT NULL COMMENT '性别',
  `user_tel` varchar(20) NOT NULL COMMENT '联系电话',
  `user_email` varchar(36) DEFAULT NULL COMMENT '邮箱',
  `user_add` varchar(64) DEFAULT NULL COMMENT '地址',
  `user_position` varchar(64) NOT NULL COMMENT '职务',
  `user_note` text COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('15', 'abcd1.com', '123456', '尘风', '张晓武', '26', '男', '18792443591', '18792443591@163.com', '北京市西城区', '大数据开发工程师', '隶属于大数据研发部');
INSERT INTO `t_user` VALUES ('16', 'abcd2.com', '123456', '该死的温柔', '黄丽丽', '30', '女', '18995362156', '12364259@qq.com', '北京市西城区月坛北街', '人员管理', '隶属于航天科技集团公司大数据研发部');
INSERT INTO `t_user` VALUES ('17', 'abcd3.com', '123456', '小客人', '小郑', '28', '女', '13926452368', '1364259436@qq.com', '北京市西城区', 'Android开发工程师', '隶属于航天科技集团公司大数据研发部');
INSERT INTO `t_user` VALUES ('18', 'abcd4.com', '123456', '绝不放过', '徐聪', '35', '男', '13926452368', '11493556249@qq.com', '北京市海淀区国荣大厦', 'Java开发工程师', '无');
INSERT INTO `t_user` VALUES ('19', 'abcd5.com', '123456', '多年以后', '刘依晨', '27', '男', '13991723594', '13991723594@163.com', '北京市朝阳区', '前端开发工程师', '无');

-- ----------------------------
-- Table structure for t_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_rel`;
CREATE TABLE `t_user_role_rel` (
  `ur_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`ur_id`),
  KEY `FK_Reference_3` (`user_id`),
  KEY `FK_Reference_4` (`role_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of t_user_role_rel
-- ----------------------------
INSERT INTO `t_user_role_rel` VALUES ('1', '15', '1');
INSERT INTO `t_user_role_rel` VALUES ('16', '15', '2');
INSERT INTO `t_user_role_rel` VALUES ('17', '17', '4');
INSERT INTO `t_user_role_rel` VALUES ('18', '18', '7');
INSERT INTO `t_user_role_rel` VALUES ('19', '19', '3');
