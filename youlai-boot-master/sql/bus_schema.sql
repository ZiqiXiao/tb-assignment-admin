use youlai_boot;

SET NAMES utf8mb4;
-- ----------------------------
-- Table structure for bus_asn_info
-- ----------------------------
DROP TABLE IF EXISTS bus_asn_info;
CREATE TABLE bus_asn_info (
    id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    asn_no varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内部编号',
    order_no varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '淘宝订单号',
    status tinyint NOT NULL DEFAULT 1 COMMENT '状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)',
    asn_scn_cat varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务场景分类',
    asn_tech_cat varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务技术分类',
    asn_lang varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务编程语言',
    asn_price float NOT NULL DEFAULT 0 COMMENT '任务金额',
    tech_portion float NOT NULL DEFAULT 0 COMMENT '老师金额',
    plat_portion float NOT NULL DEFAULT 0 COMMENT '平台金额',
    asn_desc varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务描述',
    css_id bigint NOT NULL DEFAULT -1 COMMENT '客服ID',
    tech_id bigint NOT NULL DEFAULT -1 COMMENT '程序员id',
    consult_dt date NULL DEFAULT NULL COMMENT '咨询日期',
    order_dt date NULL DEFAULT NULL COMMENT '下单日期',
    ship_dt date NULL DEFAULT NULL COMMENT '发货日期',
    deliver_dt date NULL DEFAULT NULL COMMENT '交付日期',
    receive_dt date NULL DEFAULT NULL COMMENT '收货日期',
    check_dt date NULL DEFAULT NULL COMMENT '应结日期',
    settlement_dt date NULL DEFAULT NULL COMMENT '结算日期',
    create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
    update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_asn_info
-- ----------------------------
INSERT INTO bus_asn_info VALUES (1,'tb-x-000000','123456789',0,'作业','数据分析','python',2000,1600,400,'xxx',10000,60000,'2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06 19:03:32','2019-12-06 19:03:32','2019-12-06 19:03:32');
INSERT INTO bus_asn_info VALUES (2,'tb-x-000001','123456789',0,'作业','数据分析','java',100,80,20,'实体为基，品牌为要，创新为魂。晋江以实体经济为支撑，加快建设现代化经济体系，锻造发展新优势。传统产业高新化、新兴产业集群化、现代服务业专业化，培育出51家上市企业、54家省级以上“专精特新”、18家制造业单项冠军、国家高新技术企业647家。',10000,60000,'2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06','2019-12-06 19:03:32','2019-12-06 19:03:32','2019-12-06 19:03:32');
-- ----------------------------
-- Table structure for bus_cus_svc_info
-- ----------------------------
DROP TABLE IF EXISTS bus_css_info;
CREATE TABLE bus_css_info (
    css_id bigint NOT NULL AUTO_INCREMENT COMMENT '客服编号',
    css_code varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '客服代码',
    css_name varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '客服姓名',
    entry_dt date NULL DEFAULT NULL COMMENT '入职日期',
    dep_dt date NULL DEFAULT NULL COMMENT '离职日期',
    create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
    update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (css_id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客服信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_css_info
-- ----------------------------
INSERT INTO bus_css_info VALUES (10001, 'x', '客服小丞', '2019-12-06', '2019-12-06','2019-12-06','2019-12-06');
INSERT INTO bus_css_info VALUES (10002, 'y', '客服小宁', '2019-12-06', '2019-12-06','2019-12-06','2019-12-06');

-- ----------------------------
-- Table structure for bus_tech_info
-- ----------------------------
DROP TABLE IF EXISTS bus_tech_info;
CREATE TABLE bus_tech_info (
    tech_id bigint NOT NULL AUTO_INCREMENT COMMENT '程序员编号',
    tech_name varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '程序员姓名',
    alipay varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付宝账号',
    ratio decimal(3, 2) NOT NULL DEFAULT 0.0 COMMENT '分成比例',
    entry_dt date NULL DEFAULT NULL COMMENT '入职日期',
    dep_dt date NULL DEFAULT NULL COMMENT '离职日期',
    create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
    update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (tech_id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客服信息表' ROW_FORMAT = DYNAMIC
;

-- ----------------------------
-- Records of bus_cus_svc_info
-- ----------------------------
INSERT INTO bus_tech_info VALUES (60001, '肖子奇', '18510186056', '0.80', '2019-12-06','2019-12-06','2019-12-06','2019-12-06');