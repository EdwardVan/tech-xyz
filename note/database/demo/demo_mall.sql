SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_cart
-- ----------------------------
DROP TABLE IF EXISTS `demo_cart`;
CREATE TABLE `demo_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `checked` int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 127 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_cart
-- ----------------------------
INSERT INTO `demo_cart` VALUES (126, 21, 26, 1, 1, '2017-04-13 21:27:06', '2017-04-13 21:27:06',0);

-- ----------------------------
-- Table structure for demo_category
-- ----------------------------
DROP TABLE IF EXISTS `demo_category`;
CREATE TABLE `demo_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT 1 COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100031 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_category
-- ----------------------------
INSERT INTO `demo_category` VALUES (100001, 0, '家用电器', 1, NULL, '2017-03-25 16:46:00', '2017-03-25 16:46:00',0);
INSERT INTO `demo_category` VALUES (100002, 0, '数码3C', 1, NULL, '2017-03-25 16:46:21', '2017-03-25 16:46:21',0);
INSERT INTO `demo_category` VALUES (100003, 0, '服装箱包', 1, NULL, '2017-03-25 16:49:53', '2017-03-25 16:49:53',0);
INSERT INTO `demo_category` VALUES (100004, 0, '食品生鲜', 1, NULL, '2017-03-25 16:50:19', '2017-03-25 16:50:19',0);
INSERT INTO `demo_category` VALUES (100005, 0, '酒水饮料', 1, NULL, '2017-03-25 16:50:29', '2017-03-25 16:50:29',0);
INSERT INTO `demo_category` VALUES (100006, 100001, '冰箱', 1, NULL, '2017-03-25 16:52:15', '2017-03-25 16:52:15',0);
INSERT INTO `demo_category` VALUES (100007, 100001, '电视', 1, NULL, '2017-03-25 16:52:26', '2017-03-25 16:52:26',0);
INSERT INTO `demo_category` VALUES (100008, 100001, '洗衣机', 1, NULL, '2017-03-25 16:52:39', '2017-03-25 16:52:39',0);
INSERT INTO `demo_category` VALUES (100009, 100001, '空调', 1, NULL, '2017-03-25 16:52:45', '2017-03-25 16:52:45',0);
INSERT INTO `demo_category` VALUES (100010, 100001, '电热水器', 1, NULL, '2017-03-25 16:52:54', '2017-03-25 16:52:54',0);
INSERT INTO `demo_category` VALUES (100011, 100002, '电脑', 1, NULL, '2017-03-25 16:53:18', '2017-03-25 16:53:18',0);
INSERT INTO `demo_category` VALUES (100012, 100002, '手机', 1, NULL, '2017-03-25 16:53:27', '2017-03-25 16:53:27',0);
INSERT INTO `demo_category` VALUES (100013, 100002, '平板电脑', 1, NULL, '2017-03-25 16:53:35', '2017-03-25 16:53:35',0);
INSERT INTO `demo_category` VALUES (100014, 100002, '数码相机', 1, NULL, '2017-03-25 16:53:56', '2017-03-25 16:53:56',0);
INSERT INTO `demo_category` VALUES (100015, 100002, '3C配件', 1, NULL, '2017-03-25 16:54:07', '2017-03-25 16:54:07',0);
INSERT INTO `demo_category` VALUES (100016, 100003, '女装', 1, NULL, '2017-03-25 16:54:44', '2017-03-25 16:54:44',0);
INSERT INTO `demo_category` VALUES (100017, 100003, '帽子', 1, NULL, '2017-03-25 16:54:51', '2017-03-25 16:54:51',0);
INSERT INTO `demo_category` VALUES (100018, 100003, '旅行箱', 1, NULL, '2017-03-25 16:55:02', '2017-03-25 16:55:02',0);
INSERT INTO `demo_category` VALUES (100019, 100003, '手提包', 1, NULL, '2017-03-25 16:55:09', '2017-03-25 16:55:09',0);
INSERT INTO `demo_category` VALUES (100020, 100003, '保暖内衣', 1, NULL, '2017-03-25 16:55:18', '2017-03-25 16:55:18',0);
INSERT INTO `demo_category` VALUES (100021, 100004, '零食', 1, NULL, '2017-03-25 16:55:30', '2017-03-25 16:55:30',0);
INSERT INTO `demo_category` VALUES (100022, 100004, '生鲜', 1, NULL, '2017-03-25 16:55:37', '2017-03-25 16:55:37',0);
INSERT INTO `demo_category` VALUES (100023, 100004, '半成品菜', 1, NULL, '2017-03-25 16:55:47', '2017-03-25 16:55:47',0);
INSERT INTO `demo_category` VALUES (100024, 100004, '速冻食品', 1, NULL, '2017-03-25 16:55:56', '2017-03-25 16:55:56',0);
INSERT INTO `demo_category` VALUES (100025, 100004, '进口食品', 1, NULL, '2017-03-25 16:56:06', '2017-03-25 16:56:06',0);
INSERT INTO `demo_category` VALUES (100026, 100005, '白酒', 1, NULL, '2017-03-25 16:56:22', '2017-03-25 16:56:22',0);
INSERT INTO `demo_category` VALUES (100027, 100005, '红酒', 1, NULL, '2017-03-25 16:56:30', '2017-03-25 16:56:30',0);
INSERT INTO `demo_category` VALUES (100028, 100005, '饮料', 1, NULL, '2017-03-25 16:56:37', '2017-03-25 16:56:37',0);
INSERT INTO `demo_category` VALUES (100029, 100005, '调制鸡尾酒', 1, NULL, '2017-03-25 16:56:45', '2017-03-25 16:56:45',0);
INSERT INTO `demo_category` VALUES (100030, 100005, '进口洋酒', 1, NULL, '2017-03-25 16:57:05', '2017-03-25 16:57:05',0);

-- ----------------------------
-- Table structure for demo_order
-- ----------------------------
DROP TABLE IF EXISTS `demo_order`;
CREATE TABLE `demo_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) DEFAULT NULL,
  `payment` decimal(20, 2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款,20-已付款,40-已发货,50-交易成功,60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_no_index`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_order
-- ----------------------------
INSERT INTO `demo_order` VALUES (103, 1491753014256, 1, 25, 13998.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-09 23:50:14', '2017-04-09 23:50:14',0);
INSERT INTO `demo_order` VALUES (104, 1491830695216, 1, 26, 13998.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-10 21:24:55', '2017-04-10 21:24:55',0);
INSERT INTO `demo_order` VALUES (105, 1492089528889, 1, 29, 3299.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:18:48', '2017-04-13 21:18:48',0);
INSERT INTO `demo_order` VALUES (106, 1492090946105, 1, 29, 27894.00, 1, 0, 20, '2017-04-13 21:42:40', NULL, NULL, NULL, '2017-04-13 21:42:26', '2017-04-13 21:42:41',0);
INSERT INTO `demo_order` VALUES (107, 1492091003128, 1, 29, 8597.00, 1, 0, 20, '2017-04-13 21:43:38', NULL, NULL, NULL, '2017-04-13 21:43:23', '2017-04-13 21:43:38',0);
INSERT INTO `demo_order` VALUES (108, 1492091051313, 1, 29, 1999.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:11', '2017-04-13 21:44:11',0);
INSERT INTO `demo_order` VALUES (109, 1492091061513, 1, 29, 6598.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:21', '2017-04-13 21:44:21',0);
INSERT INTO `demo_order` VALUES (110, 1492091069563, 1, 29, 3299.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:29', '2017-04-13 21:44:29',0);
INSERT INTO `demo_order` VALUES (111, 1492091076073, 1, 29, 4299.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:36', '2017-04-13 21:44:36',0);
INSERT INTO `demo_order` VALUES (112, 1492091083720, 1, 29, 3299.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:43', '2017-04-13 21:44:43',0);
INSERT INTO `demo_order` VALUES (113, 1492091089794, 1, 29, 6999.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:49', '2017-04-13 21:44:49',0);
INSERT INTO `demo_order` VALUES (114, 1492091096400, 1, 29, 6598.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:44:56', '2017-04-13 21:44:56',0);
INSERT INTO `demo_order` VALUES (115, 1492091102371, 1, 29, 3299.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2017-04-13 21:45:02', '2017-04-13 21:45:02',0);
INSERT INTO `demo_order` VALUES (116, 1492091110004, 1, 29, 8598.00, 1, 0, 40, '2017-04-13 21:55:16', '2017-04-13 21:55:31', NULL, NULL, '2017-04-13 21:45:09', '2017-04-13 21:55:31',0);
INSERT INTO `demo_order` VALUES (117, 1492091141269, 1, 29, 22894.00, 1, 0, 20, '2017-04-13 21:46:06', NULL, NULL, NULL, '2017-04-13 21:45:41', '2017-04-13 21:46:07',0);

-- ----------------------------
-- Table structure for demo_order_item
-- ----------------------------
DROP TABLE IF EXISTS `demo_order_item`;
CREATE TABLE `demo_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20, 2) DEFAULT NULL COMMENT '生成订单时的商品单价,单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20, 2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_no_index`(`order_no`) USING BTREE,
  INDEX `order_no_user_id_index`(`user_id`, `order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_order_item
-- ----------------------------
INSERT INTO `demo_order_item` VALUES (113, 1, 1491753014256, 26, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', 6999.00, 2, 13998.00, '2017-04-09 23:50:14', '2017-04-09 23:50:14',0);
INSERT INTO `demo_order_item` VALUES (114, 1, 1491830695216, 26, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', 6999.00, 2, 13998.00, '2017-04-10 21:24:55', '2017-04-10 21:24:55',0);
INSERT INTO `demo_order_item` VALUES (115, 1, 1492089528889, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 1, 3299.00, '2017-04-13 21:18:48', '2017-04-13 21:18:48',0);
INSERT INTO `demo_order_item` VALUES (116, 1, 1492090946105, 29, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', 4299.00, 2, 8598.00, '2017-04-13 21:42:26', '2017-04-13 21:42:26',0);
INSERT INTO `demo_order_item` VALUES (117, 1, 1492090946105, 28, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', 1999.00, 1, 1999.00, '2017-04-13 21:42:26', '2017-04-13 21:42:26',0);
INSERT INTO `demo_order_item` VALUES (118, 1, 1492090946105, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 1, 3299.00, '2017-04-13 21:42:26', '2017-04-13 21:42:26',0);
INSERT INTO `demo_order_item` VALUES (119, 1, 1492090946105, 26, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', 6999.00, 2, 13998.00, '2017-04-13 21:42:26', '2017-04-13 21:42:26',0);
INSERT INTO `demo_order_item` VALUES (120, 1, 1492091003128, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 2, 6598.00, '2017-04-13 21:43:23', '2017-04-13 21:43:23',0);
INSERT INTO `demo_order_item` VALUES (121, 1, 1492091003128, 28, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', 1999.00, 1, 1999.00, '2017-04-13 21:43:23', '2017-04-13 21:43:23',0);
INSERT INTO `demo_order_item` VALUES (122, 1, 1492091051313, 28, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', 1999.00, 1, 1999.00, '2017-04-13 21:44:11', '2017-04-13 21:44:11',0);
INSERT INTO `demo_order_item` VALUES (123, 1, 1492091061513, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 2, 6598.00, '2017-04-13 21:44:21', '2017-04-13 21:44:21',0);
INSERT INTO `demo_order_item` VALUES (124, 1, 1492091069563, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 1, 3299.00, '2017-04-13 21:44:29', '2017-04-13 21:44:29',0);
INSERT INTO `demo_order_item` VALUES (125, 1, 1492091076073, 29, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', 4299.00, 1, 4299.00, '2017-04-13 21:44:36', '2017-04-13 21:44:36',0);
INSERT INTO `demo_order_item` VALUES (126, 1, 1492091083720, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 1, 3299.00, '2017-04-13 21:44:43', '2017-04-13 21:44:43',0);
INSERT INTO `demo_order_item` VALUES (127, 1, 1492091089794, 26, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', 6999.00, 1, 6999.00, '2017-04-13 21:44:49', '2017-04-13 21:44:49',0);
INSERT INTO `demo_order_item` VALUES (128, 1, 1492091096400, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 2, 6598.00, '2017-04-13 21:44:56', '2017-04-13 21:44:56',0);
INSERT INTO `demo_order_item` VALUES (129, 1, 1492091102371, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 1, 3299.00, '2017-04-13 21:45:02', '2017-04-13 21:45:02',0);
INSERT INTO `demo_order_item` VALUES (130, 1, 1492091110004, 29, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', 4299.00, 2, 8598.00, '2017-04-13 21:45:09', '2017-04-13 21:45:09',0);
INSERT INTO `demo_order_item` VALUES (131, 1, 1492091141269, 26, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', 6999.00, 1, 6999.00, '2017-04-13 21:45:41', '2017-04-13 21:45:41',0);
INSERT INTO `demo_order_item` VALUES (132, 1, 1492091141269, 27, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 3299.00, 1, 3299.00, '2017-04-13 21:45:41', '2017-04-13 21:45:41',0);
INSERT INTO `demo_order_item` VALUES (133, 1, 1492091141269, 29, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', 4299.00, 2, 8598.00, '2017-04-13 21:45:41', '2017-04-13 21:45:41',0);
INSERT INTO `demo_order_item` VALUES (134, 1, 1492091141269, 28, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', 1999.00, 2, 3998.00, '2017-04-13 21:45:41', '2017-04-13 21:45:41',0);

-- ----------------------------
-- Table structure for demo_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_pay_info`;
CREATE TABLE `demo_pay_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付宝支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_pay_info
-- ----------------------------
INSERT INTO `demo_pay_info` VALUES (53, 1, 1492090946105, 1, '2017041321001004300200116250', 'WAIT_BUYER_PAY', '2017-04-13 21:42:33', '2017-04-13 21:42:33',0);
INSERT INTO `demo_pay_info` VALUES (54, 1, 1492090946105, 1, '2017041321001004300200116250', 'TRADE_SUCCESS', '2017-04-13 21:42:41', '2017-04-13 21:42:41',0);
INSERT INTO `demo_pay_info` VALUES (55, 1, 1492091003128, 1, '2017041321001004300200116251', 'WAIT_BUYER_PAY', '2017-04-13 21:43:31', '2017-04-13 21:43:31',0);
INSERT INTO `demo_pay_info` VALUES (56, 1, 1492091003128, 1, '2017041321001004300200116251', 'TRADE_SUCCESS', '2017-04-13 21:43:38', '2017-04-13 21:43:38',0);
INSERT INTO `demo_pay_info` VALUES (57, 1, 1492091141269, 1, '2017041321001004300200116252', 'WAIT_BUYER_PAY', '2017-04-13 21:45:59', '2017-04-13 21:45:59',0);
INSERT INTO `demo_pay_info` VALUES (58, 1, 1492091141269, 1, '2017041321001004300200116252', 'TRADE_SUCCESS', '2017-04-13 21:46:07', '2017-04-13 21:46:07',0);
INSERT INTO `demo_pay_info` VALUES (59, 1, 1492091110004, 1, '2017041321001004300200116396', 'WAIT_BUYER_PAY', '2017-04-13 21:55:08', '2017-04-13 21:55:08',0);
INSERT INTO `demo_pay_info` VALUES (60, 1, 1492091110004, 1, '2017041321001004300200116396', 'TRADE_SUCCESS', '2017-04-13 21:55:17', '2017-04-13 21:55:17',0);

-- ----------------------------
-- Table structure for demo_product
-- ----------------------------
DROP TABLE IF EXISTS `demo_product`;
CREATE TABLE `demo_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应demo_category表的主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '图片地址,json格式,扩展用',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商品详情',
  `price` decimal(20, 2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT 1 COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_product
-- ----------------------------
INSERT INTO `demo_product` VALUES (26, 100002, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', 'iPhone 7,现更以红色呈现.', 'test', 'test', 'test', 6999.00, 9991, 1, NULL, '2017-04-13 21:45:41',0);
INSERT INTO `demo_product` VALUES (27, 100006, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', '送品牌烤箱,五一大促', 'test', 'test', 'test', 3299.00, 8876, 1, '2017-04-13 18:51:54', '2017-04-13 21:45:41',0);
INSERT INTO `demo_product` VALUES (28, 100012, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', 'NOVA青春版1999元', 'test', 'test', 'test', 1999.00, 9994, 1, '2017-04-13 18:57:18', '2017-04-13 21:45:41',0);
INSERT INTO `demo_product` VALUES (29, 100008, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '门店机型 德邦送货', 'test', 'test', 'test', 4299.00, 9993, 1, '2017-04-13 19:07:47', '2017-04-13 21:45:41',0);

-- ----------------------------
-- Table structure for demo_shipping
-- ----------------------------
DROP TABLE IF EXISTS `demo_shipping`;
CREATE TABLE `demo_shipping`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_shipping
-- ----------------------------
INSERT INTO `demo_shipping` VALUES (4, 13, 'geely', '010', '18688888888', '北京', '北京市', '海淀区', '中关村', '100000', '2017-01-22 14:26:25', '2017-01-22 14:26:25',0);
INSERT INTO `demo_shipping` VALUES (7, 17, 'Rosen', '13800138000', '13800138000', '北京', '北京', NULL, '中关村', '100000', '2017-03-29 12:11:01', '2017-03-29 12:11:01',0);
INSERT INTO `demo_shipping` VALUES (29, 1, '吉利', '13800138000', '13800138000', '北京', '北京', '海淀区', '海淀区中关村', '100000', '2017-04-09 18:33:32', '2017-04-09 18:33:32',0);

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码,MD5加密',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `question` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1, 'admin', '427338237BD929443EC5D48E24FD2B1A', 'admin@happymmall.com', '13800138000', '问题', '答案', 1, '2016-11-06 16:56:45', '2017-04-04 19:27:36',0);

SET FOREIGN_KEY_CHECKS = 1;
