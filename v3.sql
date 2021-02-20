/*
Navicat MySQL Data Transfer

Source Server         : BIYE
Source Server Version : 50172
Source Host           : localhost:3306
Source Database       : v3

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2020-05-16 12:25:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aa
-- ----------------------------
DROP TABLE IF EXISTS `aa`;
CREATE TABLE `aa` (
  `aa` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of aa
-- ----------------------------

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `userid` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `county` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `areaCode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `postalCode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isDefault` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `addressDetail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for assess
-- ----------------------------
DROP TABLE IF EXISTS `assess`;
CREATE TABLE `assess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsid` int(11) NOT NULL,
  `assess` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startNum` int(11) DEFAULT NULL,
  `sendTime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of assess
-- ----------------------------
INSERT INTO `assess` VALUES ('2', '54', '', null, null, null);

-- ----------------------------
-- Table structure for bought
-- ----------------------------
DROP TABLE IF EXISTS `bought`;
CREATE TABLE `bought` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `bought` longtext COLLATE utf8_unicode_ci,
  `unpaid` longtext COLLATE utf8_unicode_ci,
  `unreceived` longtext COLLATE utf8_unicode_ci,
  `unassess` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of bought
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `goodsid` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '新鲜水果', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408111959_2837.png');
INSERT INTO `category` VALUES ('2', '酒水饮料', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408112010_4489.png');
INSERT INTO `category` VALUES ('3', '休闲零食', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408113102_1595.png');
INSERT INTO `category` VALUES ('4', '海鲜水产', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408113048_1276.png');
INSERT INTO `category` VALUES ('5', '新鲜蔬菜', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408112053_8191.png');
INSERT INTO `category` VALUES ('6', '乳品烘培', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408112053_8191.png');
INSERT INTO `category` VALUES ('7', '生活用品', 'http://images.baixingliangfan.cn/firstCategoryPicture/20180408/20180408112053_8191.png');

-- ----------------------------
-- Table structure for collects
-- ----------------------------
DROP TABLE IF EXISTS `collects`;
CREATE TABLE `collects` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `collects` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of collects
-- ----------------------------

-- ----------------------------
-- Table structure for goods_list
-- ----------------------------
DROP TABLE IF EXISTS `goods_list`;
CREATE TABLE `goods_list` (
  `goodsId` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `image` longtext COLLATE utf8_unicode_ci,
  `mallPrice` decimal(10,2) DEFAULT '0.00',
  `price` decimal(10,2) DEFAULT '0.00',
  `assess` decimal(10,2) DEFAULT '0.00',
  `sales` int(255) DEFAULT '0',
  `goods_type` int(255) DEFAULT '0',
  `sub_type` int(255) DEFAULT '0',
  `details` text COLLATE utf8_unicode_ci,
  `stock` int(255) DEFAULT '0',
  `describes` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`goodsId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of goods_list
-- ----------------------------
INSERT INTO `goods_list` VALUES ('2', '越南白心火龙果（中果）255个 约700g', 'D:/Code/Code/img/u=1632314861,472090964&fm=15&gp=0.jpg', '18.99', '10.80', '2.10', '54', '1', '1', '', '99965', '富含膳食纤维 饭后来一口~');
INSERT INTO `goods_list` VALUES ('3', '牛油果', 'D:/Code/Code/img/u=544265173,204794736&fm=26&gp=0.jpg', '23.90', '25.00', '3.80', '15', '1', '1', '', '85', '甜出幸福~为您精心挑选~');
INSERT INTO `goods_list` VALUES ('4', '菲律宾凤梨 800g', 'D:/Code/Code/img/u=2844671728,4229938560&fm=26&gp=0.jpg', '7.99', '12.90', '4.00', '5', '1', '1', '', '95', '真的很脆爽哦~');
INSERT INTO `goods_list` VALUES ('5', '小台农芒果 约530g', 'D:/Code/Code/img/u=1688891848,3382311654&fm=26&gp=0.jpg', '12.90', '12.90', '1.00', '3', '1', '1', '', '97', '小小个头~ 大大享受~');
INSERT INTO `goods_list` VALUES ('6', '菲诺椰皇冻1个 160g/盒', 'D:/Code/Code/img/u=575871372,2559237028&fm=26&gp=0.jpg', '22.90', '22.90', '1.34', '9', '1', '1', '', '91', '网红椰子冻 Q弹细嫩');
INSERT INTO `goods_list` VALUES ('7', '海南牛奶木瓜1个 500g以上', 'D:/Code/Code/img/u=2421971516,4130533013&fm=26&gp=0.jpg', '8.80', '8.80', '0.00', '5', '1', '1', '', '95', '建议多放几天~吃了才能棒棒哒~');
INSERT INTO `goods_list` VALUES ('8', '泰国香水椰青1个 800g以上', 'D:/Code/Code/img/u=301866191,4134397278&fm=26&gp=0.jpg', '10.80', '10.80', '2.00', '8', '1', '1', '', '92', '香甜可口~长在树上的矿泉水~');
INSERT INTO `goods_list` VALUES ('9', '海南新鲜青柠檬2个', 'D:/Code/Code/img/u=1958065142,810585041&fm=26&gp=0.jpg', '6.90', '6.90', '0.00', '0', '1', '2', '', '50', '皮薄多汁  富含维c 香味浓郁');
INSERT INTO `goods_list` VALUES ('11', '海南雨季小青柠 250g', 'D:/Code/Code/img/u=2191660219,2644015540&fm=26&gp=0.jpg', '8.80', '10.00', '3.00', '2', '1', '2', '', '48', '汁水充盈 柠香四溢');
INSERT INTO `goods_list` VALUES ('12', '澳大利亚阳光蜜柑4个 约480g', 'D:/Code/Code/img/u=2854874812,783673933&fm=26&gp=0.jpg', '19.90', '23.00', '3.00', '2', '1', '2', '', '48', '酸甜多汁 就像吃果汁糖一样~');
INSERT INTO `goods_list` VALUES ('13', '澳洲进口丑橙（自然花斑）4个 约700g', 'D:/Code/Code/img/u=3073554718,2551048094&fm=26&gp=0.jpg', '11.90', '14.00', '0.00', '0', '1', '2', '', '100', '有故事的橙子更香甜');
INSERT INTO `goods_list` VALUES ('14', '澳大利亚阳光脐橙4个 约700g', 'D:/Code/Code/img/u=1208525901,2517303924&fm=26&gp=0.jpg', '16.80', '16.80', '0.00', '0', '1', '2', '', '100', '晒足阳光 皮薄多汁~');
INSERT INTO `goods_list` VALUES ('15', '南非西柚4个 1kg以上', 'D:/Code/Code/img/u=2625080654,3643760324&fm=26&gp=0.jpg', '14.90', '16.00', '0.00', '0', '1', '2', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('16', '皇帝蕉 300g', 'D:/Code/Code/img/u=419477091,86123777&fm=26&gp=0.jpg', '9.90', '11.90', '0.00', '0', '1', '3', '', '100', '小巧精致就是我 香甜软萌就是我 ~');
INSERT INTO `goods_list` VALUES ('17', '新西兰苹果4个', 'D:/Code/Code/img/u=3115867769,2889560802&fm=26&gp=0.jpg', '17.90', '19.90', '0.00', '0', '1', '3', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('18', '翠冠梨2个 约600g', 'D:/Code/Code/img/u=1679668178,4241502607&fm=26&gp=0.jpg', '5.99', '8.90', '0.00', '2', '1', '3', '', '98', '咬一口 脆甜多汁~');
INSERT INTO `goods_list` VALUES ('19', '进口香蕉 约500g', 'D:/Code/Code/img/u=3340785118,1888665824&fm=26&gp=0.jpg', '6.90', '6.90', '0.00', '0', '1', '3', '', '100', '香甜软糯 老少皆宜');
INSERT INTO `goods_list` VALUES ('20', '汉水蜜梨4个 800g以上', 'D:/Code/Code/img/u=3442320504,2378632191&fm=26&gp=0.jpg', '10.90', '10.90', '0.00', '0', '1', '3', '', '100', '新梨上市 脆甜爽口');
INSERT INTO `goods_list` VALUES ('21', '山东精品红富士苹果（中果）4个 约860g', 'D:/Code/Code/img/u=4252544932,2987433523&fm=26&gp=0.jpg', '28.80', '28.80', '0.00', '0', '1', '3', '', '100', '嘎嘣脆 满口香~');
INSERT INTO `goods_list` VALUES ('22', '智利绿奇异果4个 360g以上', 'D:/Code/Code/img/u=1178048734,468363575&fm=26&gp=0.jpg', '11.90', '11.90', '0.00', '2', '1', '4', '', '98', '维C满满');
INSERT INTO `goods_list` VALUES ('23', '新西兰佳沛金奇异果2个 200g', 'D:/Code/Code/img/u=1353793669,3219073886&fm=26&gp=0.jpg', '14.90', '14.90', '0.00', '0', '1', '4', '', '50', '阳光“金果”来自新西兰的阳光能量');
INSERT INTO `goods_list` VALUES ('24', '国产蓝莓 125g/盒', 'D:/Code/Code/img/u=184378841,1301869725&fm=26&gp=0.jpg', '7.90', '10.90', '0.00', '0', '1', '4', '', '100', '小果实 大营养');
INSERT INTO `goods_list` VALUES ('25', '巨峰葡萄 500g', 'D:/Code/Code/img/u=3426481609,2338801724&fm=26&gp=0.jpg', '8.90', '12.90', '3.00', '3', '1', '4', '', '97', '酸甜味浓 皮薄易撕~');
INSERT INTO `goods_list` VALUES ('26', '广西百香果 约250g', 'D:/Code/Code/img/u=1155959895,4043849295&fm=26&gp=0.jpg', '9.90', '9.90', '3.00', '1', '1', '4', '', '49', '百香果加蜂蜜泡水喝 这个味儿~酸爽！');
INSERT INTO `goods_list` VALUES ('27', '云南蒙自甜石榴2个 约500g', 'D:/Code/Code/img/u=316992736,3831301416&fm=26&gp=0.jpg', '13.90', '13.90', '0.00', '0', '1', '4', '', '50', '汁多味甜 如饮琼浆~');
INSERT INTO `goods_list` VALUES ('28', '农夫山泉饮用天然水 550ml*28瓶', 'D:/Code/Code/img/u=347838226,1369157896&fm=26&gp=0.jpg', '35.80', '45.00', '3.00', '2', '2', '1', '', '98', '天然弱碱性的健康水~');
INSERT INTO `goods_list` VALUES ('29', '怡宝纯净水 555ml*24/箱', 'D:/Code/Code/img/u=502620103,2392554444&fm=15&gp=0.jpg', '39.90', '39.90', '0.00', '0', '2', '1', '', '100', '喝完才知道的纯净清甜~');
INSERT INTO `goods_list` VALUES ('30', '农夫山泉桶装水 5L/桶', 'D:/Code/Code/img/u=1120897849,2500880469&fm=26&gp=0.jpg', '8.00', '9.00', '2.67', '3', '2', '1', '', '997', '大自然的搬水工~');
INSERT INTO `goods_list` VALUES ('31', '怡宝纯净水 555ml/瓶', 'D:/Code/Code/img/u=2631427933,2206181122&fm=26&gp=0.jpg', '2.00', '2.00', '0.00', '0', '2', '1', '', '100', '甘之如饴 品质承诺');
INSERT INTO `goods_list` VALUES ('32', '雪碧清爽柠檬味汽水 330ml*6罐', 'D:/Code/Code/img/u=326816131,2176382652&fm=26&gp=0.jpg', '13.90', '15.90', '0.00', '2', '2', '2', '', '98', '暂无描述');
INSERT INTO `goods_list` VALUES ('33', '哈尔滨冰爽啤酒 330ml*6罐', 'D:/Code/Code/img/u=530551723,2877299794&fm=26&gp=0.jpg', '13.80', '13.80', '0.00', '0', '2', '3', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('34', '小红帽干红葡萄酒 750ml/瓶', 'D:/Code/Code/img/u=2288755976,385425466&fm=26&gp=0.jpg', '88.00', '92.00', '0.00', '0', '2', '4', '', '100', '来自西班牙 童话故事与酒融合 混合果香 细腻单宁');
INSERT INTO `goods_list` VALUES ('35', '42度牛栏山陈酿 500ml/瓶', 'D:/Code/Code/img/u=544247526,2974523192&fm=26&gp=0.jpg', '14.90', '16.90', '0.00', '0', '2', '5', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('36', '东园什锦豆果子 40g/袋', 'D:/Code/Code/img/u=2663107222,2165917203&fm=26&gp=0.jpg', '5.90', '5.90', '0.00', '0', '3', '1', '', '100', '好味道 你知道~');
INSERT INTO `goods_list` VALUES ('37', '源氏大辣片 230g/袋', 'D:/Code/Code/img/u=2225025596,2336208468&fm=26&gp=0.jpg', '8.90', '8.90', '0.00', '0', '3', '2', '', '100', '记忆中的好味道~');
INSERT INTO `goods_list` VALUES ('38', '宏途山药薄片脆片(酱香味 ) 90g/袋', 'D:/Code/Code/img/u=4061115133,3066965525&fm=26&gp=0.jpg', '9.90', '11.90', '0.00', '0', '3', '3', '', '150', '零食新风尚 趣味又营养');
INSERT INTO `goods_list` VALUES ('39', '明治熊猫巧克力夹心饼干 50g/盒', 'D:/Code/Code/img/u=3639520645,1291424136&fm=26&gp=0.jpg', '11.80', '11.80', '0.00', '0', '3', '4', '', '120', '我的心里除了奶油 还有你~');
INSERT INTO `goods_list` VALUES ('40', '鲜活基围虾 350g', 'D:/Code/Code/img/u=1733579133,2117469395&fm=26&gp=0.jpg', '33.90', '33.90', '0.00', '0', '4', '1', '', '210', '暂无描述');
INSERT INTO `goods_list` VALUES ('41', '鲜活六月黄螃蟹2只 130g以上', 'D:/Code/Code/img/u=716498711,1733085485&fm=26&gp=0.jpg', '29.90', '29.90', '3.00', '4', '4', '2', '', '106', '单只65g以上 以公蟹为主');
INSERT INTO `goods_list` VALUES ('42', '鲜活小生蚝5只 250g以上', 'D:/Code/Code/img/u=429515498,4291857401&fm=15&gp=0.jpg', '12.80', '12.80', '0.00', '2', '4', '3', '', '98', '清蒸小生蚝 饱满多汁 蚝美味');
INSERT INTO `goods_list` VALUES ('43', '冷冻鱿鱼须 250g', 'D:/Code/Code/img/u=1231356721,1424332275&fm=26&gp=0.jpg', '19.90', '19.90', '0.00', '0', '4', '4', '', '150', '烧烤季到了 烤鱿鱼不错哦');
INSERT INTO `goods_list` VALUES ('44', '有机土豆 300g', 'D:/Code/Code/img/u=25566141,1229808509&fm=15&gp=0.jpg', '15.80', '15.80', '0.00', '0', '5', '1', '', '100', '有机食品·我就是传说中的大众脸 家常餐桌必备');
INSERT INTO `goods_list` VALUES ('45', '小青菜 300g', 'D:/Code/Code/img/u=2971264432,2466016860&fm=26&gp=0.jpg', '1.90', '1.90', '0.00', '0', '5', '2', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('46', '三红胡萝卜 约500g', 'D:/Code/Code/img/u=3366400392,965912645&fm=26&gp=0.jpg', '2.50', '2.50', '0.00', '0', '5', '3', '', '100', '红皮红肉红心 胡萝卜精品哦~');
INSERT INTO `goods_list` VALUES ('47', '黄瓜 约600g', 'D:/Code/Code/img/u=3145151882,2834708761&fm=26&gp=0.jpg', '3.90', '3.90', '0.00', '0', '5', '4', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('48', '每日鲜语鲜牛奶 1000ml/瓶', 'D:/Code/Code/img/u=2095726209,3646947911&fm=26&gp=0.jpg', '22.90', '24.90', '0.00', '0', '6', '1', '', '0', '2小时，从挤奶到鲜奶');
INSERT INTO `goods_list` VALUES ('49', '光明畅优风味发酵乳（原味） 100g*8杯/组', 'D:/Code/Code/img/u=1607439338,639112548&fm=26&gp=0.jpg', '18.90', '18.90', '0.00', '0', '6', '2', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('50', '全麦高纤吐司（天然酵母） 110g/份', 'D:/Code/Code/img/u=2127058433,2682366562&fm=15&gp=0.jpg', '6.90', '6.90', '0.00', '0', '6', '3', '', '50', '暂无描述');
INSERT INTO `goods_list` VALUES ('51', '维达超韧系列纸面巾 130抽/包', 'D:/Code/Code/img/u=4233503378,1498871443&fm=15&gp=0.jpg', '4.90', '4.90', '0.00', '0', '7', '1', '', '100', '暂无描述');
INSERT INTO `goods_list` VALUES ('52', '妙洁PE保鲜袋 30米/盒(送15米)', 'D:/Code/Code/img/u=2963698577,2343046932&fm=26&gp=0.jpg', '13.80', '13.80', '0.00', '0', '7', '2', '', '150', '暂无描述');
INSERT INTO `goods_list` VALUES ('53', '白猫洗洁精 50770g/瓶', 'D:/Code/Code/img/u=2354228812,1369828118&fm=15&gp=0.jpg', '4.80', '6.80', '0.00', '1', '7', '3', '', '1003', '暂无描述166');

-- ----------------------------
-- Table structure for home
-- ----------------------------
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodPrice` longtext COLLATE utf8_unicode_ci,
  `headerSwipes` text COLLATE utf8_unicode_ci,
  `recommends` longtext COLLATE utf8_unicode_ci,
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of home
-- ----------------------------
INSERT INTO `home` VALUES ('1', '[25,34,48,16,28]', '[39,22,40,2,45]', '[53,21,8,30,20]', '1589558400000');

-- ----------------------------
-- Table structure for subcategory
-- ----------------------------
DROP TABLE IF EXISTS `subcategory`;
CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subCategoryId` int(11) NOT NULL,
  `subCategoryName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `categoryId` (`categoryId`) USING BTREE,
  CONSTRAINT `categoryId` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of subcategory
-- ----------------------------
INSERT INTO `subcategory` VALUES ('1', '1', '热带水果', '1');
INSERT INTO `subcategory` VALUES ('2', '2', '柑橘橙柚', '1');
INSERT INTO `subcategory` VALUES ('3', '3', '苹果梨蕉', '1');
INSERT INTO `subcategory` VALUES ('4', '4', '葡提浆果', '1');
INSERT INTO `subcategory` VALUES ('5', '1', '饮用水', '2');
INSERT INTO `subcategory` VALUES ('6', '2', '碳酸饮料', '2');
INSERT INTO `subcategory` VALUES ('7', '3', '啤酒', '2');
INSERT INTO `subcategory` VALUES ('8', '4', '葡萄酒', '2');
INSERT INTO `subcategory` VALUES ('9', '5', '白酒', '2');
INSERT INTO `subcategory` VALUES ('10', '1', '坚果', '3');
INSERT INTO `subcategory` VALUES ('11', '2', '肉干小食', '3');
INSERT INTO `subcategory` VALUES ('12', '3', '膨化食品', '3');
INSERT INTO `subcategory` VALUES ('13', '4', '饼干糕点', '3');
INSERT INTO `subcategory` VALUES ('14', '1', '活鱼活虾', '4');
INSERT INTO `subcategory` VALUES ('15', '2', '活蟹', '4');
INSERT INTO `subcategory` VALUES ('16', '3', '活贝', '4');
INSERT INTO `subcategory` VALUES ('17', '4', '冷冻海鲜', '4');
INSERT INTO `subcategory` VALUES ('18', '1', '有机蔬菜', '5');
INSERT INTO `subcategory` VALUES ('19', '2', '叶菜类', '5');
INSERT INTO `subcategory` VALUES ('20', '3', '根茎类', '5');
INSERT INTO `subcategory` VALUES ('21', '4', '瓜果类', '5');
INSERT INTO `subcategory` VALUES ('22', '1', '鲜奶', '6');
INSERT INTO `subcategory` VALUES ('23', '2', '酸奶', '6');
INSERT INTO `subcategory` VALUES ('24', '3', '面包烘培', '6');
INSERT INTO `subcategory` VALUES ('25', '1', '纸类', '7');
INSERT INTO `subcategory` VALUES ('26', '2', '保鲜袋', '7');
INSERT INTO `subcategory` VALUES ('27', '3', '清洁用品', '7');

-- ----------------------------
-- Table structure for swipers
-- ----------------------------
DROP TABLE IF EXISTS `swipers`;
CREATE TABLE `swipers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of swipers
-- ----------------------------
INSERT INTO `swipers` VALUES ('1', 'http://images.baixingliangfan.cn/advertesPicture/20180407/20180407175040_1780.jpg');
INSERT INTO `swipers` VALUES ('2', 'http://images.baixingliangfan.cn/advertesPicture/20180407/20180407175111_9509.jpg');
INSERT INTO `swipers` VALUES ('3', 'http://images.baixingliangfan.cn/advertesPicture/20180407/20180407175142_6947.jpg');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `payPWD` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `telephone` varchar(20) COLLATE utf8_unicode_ci DEFAULT '',
  `sex` varchar(5) COLLATE utf8_unicode_ci DEFAULT '',
  `age` int(11) DEFAULT '0',
  `birthday` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574227536070&di=40b0dae73c365befe4290cacdb0a78e3&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fuploadfile%2F2015%2F1208%2F20151208073837635.jpg',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username_unique_index` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_info
-- ----------------------------
