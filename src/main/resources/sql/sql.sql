/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 192.168.96.3:3306
 Source Schema         : expression

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/09/2020 18:33:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_id` bigint(20) NULL DEFAULT NULL,
  `tree_order` bigint(20) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '#', 'fa fa-fw fa-cogs', '系统管理', 0, 1, '#', '1');
INSERT INTO `menu` VALUES (2, NULL, 'fa fa-fw fa-tree', '菜单管理', 1, 1, 'treeList', '1');
INSERT INTO `menu` VALUES (7, 'groupManager', 'fa fa-fw fa-group', '组织架构', 1, 2, 'groupList', '1');
INSERT INTO `menu` VALUES (8, 'userRoleManager', 'fa fa-fw fa-user-secret', '角色管理', 1, 3, 'userRoleList', '1');
INSERT INTO `menu` VALUES (9, 'userManager', 'fa fa-fw fa-user', '用户维护', 1, 4, 'userList', '1');
INSERT INTO `menu` VALUES (10, 'dictManager', 'fa fa-fw fa-book', '字典维护', 1, 5, 'dictList', '0');
INSERT INTO `menu` VALUES (17, 'sysManager', 'fa fa-fw fa-desktop', '菜单维护', 0, 2, '#', '1');
INSERT INTO `menu` VALUES (23, 'foodTypeManager', 'fa fa-fw fa-tree', '菜品维护', 17, 1, 'foodTypeList', '1');
INSERT INTO `menu` VALUES (24, 'dinerListManager', 'fa fa-fw fa-book', '菜单管理', 17, 2, 'dinerList', '1');
INSERT INTO `menu` VALUES (25, 'orderManager', 'fa fa-fw fa-book', '订单管理', 17, 3, 'orderList', '1');
INSERT INTO `menu` VALUES (26, 'diningTableManager', 'fa fa-fw fa-tree', '餐桌维护', 17, 4, 'diningTableList', '1');
INSERT INTO `menu` VALUES (27, 'userManagerTop', 'fa fa-user', '用户维护', 0, 3, '#', '1');
INSERT INTO `menu` VALUES (28, 'mobileUserManager', 'fa fa-user-circle', '移动用户', 27, 1, 'mobileUser', '1');
INSERT INTO `menu` VALUES (29, 'scoreDetailManager', 'fa fa-user-circle', '积分明细', 27, 2, 'scoreDetailList', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '街道地址',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NOT NULL COMMENT '生日',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `image_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `image` blob NULL COMMENT '图片路径',
  `last_login_time` date NULL DEFAULT NULL COMMENT '上次登陆时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (9, '张三', 'zhangsan', '123456', '13856888888', '86868686@qq.com', '山西省', '太原市', '小店区', '山西省太原市小店区街道地址', 1, NULL, '0000-00-00', NULL, NULL, NULL, '2020-09-24');
INSERT INTO `user` VALUES (10, '张无忌', 'zhangwuji', '123456', '13856888888', '86868686@qq.com', '北京市', '北京市市辖区', '东城区', '北京市北京市市辖区东城区街道地址', 1, NULL, '0000-00-00', NULL, NULL, NULL, '2020-09-24');
INSERT INTO `user` VALUES (76, '广晴衙', 'Ueanc', NULL, '13221974460', NULL, '湖北省', NULL, NULL, '江苏省张家港市肿冷劲址绝喧掉有限公司', NULL, 2, '0000-00-00', 'Shantou Computer Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (77, '雪孕', 'Twdl', NULL, '18040669743', NULL, '台湾省', NULL, NULL, '中国陕西省铜川市豁喻袍莫慢庵有限公司', NULL, 2, '0000-00-00', 'Xining Hardware Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (78, '戴胡溃', 'Ufkknlvk', NULL, '18042722866', NULL, '新疆维吾尔自治区', NULL, NULL, '中国澳门特别行政区圣安多尼堂区坏离梳勿慕靖悍有限公司', NULL, 1, '0000-00-00', 'Chuzhou Education Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (79, '佟胡垢', 'Myxripcn', NULL, '15968386813', NULL, '新疆维吾尔自治区', NULL, NULL, '中国海南省五指山市拱贫铺纠桅股份有限公司', NULL, 1, '0000-00-00', 'Yuncheng Software Group Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (80, '说侈亩', 'Gchg', NULL, '15740800594', NULL, '内蒙古自治区', NULL, NULL, '中国西藏自治区日喀则市舒缚逆股份有限公司', NULL, 2, '0000-00-00', 'Zhoushan Technology Co.Ltd.', NULL, NULL, NULL);
INSERT INTO `user` VALUES (81, '肖辱沟', 'Tyvh', NULL, '18417993555', NULL, '上海市', NULL, NULL, '中国河南省周口市脾刷锰庹密股份有限公司', NULL, 2, '0000-00-00', 'Hengshui Education Co.Ltd.', NULL, NULL, NULL);
INSERT INTO `user` VALUES (82, '武暮', 'Lwqkb Sckpzoej', NULL, '15657618577', NULL, '辽宁省', NULL, NULL, '广西壮族自治区南宁市北富孽纽射期有限公司', NULL, 2, '0000-00-00', 'Ganzhou Telectronic Commerce Co.Ltd.', NULL, NULL, NULL);
INSERT INTO `user` VALUES (83, '五鲍', 'Pxdmqrq Cqsaijn', NULL, '13513771297', NULL, '广东省', NULL, NULL, '中国湖北省宜城市斥笋萤哩课蒉磷集团有限公司', NULL, 2, '0000-00-00', 'Sanming Telectronic Commerce Company Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (84, '吾钳诅', 'Drvk', NULL, '18722270468', NULL, '台湾省', NULL, NULL, '中国江苏省宜兴市鬼想歌集团有限公司', NULL, 2, '0000-00-00', 'Liuzhou Seed Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (85, '韵挖输', 'Uhzcrk', NULL, '15658881006', NULL, '台湾省', NULL, NULL, '新疆维吾尔自治区阿图什市肤误贪胯括滚马匾股份有限公司', NULL, 1, '0000-00-00', 'Quzhou Game Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (86, '巴谣', 'Prspad', NULL, '13251003740', NULL, '新疆维吾尔自治区', NULL, NULL, '山东省日照市足叁抠漾喊穆悔湃技术有限公司', NULL, 2, '0000-00-00', 'Hefei Computer Group Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (87, '房慢考', 'Yuwv', NULL, '15726905424', NULL, '云南省', NULL, NULL, '中国山西省汾阳市扎门股份有限公司', NULL, 1, '0000-00-00', 'Bengbu Finance  Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (88, '释打勇', 'Qprpqda', NULL, '15177521971', NULL, '四川省', NULL, NULL, '中国辽宁省营口市眠付技术有限公司', NULL, 1, '0000-00-00', 'Weihai Adviertisement Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (89, '森张', 'Rauz', NULL, '13890668828', NULL, '甘肃省', NULL, NULL, '广东省中山市如仔奕距剃钱技术有限公司', NULL, 1, '0000-00-00', 'Harbin Internet Company Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (90, '谌拆', 'Brcpyb', NULL, '13948725478', NULL, '云南省', NULL, NULL, '贵州省清镇市扯钞絮跺愚棚邬集团有限公司', NULL, 1, '0000-00-00', 'Zhanjiang Data Service Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (91, '臧蒋辐', 'Knee', NULL, '18369727446', NULL, '西藏自治区', NULL, NULL, '吉林省洮南市乜谊耘轴快有限公司', NULL, 1, '0000-00-00', 'Dali Software Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (92, '兴式', 'Obupisx', NULL, '15774892562', NULL, '海南省', NULL, NULL, '吉林省白山市眨扩惰长室再齐岔技术有限公司', NULL, 2, '0000-00-00', 'Siping Technology Company Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (93, '仆页矾', 'Ujhlxbv', NULL, '15324820973', NULL, '福建省', NULL, NULL, '澳门特别行政区圣方济各堂区瞒受件绽溅鞠胞集团有限公司', NULL, 2, '0000-00-00', 'DaXingAnLing Media Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (94, '撒智有', 'Horeyiy', NULL, '18536833172', NULL, '云南省', NULL, NULL, '黑龙江省尚志市他循铆公膳顽股份有限公司', NULL, 1, '0000-00-00', 'Shiyan Information Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (95, '祖掘典', 'Xwennaq', NULL, '18294897565', NULL, '西藏自治区', NULL, NULL, '江苏省南京市述闭怯桓股份有限公司', NULL, 2, '0000-00-00', 'Yiyang Technology Group Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (96, '谷慨', 'Euzmij', NULL, '15961650020', NULL, '海南省', NULL, NULL, '湖南省临湘市邹侈侍痹嘴集团有限公司', NULL, 1, '0000-00-00', 'Zhoushan Finance  Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (97, '乌孙债', 'Iedp', NULL, '15232483743', NULL, '台湾省', NULL, NULL, '江苏省南京市枕嚎股份有限公司', NULL, 2, '0000-00-00', 'Huangnan Sysetm Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (98, '佘虽', 'Deaxa', NULL, '13529533010', NULL, '贵州省', NULL, NULL, '福建省福州市铸鲸缤嗜有限公司', NULL, 1, '0000-00-00', 'Jinan Real Estate Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (99, '茂护翔', 'Fhpf', NULL, '18559560054', NULL, '江西省', NULL, NULL, '云南省开远市惹拒响棺惨廉囱有限公司', NULL, 2, '0000-00-00', 'Chaozhou Software Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (100, '第漱霞', 'Kxno', NULL, '13820749463', NULL, '广西壮族自治区', NULL, NULL, '宁夏回族自治区青铜峡市秕好集团有限公司', NULL, 1, '0000-00-00', 'Beihai Game Company Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (101, '褚怒', 'Owfsi', NULL, '18469491246', NULL, '天津市', NULL, NULL, '福建省福清市郊临害脐集团有限公司', NULL, 2, '0000-00-00', 'Qingdao Real Estate Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (102, '邴衷股', 'Gwlrd', NULL, '13058149559', NULL, '上海市', NULL, NULL, '中国浙江省慈溪市考谅证技术有限公司', NULL, 1, '0000-00-00', 'Hinggan Seed Co.Ltd', NULL, NULL, NULL);
INSERT INTO `user` VALUES (103, '刘帖愚', 'Ucapeslc', NULL, '18824081873', NULL, '福建省', NULL, NULL, '中国山西省原平市盅沮拷技术有限公司', NULL, 1, '0000-00-00', 'Taiyuan Hardware Group Company', NULL, NULL, NULL);
INSERT INTO `user` VALUES (104, '尉迟档', 'Ldpunt', NULL, '13987078178', NULL, '江苏省', NULL, NULL, '中国辽宁省铁岭市破躯巴盗股份有限公司', NULL, 1, '0000-00-00', 'Meizhou Seed Company Limited', NULL, NULL, NULL);
INSERT INTO `user` VALUES (105, '翦施', 'Wnidk', NULL, '18625896205', NULL, '新疆维吾尔自治区', NULL, NULL, '中国江西省樟树市辩秧晁集团有限公司', NULL, 2, '0000-00-00', 'Anqing Seed Co.Ltd', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES (1, '我的好友');
INSERT INTO `user_group` VALUES (2, '我喜欢的');

-- ----------------------------
-- Table structure for user_user
-- ----------------------------
DROP TABLE IF EXISTS `user_user`;
CREATE TABLE `user_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `friend_id` int(11) NULL DEFAULT NULL COMMENT '朋友id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user
-- ----------------------------
INSERT INTO `user_user` VALUES (1, 9, 10, '1');
INSERT INTO `user_user` VALUES (2, 9, 76, '1');
INSERT INTO `user_user` VALUES (3, 9, 77, '1');
INSERT INTO `user_user` VALUES (4, 9, 101, '2');
INSERT INTO `user_user` VALUES (5, 9, 102, '2');
INSERT INTO `user_user` VALUES (6, 9, 103, '2');
INSERT INTO `user_user` VALUES (7, 9, 104, '2');
INSERT INTO `user_user` VALUES (8, 9, 105, '2');

SET FOREIGN_KEY_CHECKS = 1;
