/*
* youlai_boot 权限系统数据库(MySQL8.x)
* @author haoxr
*/

-- ----------------------------
-- 1. 创建数据库
-- ----------------------------
CREATE DATABASE IF NOT EXISTS youlai_boot DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;


-- ----------------------------
-- 2. 创建表 && 数据初始化
-- ----------------------------
use youlai_boot;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
                             `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父节点id',
                             `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '父节点id路径',
                             `sort` int NULL DEFAULT 0 COMMENT '显示顺序',
                             `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(1:正常;0:禁用)',
                             `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除标识(1:已删除;0:未删除)',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '有来技术', 0, '0', 1, 1, 0, NULL, NULL, 1, 1);
INSERT INTO `sys_dept` VALUES (2, '研发部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);
INSERT INTO `sys_dept` VALUES (3, '测试部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);
-- INSERT INTO `sys_dept` VALUES (175,'客服',1,'0,1',1,1,0,'2023-06-21 21:40:28','2023-06-21 21:40:28',NULL,NULL);
-- INSERT INTO `sys_dept` VALUES (176,'游客',1,'0,1',1,1,0,'2023-06-21 21:48:38','2023-06-21 21:48:38',NULL,NULL);


-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `type_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型编码',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项名称',
                             `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项值',
                             `sort` int NULL DEFAULT 0 COMMENT '排序',
                             `status` tinyint NULL DEFAULT 0 COMMENT '状态(1:正常;0:禁用)',
                             `defaulted` tinyint NULL DEFAULT 0 COMMENT '是否默认(1:是;0:否)',
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'gender', '男', '1', 1, 1, 0, NULL, '2019-05-05 13:07:52', '2022-06-12 23:20:39');
INSERT INTO `sys_dict` VALUES (2, 'gender', '女', '2', 2, 1, 0, NULL, '2019-04-19 11:33:00', '2019-07-02 14:23:05');
INSERT INTO `sys_dict` VALUES (3, 'gender', '未知', '0', 1, 1, 0, NULL, '2020-10-17 08:09:31', '2020-10-17 08:09:31');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ',
                                  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型名称',
                                  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型编码',
                                  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0:正常;1:禁用)',
                                  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `type_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '性别', 'gender', 1, NULL, '2019-12-06 19:03:32', '2022-06-12 16:21:28');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `parent_id` bigint NOT NULL COMMENT '父菜单ID',
                             `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点ID路径',
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
                             `type` tinyint NOT NULL COMMENT '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
                             `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
                             `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
                             `perm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
                             `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
                             `sort` int NULL DEFAULT 0 COMMENT '排序',
                             `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单图标',
                             `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user', '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO `sys_menu` VALUES (2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO `sys_menu` VALUES (3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO `sys_menu` VALUES (4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO `sys_menu` VALUES (5, 1, '0,1', '部门管理', 1, 'dept', 'system/dept/index', NULL, 1, 4, 'tree', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO `sys_menu` VALUES (6, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', NULL, 1, 5, 'dict', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO `sys_menu` VALUES (20, 0, '0', '多级菜单', 2, '/multi-level', 'Layout', NULL, 1, 9, 'multi_level', '/multi-level/multi-level1', '2022-02-16 23:11:00', '2022-02-16 23:11:00');
INSERT INTO `sys_menu` VALUES (21, 20, '0,20', '菜单一级', 2, 'multi-level1', 'demo/multi-level/level1', NULL, 1, 1, '', '/multi-level/multi-level2', '2022-02-16 23:13:38', '2022-02-16 23:13:38');
INSERT INTO `sys_menu` VALUES (22, 21, '0,20,21', '菜单二级', 2, 'multi-level2', 'demo/multi-level/children/level2', NULL, 1, 1, '', '/multi-level/multi-level2/multi-level3-1', '2022-02-16 23:14:23', '2022-02-16 23:14:23');
INSERT INTO `sys_menu` VALUES (23, 22, '0,20,21,22', '菜单三级-1', 1, 'multi-level3-1', 'demo/multi-level/children/children/level3-1', NULL, 1, 1, '', '', '2022-02-16 23:14:51', '2022-02-16 23:14:51');
INSERT INTO `sys_menu` VALUES (24, 22, '0,20,21,22', '菜单三级-2', 1, 'multi-level3-2', 'demo/multi-level/children/children/level3-2', NULL, 1, 2, '', '', '2022-02-16 23:15:08', '2022-02-16 23:15:08');
INSERT INTO `sys_menu` VALUES (26, 0, '0', '外部链接', 2, '/external-link', 'Layout', NULL, 1, 8, 'link', 'noredirect', '2022-02-17 22:51:20', '2022-02-17 22:51:20');
INSERT INTO `sys_menu` VALUES (30, 26, '0,26', 'document', 3, 'https://juejin.cn/post/7228990409909108793', '', NULL, 1, 1, 'document', '', '2022-02-18 00:01:40', '2022-02-18 00:01:40');
INSERT INTO `sys_menu` VALUES (31, 2, '0,1,2', '用户新增', 4, '', NULL, 'sys:user:add', 1, 1, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11');
INSERT INTO `sys_menu` VALUES (32, 2, '0,1,2', '用户编辑', 4, '', NULL, 'sys:user:edit', 1, 2, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11');
INSERT INTO `sys_menu` VALUES (33, 2, '0,1,2', '用户删除', 4, '', NULL, 'sys:user:delete', 1, 3, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11');
INSERT INTO `sys_menu` VALUES (36, 0, '0', '组件封装', 2, '/component', 'Layout', NULL, 1, 10, 'menu', '', '2022-10-31 09:18:44', '2022-10-31 09:18:47');
INSERT INTO `sys_menu` VALUES (37, 36, '0,36', '富文本编辑器', 1, 'wang-editor', 'demo/wang-editor', NULL, 1, 1, '', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (38, 36, '0,36', '图片上传', 1, 'upload', 'demo/upload', NULL, 1, 2, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32');
INSERT INTO `sys_menu` VALUES (39, 36, '0,36', '图标选择器', 1, 'icon-selector', 'demo/icon-selector', NULL, 1, 3, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32');
INSERT INTO `sys_menu` VALUES (40, 0, '0', '接口', 2, '/api', 'Layout', NULL, 1, 7, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20');
INSERT INTO `sys_menu` VALUES (41, 40, '0,40', '接口文档', 1, 'apidoc', 'demo/api-doc', NULL, 1, 1, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20');
INSERT INTO `sys_menu` VALUES (70, 3, '0,1,3', '角色新增', 4, '', NULL, 'sys:role:add', 1, 1, '', NULL, '2023-05-20 23:39:09', '2023-05-20 23:39:09');
INSERT INTO `sys_menu` VALUES (71, 3, '0,1,3', '角色编辑', 4, '', NULL, 'sys:role:edit', 1, 2, '', NULL, '2023-05-20 23:40:31', '2023-05-20 23:40:31');
INSERT INTO `sys_menu` VALUES (72, 3, '0,1,3', '角色删除', 4, '', NULL, 'sys:role:delete', 1, 3, '', NULL, '2023-05-20 23:41:08', '2023-05-20 23:41:08');
INSERT INTO `sys_menu` VALUES (73, 4, '0,1,4', '菜单新增', 4, '', NULL, 'sys:menu:add', 1, 1, '', NULL, '2023-05-20 23:41:35', '2023-05-20 23:41:35');
INSERT INTO `sys_menu` VALUES (74, 4, '0,1,4', '菜单编辑', 4, '', NULL, 'sys:menu:edit', 1, 3, '', NULL, '2023-05-20 23:41:58', '2023-05-20 23:41:58');
INSERT INTO `sys_menu` VALUES (75, 4, '0,1,4', '菜单删除', 4, '', NULL, 'sys:menu:delete', 1, 3, '', NULL, '2023-05-20 23:44:18', '2023-05-20 23:44:18');
INSERT INTO `sys_menu` VALUES (76, 5, '0,1,5', '部门新增', 4, '', NULL, 'sys:dept:add', 1, 1, '', NULL, '2023-05-20 23:45:00', '2023-05-20 23:45:00');
INSERT INTO `sys_menu` VALUES (77, 5, '0,1,5', '部门编辑', 4, '', NULL, 'sys:dept:edit', 1, 2, '', NULL, '2023-05-20 23:46:16', '2023-05-20 23:46:16');
INSERT INTO `sys_menu` VALUES (78, 5, '0,1,5', '部门删除', 4, '', NULL, 'sys:dept:delete', 1, 3, '', NULL, '2023-05-20 23:46:36', '2023-05-20 23:46:36');
INSERT INTO `sys_menu` VALUES (79, 6, '0,1,6', '字典类型新增', 4, '', NULL, 'sys:dict_type:add', 1, 1, '', NULL, '2023-05-21 00:16:06', '2023-05-21 00:16:06');
INSERT INTO `sys_menu` VALUES (81, 6, '0,1,6', '字典类型编辑', 4, '', NULL, 'sys:dict_type:edit', 1, 2, '', NULL, '2023-05-21 00:27:37', '2023-05-21 00:27:37');
INSERT INTO `sys_menu` VALUES (84, 6, '0,1,6', '字典类型删除', 4, '', NULL, 'sys:dict_type:delete', 1, 3, '', NULL, '2023-05-21 00:29:39', '2023-05-21 00:29:39');
INSERT INTO `sys_menu` VALUES (85, 6, '0,1,6', '字典数据新增', 4, '', NULL, 'sys:dict:add', 1, 4, '', NULL, '2023-05-21 00:46:56', '2023-05-21 00:47:06');
INSERT INTO `sys_menu` VALUES (86, 6, '0,1,6', '字典数据编辑', 4, '', NULL, 'sys:dict:edit', 1, 5, '', NULL, '2023-05-21 00:47:36', '2023-05-21 00:47:36');
INSERT INTO `sys_menu` VALUES (87, 6, '0,1,6', '字典数据删除', 4, '', NULL, 'sys:dict:delete', 1, 6, '', NULL, '2023-05-21 00:48:10', '2023-05-21 00:48:20');
INSERT INTO `sys_menu` VALUES (88, 2, '0,1,2', '重置密码', 4, '', NULL, 'sys:user:reset_pwd', 1, 4, '', NULL, '2023-05-21 00:49:18', '2023-05-21 00:49:18');
INSERT INTO `sys_menu` VALUES (89, 0, '0', '任务信息展示', 2, '/asn-op', 'Layout', NULL,1,1, 'system', NULL,'2023-06-18 22:47:53','2023-06-18 22:47:55');
INSERT INTO `sys_menu` VALUES (90, 89, '0,89', '任务信息展示', 2, 'op', 'demo/asn-op', NULL,1,1, 'system', NULL,'2023-06-18 22:47:53','2023-06-18 22:47:55');


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
                             `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
                             `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态(1-正常；0-停用)',
                             `data_scope` tinyint NULL DEFAULT NULL COMMENT '数据权限(0-所有数据；1-部门及子部门数据；2-本部门数据；3-本人数据)',
                             `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, 0, '2021-05-21 14:56:51', '2018-12-23 16:00:00');
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ADMIN', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (3, '访问游客', 'GUEST', 3, 1, 2, 0, '2021-05-26 15:49:05', '2019-05-05 16:00:00');
INSERT INTO `sys_role` VALUES (4, '客服', 'CSS', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (5, '系统管理员2', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (6, '系统管理员3', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (7, '系统管理员4', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (8, '系统管理员5', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (9, '系统管理员6', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (10, '系统管理员7', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (11, '系统管理员8', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role` VALUES (12, '系统管理员9', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` bigint NOT NULL COMMENT '角色ID',
                                  `menu_id` bigint NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 11);
INSERT INTO `sys_role_menu` VALUES (2, 12);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (2, 18);
INSERT INTO `sys_role_menu` VALUES (2, 17);
INSERT INTO `sys_role_menu` VALUES (2, 13);
INSERT INTO `sys_role_menu` VALUES (2, 14);
INSERT INTO `sys_role_menu` VALUES (2, 15);
INSERT INTO `sys_role_menu` VALUES (2, 16);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 24);
INSERT INTO `sys_role_menu` VALUES (2, 32);
INSERT INTO `sys_role_menu` VALUES (2, 33);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 34);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 31);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 41);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 24);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 31);
INSERT INTO `sys_role_menu` VALUES (2, 32);
INSERT INTO `sys_role_menu` VALUES (2, 33);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 41);
INSERT INTO `sys_role_menu` VALUES (2, 70);
INSERT INTO `sys_role_menu` VALUES (2, 71);
INSERT INTO `sys_role_menu` VALUES (2, 72);
INSERT INTO `sys_role_menu` VALUES (2, 73);
INSERT INTO `sys_role_menu` VALUES (2, 74);
INSERT INTO `sys_role_menu` VALUES (2, 75);
INSERT INTO `sys_role_menu` VALUES (2, 76);
INSERT INTO `sys_role_menu` VALUES (2, 77);
INSERT INTO `sys_role_menu` VALUES (2, 78);
INSERT INTO `sys_role_menu` VALUES (2, 79);
INSERT INTO `sys_role_menu` VALUES (2, 81);
INSERT INTO `sys_role_menu` VALUES (2, 84);
INSERT INTO `sys_role_menu` VALUES (2, 85);
INSERT INTO `sys_role_menu` VALUES (2, 86);
INSERT INTO `sys_role_menu` VALUES (2, 87);
INSERT INTO `sys_role_menu` VALUES (2, 88);
INSERT INTO `sys_role_menu` VALUES (2, 89);
INSERT INTO `sys_role_menu` VALUES (2, 90);
INSERT INTO `sys_role_menu` VALUES (4, 89);
INSERT INTO `sys_role_menu` VALUES (4, 90);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                             `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
                             `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别((1:男;2:女))',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                             `dept_id` int NULL DEFAULT NULL COMMENT '部门ID',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
                             `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '用户状态((1:正常;0:禁用))',
                             `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                             `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0:未删除;1:已删除)',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `login_name`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '有来技术', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', NULL, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621590365', 1, 'youlaitech@163.com', 0, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'admin', '系统管理员', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, '', 0, '2019-10-10 13:41:22', '2022-07-31 12:39:30');
INSERT INTO `sys_user` VALUES (3, 'test', '测试小用户', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 3, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, 'youlaitech@163.com', 0, '2021-06-05 01:31:29', '2021-06-05 01:31:29');
-- INSERT INTO `sys_user` VALUES (287, '123', '123', 1, '$2a$10$mVoBVqm1837huf7kcN0wS.GVYKEFv0arb7GvzfFXoTyqDlcRzT.6i', 1, '', NULL, 1, NULL, 1, '2023-05-21 14:11:19', '2023-05-21 14:11:25');
-- INSERT INTO `sys_user` VALUES (289,'guest','guest',1,'$2a$10$aHrgFpG4qKbOdNBbTE5FMOwKvwPqGhyO.rjnn0.Byor141m/Y0VkC',176,'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif',NULL,1,NULL,0,'2023-06-21 21:48:58','2023-06-21 21:48:58');
-- INSERT INTO `sys_user` VALUES (290, 'ziqix', '客服:10001', 1, '$2a$10$n8oNnS3KenrRRS4JyFPaQuKupt3NjNwPLZM.tX11yiRci0B8GUubK', 175, '', NULL, 1, NULL, 1, '2023-06-21 21:48:58', '2023-06-21 21:48:58');
-- INSERT INTO `sys_user` VALUES (291, 'yunaning', '客服:10002', 1, '$2a$10$koOWujYVWG8HUWHTWOS0ae2AuyZeTs5InIZ6P02fkld0Dkq68iHim', 175, '', NULL, 1, NULL, 1, '2023-06-21 21:48:58', '2023-06-21 21:48:58');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `role_id` bigint NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);
-- INSERT INTO `sys_user_role` VALUES (287, 2);
-- INSERT INTO `sys_user_role` VALUES (289, 3);
-- INSERT INTO `sys_user_role` VALUES (290, 4);
-- INSERT INTO `sys_user_role` VALUES (291, 4);
-- ----------------------------
-- Table structure for bus_asn_info
-- ----------------------------
DROP TABLE IF EXISTS `bus_asn_info`;
CREATE TABLE `bus_asn_info` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `asn_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内部编号',
    `order_no` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '淘宝订单号',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)',
    `asn_scn_cat` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务场景分类',
    `asn_tech_cat` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务技术分类',
    `asn_lang` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务编程语言',
    `asn_price` float NOT NULL DEFAULT 0 COMMENT '任务金额',
    `tech_portion` float NOT NULL DEFAULT 0 COMMENT '老师金额',
    `plat_portion` float NOT NULL DEFAULT 0 COMMENT '平台金额',
    `asn_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务描述',
    `css_id` bigint NOT NULL DEFAULT -1 COMMENT '客服ID',
    `tech_id` bigint NOT NULL DEFAULT -1 COMMENT '程序员id',
    `consult_dt` date NULL DEFAULT NULL COMMENT '咨询日期',
    `order_dt` date NULL DEFAULT NULL COMMENT '下单日期',
    `ship_dt` date NULL DEFAULT NULL COMMENT '发货日期',
    `deliver_dt` date NULL DEFAULT NULL COMMENT '交付日期',
    `receive_dt` date NULL DEFAULT NULL COMMENT '收货日期',
    `check_dt` date NULL DEFAULT NULL COMMENT '应结日期',
    `settlement_dt` date NULL DEFAULT NULL COMMENT '结算日期',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_asn_info
-- ----------------------------
INSERT INTO `bus_asn_info` (id, asn_no, order_no, status, asn_scn_cat, asn_tech_cat, asn_lang, asn_price, tech_portion, plat_portion, asn_desc, css_id, tech_id, consult_dt, order_dt, ship_dt, deliver_dt, receive_dt, check_dt, settlement_dt, create_time, update_time)
VALUES
    (1,'tb-x-100100','123456789',0,'作业','数据分析','python',2000,1600,400,'xxx',10000,60000,'2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06 19:03:32','2019-12-06 19:03:32','2019-12-06 19:03:32'),
    (2,'tb-x-100101','123456789',0,'作业','数据分析','java',100,80,20,'实体为基，品牌为要，创新为魂。晋江以实体经济为支撑，加快建设现代化经济体系，锻造发展新优势。传统产业高新化、新兴产业集群化、现代服务业专业化，培育出51家上市企业、54家省级以上“专精特新”、18家制造业单项冠军、国家高新技术企业647家。',10000,60000,'2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06 19:03:32','2019-12-06 19:03:32','2019-12-06 19:03:32');
-- ----------------------------
-- Table structure for bus_cus_svc_info
-- ----------------------------
DROP TABLE IF EXISTS `bus_css_info`;
CREATE TABLE `bus_css_info` (
    `css_id` bigint NOT NULL AUTO_INCREMENT COMMENT '客服编号',
    `css_code` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '客服代码',
    `css_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '客服姓名',
    `entry_dt` date NULL DEFAULT NULL COMMENT '入职日期',
    `dep_dt` date NULL DEFAULT NULL COMMENT '离职日期',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`css_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客服信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_css_info
-- ----------------------------
INSERT INTO `bus_css_info` (css_id, css_code, css_name, entry_dt, dep_dt, create_time, update_time)
VALUES
    (10001, 'x', '客服小丞', '2019-12-06', '2019-12-06','2019-12-06','2019-12-06'),
    (10002, 'y', '客服小宁', '2019-12-06', '2019-12-06','2019-12-06','2019-12-06');
INSERT INTO `bus_css_info` VALUE (10003, 'y', '客服小馨', '2019-12-06', '2019-12-06','2019-12-06','2019-12-06');
INSERT INTO `bus_css_info` VALUE (10004, 'y', '客服珊珊', '2019-12-06', '2019-12-06','2019-12-06','2019-12-06');
-- ----------------------------
-- Table structure for bus_tech_info
-- ----------------------------
DROP TABLE IF EXISTS `bus_tech_info`;
CREATE TABLE `bus_tech_info` (
    `tech_id` bigint NOT NULL AUTO_INCREMENT COMMENT '程序员编号',
    `tech_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '程序员姓名',
    `alipay` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付宝账号',
    `ratio` decimal(3, 2) NOT NULL DEFAULT 0.0 COMMENT '分成比例',
    `entry_dt` date NULL DEFAULT NULL COMMENT '入职日期',
    `dep_dt` date NULL DEFAULT NULL COMMENT '离职日期',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`tech_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客服信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_cus_svc_info
-- ----------------------------
INSERT INTO `bus_tech_info` (tech_id, tech_name, alipay, ratio, entry_dt, dep_dt, create_time, update_time)
VALUES
    (60001,'李一鹭','18818207237',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60002,'卢坤鹏','2357790039@qq.com',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60003,'刘映辉','18174080060',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60004,'费敬雯','13248260331',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60005,'佟建恒','15641539789',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60006,'裴宝琦','19801191201',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60007,'彭以清','15615019379',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60008,'徐一品','15706806626',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60009,'陈俊浩','18781375694',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60010,'郑万鑫','19181310596',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60011,'王浩维','13654080516',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60012,'罗康洋','18321168151',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60013,'李俊林','18837111797',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60014,'于晓庆','15373709792',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60015,'张嘉宇','15359274253',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60016,'魏郑','13472598312',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60017,'张翰康','18862632816',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60018,'万凤强','wfq011207@163.com',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60019,'齐佳玉','15536100407',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60020,'李彦夷','lyy_ddd@qq.com',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60021,'潘松','15356406402',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60022,'卢方启','15717026958',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60023,'刘诗画','18900297379',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60024,'杨斯迪','yangnuanyou@163.com',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60025,'张翰康','18862632816',0.80,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60026,'佘依函','13997800577',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60027,'廖国清','17353590584',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60028,'苗列','18508169658',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60029,'崔赫萌','16665202509',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60030,'金欣哲','hidejxz@hotmail.com',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60031,'关曾铭','18177511992',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60032,'陈天驰','18917389203',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60033,'张永康','15238201046@163.com',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60034,'姜云瑶','18015330385',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60035,'宋启兴','15214375765',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60036,'李碧琳','15201796139',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60037,'高一哲','13756474818',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60038,'祝璠','18990780225',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60039,'孟祥臣','13361376383',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60040,'谢忱妤','18305670896',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60041,'陈意诺','15618800036',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60042,'范闻天','18502752757',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60043,'张帅','17360839833',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60044,'王昆','17605548340',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60045,'许茹芳','18770602125',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60046,'侯程洋','15662339483',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60047,'王宋','229390299@qq.com',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60048,'陈黎明','15726675527',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60049,'陈硕','18810952966',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60050,'张凯','15291866599',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60051,'谢扬','18023129389',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60052,'于英坤','15053649657',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60053,'李嘉琪','19838039037',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60054,'沈铎','19379457194',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60055,'戚玉兴','17637823039',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60056,'王世杰','18765424863',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60057,'王俊梁','15237490760',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60058,'陶炬','2639753385@qq.com',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60059,'冯新星','17692930143',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60060,'游磊','15507412146',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60061,'赵旭','19995455356',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60062,'丁世杰','15937283587',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60063,'周鑫宇','18361566257',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60064,'李慧婷','19512244741',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60065,'梁旺建','18520143865',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60066,'樊昱含','13409968675',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60067,'韩康康','',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60068,'李向林','13731634694',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60069,'于典','',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60070,'耿皓松','17349860113',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22')
    ,(60071,'Ella','15192588601',0.70,'2023/6/22',NULL,'2023/6/22','2023/6/22');

SET FOREIGN_KEY_CHECKS = 1;