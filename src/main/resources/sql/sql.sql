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

 Date: 29/09/2020 18:52:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_collect
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `collect_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏名称',
  `user_id` int(9) NULL DEFAULT NULL COMMENT '用户id',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否有效（1：有效，0：无效）',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_date` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collect
-- ----------------------------
INSERT INTO `t_collect` VALUES (1, '默认收藏', NULL, 1, '2020-09-29 10:23:06', NULL);

-- ----------------------------
-- Table structure for t_collect_content
-- ----------------------------
DROP TABLE IF EXISTS `t_collect_content`;
CREATE TABLE `t_collect_content`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(9) NULL DEFAULT NULL COMMENT '用户id',
  `friend_id` int(11) NULL DEFAULT NULL,
  `content_id` int(9) NULL DEFAULT NULL COMMENT '内容id',
  `collect_id` int(9) NULL DEFAULT NULL COMMENT '收藏夹id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xxx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'user_id 收藏friend_id content_id 放在collect_id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collect_content
-- ----------------------------
INSERT INTO `t_collect_content` VALUES (7, 9, NULL, 43, 1, '2020-09-29 03:22:47');
INSERT INTO `t_collect_content` VALUES (8, 9, NULL, 42, 1, '2020-09-29 03:22:48');
INSERT INTO `t_collect_content` VALUES (10, 9, NULL, 44, 1, '2020-09-29 05:46:00');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `comment_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论',
  `friend_id` int(9) NULL DEFAULT NULL COMMENT '好友id',
  `content_id` int(9) NULL DEFAULT NULL COMMENT '内容id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'user_id 评论(comment_text) friend_id content_id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 9, '精彩！！！！！', 9, 42, '2020-09-29 10:32:02');
INSERT INTO `t_comment` VALUES (2, 9, '66666666', 9, 42, '2020-09-29 10:32:02');
INSERT INTO `t_comment` VALUES (3, 9, '笑死了', 9, 42, '2020-09-29 10:32:02');
INSERT INTO `t_comment` VALUES (4, 9, '66666666', 9, 43, '2020-09-29 10:32:02');
INSERT INTO `t_comment` VALUES (5, 9, '7777777', 9, 43, '2020-09-29 10:32:02');

-- ----------------------------
-- Table structure for t_content
-- ----------------------------
DROP TABLE IF EXISTS `t_content`;
CREATE TABLE `t_content`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(9) NULL DEFAULT NULL COMMENT '用户id',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否有效（1：有效，0：无效）',
  `p_id` int(9) NULL DEFAULT NULL COMMENT '转发内容id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布内容',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_date` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `f_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'user_id 发布/删除(status) content \r\nuser_id 转发(p_id) 发布/删除(status) content' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_content
-- ----------------------------
INSERT INTO `t_content` VALUES (42, 9, 1, NULL, '', '2020-09-28 08:20:48', NULL);
INSERT INTO `t_content` VALUES (43, 9, 1, NULL, '', '2020-09-28 08:21:01', NULL);
INSERT INTO `t_content` VALUES (44, 9, 1, NULL, '1231231231231', '2020-09-29 02:46:24', NULL);
INSERT INTO `t_content` VALUES (47, 9, 1, 44, 'adsad', '2020-09-29 07:36:10', NULL);
INSERT INTO `t_content` VALUES (48, 9, 1, 47, '', '2020-09-29 07:40:02', NULL);
INSERT INTO `t_content` VALUES (49, 9, 1, 44, '', '2020-09-29 07:40:18', NULL);
INSERT INTO `t_content` VALUES (50, 9, 1, 44, '12312323123', '2020-09-29 10:19:32', NULL);
INSERT INTO `t_content` VALUES (51, 76, 1, NULL, '还有一天就放假啦', '2020-09-29 10:27:51', NULL);
INSERT INTO `t_content` VALUES (52, 76, 1, 49, '123123123123', '2020-09-29 10:28:16', NULL);
INSERT INTO `t_content` VALUES (53, 76, 1, 51, '的', '2020-09-29 10:29:07', NULL);
INSERT INTO `t_content` VALUES (54, 76, 1, 51, '顶顶顶顶顶', '2020-09-29 10:29:19', NULL);
INSERT INTO `t_content` VALUES (55, 76, 1, NULL, '', '2020-09-29 10:32:24', NULL);
INSERT INTO `t_content` VALUES (56, 76, 1, NULL, '', '2020-09-29 10:35:18', NULL);
INSERT INTO `t_content` VALUES (57, 76, 1, 55, '转发', '2020-09-29 10:50:51', NULL);
INSERT INTO `t_content` VALUES (58, 76, 1, 54, '转发', '2020-09-29 10:51:05', NULL);
INSERT INTO `t_content` VALUES (59, 76, 1, 54, '嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻', '2020-09-29 10:51:21', NULL);

-- ----------------------------
-- Table structure for t_content_file
-- ----------------------------
DROP TABLE IF EXISTS `t_content_file`;
CREATE TABLE `t_content_file`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content_id` int(9) NULL DEFAULT NULL COMMENT '内容id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_content_id`(`content_id`) USING BTREE,
  CONSTRAINT `f_content_id` FOREIGN KEY (`content_id`) REFERENCES `t_content` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_content_file
-- ----------------------------
INSERT INTO `t_content_file` VALUES (29, 42, 'QQ图片20200927091542 - 副本 (2).jpg', 'F:\\workspace\\upload\\3e9aaeaea2f546d1917bfd16e590067f.jpg');
INSERT INTO `t_content_file` VALUES (30, 42, 'QQ图片20200927091542 - 副本 (3).jpg', 'F:\\workspace\\upload\\aaa0589d235e4fbb97f5b69324a876e8.jpg');
INSERT INTO `t_content_file` VALUES (31, 42, 'QQ图片20200927091542 - 副本 (4).jpg', 'F:\\workspace\\upload\\ac4529dfc07c4ab09079c9304d20520c.jpg');
INSERT INTO `t_content_file` VALUES (32, 42, 'QQ图片20200927091542 - 副本.jpg', 'F:\\workspace\\upload\\aeec14eb2e314cf79202a2a6a66940e9.jpg');
INSERT INTO `t_content_file` VALUES (33, 42, 'QQ图片20200927091542.jpg', 'F:\\workspace\\upload\\ca1aa143fd674d66805e8010241e95eb.jpg');
INSERT INTO `t_content_file` VALUES (34, 42, '微信图片_20200927091341 - 副本 (2).jpg', 'F:\\workspace\\upload\\8ab0934e1d674a3fa0d247aff22be3f4.jpg');
INSERT INTO `t_content_file` VALUES (35, 42, '微信图片_20200927091341 - 副本 (3).jpg', 'F:\\workspace\\upload\\a876f4f2d0a1456086d4010e2c159135.jpg');
INSERT INTO `t_content_file` VALUES (36, 42, '微信图片_20200927091341 - 副本 (4).jpg', 'F:\\workspace\\upload\\52e986e7da2848e49777aaec14f4cffc.jpg');
INSERT INTO `t_content_file` VALUES (37, 42, '微信图片_20200927091341 - 副本.jpg', 'F:\\workspace\\upload\\7c01ef9762e84bf8b7e010843534ee6e.jpg');
INSERT INTO `t_content_file` VALUES (38, 43, 'asteroid - 副本.png', 'F:\\workspace\\upload\\9c49e3dbb29c417db98d9cebc22f0782.png');
INSERT INTO `t_content_file` VALUES (39, 43, 'asteroid.png', 'F:\\workspace\\upload\\9414082a1c58468785cf9d0d346853f2.png');
INSERT INTO `t_content_file` VALUES (40, 43, 'Frog - 副本.png', 'F:\\workspace\\upload\\d73ccded681245c58656d30c74a9fe92.png');
INSERT INTO `t_content_file` VALUES (41, 43, 'Frog.png', 'F:\\workspace\\upload\\0ae53e97a80e49c78b363affbd982457.png');
INSERT INTO `t_content_file` VALUES (42, 43, 'QQ图片20200927091542 - 副本 (3).jpg', 'F:\\workspace\\upload\\ab0a2615229844d5857d5776389b21f5.jpg');
INSERT INTO `t_content_file` VALUES (43, 43, 'QQ图片20200927091542 - 副本 (4).jpg', 'F:\\workspace\\upload\\3c9bd8679c16430b829cc228faad0e6f.jpg');
INSERT INTO `t_content_file` VALUES (44, 43, 'QQ图片20200927091542 - 副本.jpg', 'F:\\workspace\\upload\\2433d7bfb53a40a59fa75ef55733add1.jpg');
INSERT INTO `t_content_file` VALUES (45, 43, 'QQ图片20200927091542.jpg', 'F:\\workspace\\upload\\71083b8ffc284c1fb0594aedc41862ea.jpg');
INSERT INTO `t_content_file` VALUES (46, 51, '微信图片_20200929091723.jpg', 'F:\\workspace\\upload\\dba70f4a7a4e4d63bba46ab632254f03.jpg');
INSERT INTO `t_content_file` VALUES (47, 55, 'asteroid.png', 'F:\\workspace\\upload\\05c05cdb6eb84cb9bf9534a32eb062f8.png');
INSERT INTO `t_content_file` VALUES (48, 55, 'Frog - 副本.png', 'F:\\workspace\\upload\\ad722824114040789b5d51076521cfdc.png');
INSERT INTO `t_content_file` VALUES (49, 55, 'Frog.png', 'F:\\workspace\\upload\\c267eaf03e87452289848da6a26b024a.png');
INSERT INTO `t_content_file` VALUES (50, 56, '微信图片_20200817090935.jpg', 'F:\\workspace\\upload\\c65f8f2e67914b0689f693aeccbac5ac.jpg');
INSERT INTO `t_content_file` VALUES (51, 56, '微信图片_20200927091341.jpg', 'F:\\workspace\\upload\\88ff0447b87c477d98afb28c116a0113.jpg');
INSERT INTO `t_content_file` VALUES (52, 56, '微信图片_20200929091723.jpg', 'F:\\workspace\\upload\\d5c8432a9e574909ae58642020656120.jpg');

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否有效（1：有效，0：无效）',
  `friend_id` int(9) NOT NULL COMMENT '好友id',
  `group_id` int(9) NOT NULL COMMENT '分组id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_date` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_group_id`(`group_id`) USING BTREE,
  INDEX `t_frient_id`(`friend_id`) USING BTREE,
  INDEX `t_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `f_group_id` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_frient_id` FOREIGN KEY (`friend_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'user_id 添加/删除(status) friend_id 分到了 group_id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES (1, 9, 1, 10, 1, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (2, 9, 1, 76, 1, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (3, 9, 1, 77, 1, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (4, 9, 1, 101, 2, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (5, 9, 1, 102, 2, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (6, 9, 1, 103, 2, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (7, 9, 1, 104, 2, '2020-09-28 10:45:52', NULL);
INSERT INTO `t_friend` VALUES (8, 9, 1, 105, 2, '2020-09-28 10:45:52', NULL);

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否有效（1：有效，0：无效）',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_date` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES (1, '我的好友', 1, '2020-09-28 10:46:39', NULL);
INSERT INTO `t_group` VALUES (2, '我喜欢的', 1, '2020-09-28 10:46:39', NULL);

-- ----------------------------
-- Table structure for t_thumb
-- ----------------------------
DROP TABLE IF EXISTS `t_thumb`;
CREATE TABLE `t_thumb`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(9) NULL DEFAULT NULL COMMENT '用户id',
  `friend_id` int(9) NULL DEFAULT NULL COMMENT '好友id',
  `content_id` int(9) NULL DEFAULT NULL COMMENT '内容id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'user_id 点赞了 friend_id 的 发布content_id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_thumb
-- ----------------------------
INSERT INTO `t_thumb` VALUES (5, 9, 9, 43, '2020-09-29 02:44:11');
INSERT INTO `t_thumb` VALUES (8, 9, 9, 42, '2020-09-29 03:23:05');
INSERT INTO `t_thumb` VALUES (10, 9, 9, 44, '2020-09-29 05:48:07');
INSERT INTO `t_thumb` VALUES (11, 76, 76, 54, '2020-09-29 10:29:36');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '街道地址',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否有效（1：有效，0：无效）',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `image_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `image_path` blob NULL COMMENT '图片路径',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登陆时间',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_date` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (9, '张三', 'zhangsan', '123456', '13856888888', '86868686@qq.com', '山西省', '太原市', '小店区', '山西省太原市小店区街道地址', 1, NULL, '0000-00-00', NULL, NULL, NULL, '2020-09-24 00:00:00', '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (10, '张无忌', 'zhangwuji', '123456', '13856888888', '86868686@qq.com', '北京市', '北京市市辖区', '东城区', '北京市北京市市辖区东城区街道地址', 1, NULL, '0000-00-00', NULL, NULL, NULL, '2020-09-24 00:00:00', '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (76, '广晴衙', 'Ueanc', NULL, '13221974460', NULL, '湖北省', NULL, NULL, '江苏省张家港市肿冷劲址绝喧掉有限公司', 1, 2, '0000-00-00', 'Shantou Computer Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (77, '雪孕', 'Twdl', NULL, '18040669743', NULL, '台湾省', NULL, NULL, '中国陕西省铜川市豁喻袍莫慢庵有限公司', 1, 2, '0000-00-00', 'Xining Hardware Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (78, '戴胡溃', 'Ufkknlvk', NULL, '18042722866', NULL, '新疆维吾尔自治区', NULL, NULL, '中国澳门特别行政区圣安多尼堂区坏离梳勿慕靖悍有限公司', 1, 1, '0000-00-00', 'Chuzhou Education Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (79, '佟胡垢', 'Myxripcn', NULL, '15968386813', NULL, '新疆维吾尔自治区', NULL, NULL, '中国海南省五指山市拱贫铺纠桅股份有限公司', 1, 1, '0000-00-00', 'Yuncheng Software Group Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (80, '说侈亩', 'Gchg', NULL, '15740800594', NULL, '内蒙古自治区', NULL, NULL, '中国西藏自治区日喀则市舒缚逆股份有限公司', 1, 2, '0000-00-00', 'Zhoushan Technology Co.Ltd.', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (81, '肖辱沟', 'Tyvh', NULL, '18417993555', NULL, '上海市', NULL, NULL, '中国河南省周口市脾刷锰庹密股份有限公司', 1, 2, '0000-00-00', 'Hengshui Education Co.Ltd.', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (82, '武暮', 'Lwqkb Sckpzoej', NULL, '15657618577', NULL, '辽宁省', NULL, NULL, '广西壮族自治区南宁市北富孽纽射期有限公司', 1, 2, '0000-00-00', 'Ganzhou Telectronic Commerce Co.Ltd.', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (83, '五鲍', 'Pxdmqrq Cqsaijn', NULL, '13513771297', NULL, '广东省', NULL, NULL, '中国湖北省宜城市斥笋萤哩课蒉磷集团有限公司', 1, 2, '0000-00-00', 'Sanming Telectronic Commerce Company Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (84, '吾钳诅', 'Drvk', NULL, '18722270468', NULL, '台湾省', NULL, NULL, '中国江苏省宜兴市鬼想歌集团有限公司', 1, 2, '0000-00-00', 'Liuzhou Seed Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (85, '韵挖输', 'Uhzcrk', NULL, '15658881006', NULL, '台湾省', NULL, NULL, '新疆维吾尔自治区阿图什市肤误贪胯括滚马匾股份有限公司', 1, 1, '0000-00-00', 'Quzhou Game Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (86, '巴谣', 'Prspad', NULL, '13251003740', NULL, '新疆维吾尔自治区', NULL, NULL, '山东省日照市足叁抠漾喊穆悔湃技术有限公司', 1, 2, '0000-00-00', 'Hefei Computer Group Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (87, '房慢考', 'Yuwv', NULL, '15726905424', NULL, '云南省', NULL, NULL, '中国山西省汾阳市扎门股份有限公司', 1, 1, '0000-00-00', 'Bengbu Finance  Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (88, '释打勇', 'Qprpqda', NULL, '15177521971', NULL, '四川省', NULL, NULL, '中国辽宁省营口市眠付技术有限公司', 1, 1, '0000-00-00', 'Weihai Adviertisement Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (89, '森张', 'Rauz', NULL, '13890668828', NULL, '甘肃省', NULL, NULL, '广东省中山市如仔奕距剃钱技术有限公司', 1, 1, '0000-00-00', 'Harbin Internet Company Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (90, '谌拆', 'Brcpyb', NULL, '13948725478', NULL, '云南省', NULL, NULL, '贵州省清镇市扯钞絮跺愚棚邬集团有限公司', 1, 1, '0000-00-00', 'Zhanjiang Data Service Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (91, '臧蒋辐', 'Knee', NULL, '18369727446', NULL, '西藏自治区', NULL, NULL, '吉林省洮南市乜谊耘轴快有限公司', 1, 1, '0000-00-00', 'Dali Software Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (92, '兴式', 'Obupisx', NULL, '15774892562', NULL, '海南省', NULL, NULL, '吉林省白山市眨扩惰长室再齐岔技术有限公司', 1, 2, '0000-00-00', 'Siping Technology Company Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (93, '仆页矾', 'Ujhlxbv', NULL, '15324820973', NULL, '福建省', NULL, NULL, '澳门特别行政区圣方济各堂区瞒受件绽溅鞠胞集团有限公司', 1, 2, '0000-00-00', 'DaXingAnLing Media Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (94, '撒智有', 'Horeyiy', NULL, '18536833172', NULL, '云南省', NULL, NULL, '黑龙江省尚志市他循铆公膳顽股份有限公司', 1, 1, '0000-00-00', 'Shiyan Information Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (95, '祖掘典', 'Xwennaq', NULL, '18294897565', NULL, '西藏自治区', NULL, NULL, '江苏省南京市述闭怯桓股份有限公司', 1, 2, '0000-00-00', 'Yiyang Technology Group Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (96, '谷慨', 'Euzmij', NULL, '15961650020', NULL, '海南省', NULL, NULL, '湖南省临湘市邹侈侍痹嘴集团有限公司', 1, 1, '0000-00-00', 'Zhoushan Finance  Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (97, '乌孙债', 'Iedp', NULL, '15232483743', NULL, '台湾省', NULL, NULL, '江苏省南京市枕嚎股份有限公司', 1, 2, '0000-00-00', 'Huangnan Sysetm Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (98, '佘虽', 'Deaxa', NULL, '13529533010', NULL, '贵州省', NULL, NULL, '福建省福州市铸鲸缤嗜有限公司', 1, 1, '0000-00-00', 'Jinan Real Estate Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (99, '茂护翔', 'Fhpf', NULL, '18559560054', NULL, '江西省', NULL, NULL, '云南省开远市惹拒响棺惨廉囱有限公司', 1, 2, '0000-00-00', 'Chaozhou Software Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (100, '第漱霞', 'Kxno', NULL, '13820749463', NULL, '广西壮族自治区', NULL, NULL, '宁夏回族自治区青铜峡市秕好集团有限公司', 1, 1, '0000-00-00', 'Beihai Game Company Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (101, '褚怒', 'Owfsi', NULL, '18469491246', NULL, '天津市', NULL, NULL, '福建省福清市郊临害脐集团有限公司', 1, 2, '0000-00-00', 'Qingdao Real Estate Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (102, '邴衷股', 'Gwlrd', NULL, '13058149559', NULL, '上海市', NULL, NULL, '中国浙江省慈溪市考谅证技术有限公司', 1, 1, '0000-00-00', 'Hinggan Seed Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (103, '刘帖愚', 'Ucapeslc', NULL, '18824081873', NULL, '福建省', NULL, NULL, '中国山西省原平市盅沮拷技术有限公司', 1, 1, '0000-00-00', 'Taiyuan Hardware Group Company', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (104, '尉迟档', 'Ldpunt', NULL, '13987078178', NULL, '江苏省', NULL, NULL, '中国辽宁省铁岭市破躯巴盗股份有限公司', 1, 1, '0000-00-00', 'Meizhou Seed Company Limited', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (105, '翦施', 'Wnidk', NULL, '18625896205', NULL, '新疆维吾尔自治区', NULL, NULL, '中国江西省樟树市辩秧晁集团有限公司', 1, 2, '0000-00-00', 'Anqing Seed Co.Ltd', NULL, NULL, NULL, '2020-09-28 10:45:28', NULL);
INSERT INTO `t_user` VALUES (106, '成龙', 'Jackie Chen', '123456', '13856888888', '86868686@qq.com', '北京市', '北京市市辖区', '东城区', '北京市北京市市辖区东城区街道地址', 1, 1, NULL, 'image_path', NULL, NULL, NULL, '2020-09-28 07:11:54', NULL);

SET FOREIGN_KEY_CHECKS = 1;
