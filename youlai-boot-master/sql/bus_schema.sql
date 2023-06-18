-- ----------------------------
-- Table structure for bus_asn_info
-- ----------------------------
DROP TABLE IF EXISTS bus_asn_info;
CREATE TABLE bus_asn_info (
    id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    bus_no varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内部编号',
    order_no varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '淘宝订单号',
    status tinyint NOT NULL DEFAULT 1 COMMENT '状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;)',
    status_time json NOT NULL DEFAULT '{"0":"","1":"","2":"","3":"","4":"","5":"","6":"","7":""}' COMMENT '状态更新时间',
    asn_env_cat varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务场景分类',
    asn_tech_cat varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务技术分类',
    asn_lan varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务编程语言',
    asn_price int NOT NULL DEFAULT 0 COMMENT '任务金额',
    tech_portion int NOT NULL DEFAULT 0 COMMENT '老师金额',
    plat_portion int NOT NULL DEFAULT 0 COMMENT '平台金额',
    asn_desc varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务描述',
    css_id bigint NOT NULL COMMENT '客服ID',
    tech_id bigint NOT NULL COMMENT '程序员id',
    consult_dt date NULL DEFAULT NULL COMMENT '咨询日期',
    order_dt date NULL DEFAULT NULL COMMENT '下单日期',
    check_dt date NULL DEFAULT NULL COMMENT '应结日期',
    settlement_dt date NULL DEFAULT NULL COMMENT '结算时间',
    create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
    update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_asn_info
-- ----------------------------
INSERT INTO bus_asn_info VALUES (
                                 1,
                                 'tb-x-000000',
                                 '123456789',
                                 0,
                                 '{"0":"2019-12-06 19:03:32","1":"","2":"","3":"","4":"","5":"","6":"","7":""}',
                                 '作业',
                                 '数据分析',
                                 'python',
                                 0,
                                 0,
                                 0,
                                 'xxx',
                                 0,
                                 0,
                                 '2019-12-06',
                                 '2019-12-06',
                                 '2019-12-06',
                                 '2019-12-06 19:03:32',
                                 '2019-12-06 19:03:32',
                                 '2019-12-06 19:03:32'
 )
;

-- ----------------------------
-- Table structure for bus_cus_svc_info
-- ----------------------------
DROP TABLE IF EXISTS bus_cus_svc_info;
CREATE TABLE bus_cus_svc_info (
    css_id bigint NOT NULL AUTO_INCREMENT COMMENT '客服编号',
    css_code varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '客服代码',
    css_name varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '客服姓名',
    emp_date date NULL DEFAULT NULL COMMENT '入职日期',
    dep_date date NULL DEFAULT NULL COMMENT '离职日期',
    PRIMARY KEY (css_id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客服信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_cus_svc_info
-- ----------------------------
INSERT INTO bus_cus_svc_info VALUES (10000, 'x', '肖子奇', '2019-12-06', '2019-12-06');

-- ----------------------------
-- Table structure for bus_tech_info
-- ----------------------------
DROP TABLE IF EXISTS bus_tech_info;
CREATE TABLE bus_tech_info (
    tech_id bigint NOT NULL AUTO_INCREMENT COMMENT '程序员编号',
    tech_name varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '程序员姓名',
    ratio decimal(3, 2) NOT NULL DEFAULT 0.0 COMMENT '分成比例',
    emp_date date NULL DEFAULT NULL COMMENT '入职日期',
    dep_date date NULL DEFAULT NULL COMMENT '离职日期',
    PRIMARY KEY (tech_id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客服信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_cus_svc_info
-- ----------------------------
INSERT INTO bus_tech_info VALUES (60000, '肖子奇', '0.80', '2019-12-06', '2019-12-06');