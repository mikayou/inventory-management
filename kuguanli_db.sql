/*
 Navicat Premium Data Transfer

 Source Server         : jsp
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : kuguanli_db

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 09/06/2023 16:30:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for catalogs
-- ----------------------------
DROP TABLE IF EXISTS `catalogs`;
CREATE TABLE `catalogs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of catalogs
-- ----------------------------
INSERT INTO `catalogs` VALUES (3, '五金');
INSERT INTO `catalogs` VALUES (8, '日用品');
INSERT INTO `catalogs` VALUES (9, '日用品1');
INSERT INTO `catalogs` VALUES (13, '日用品2');
INSERT INTO `catalogs` VALUES (4, '食品');
INSERT INTO `catalogs` VALUES (14, '饮料');

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, '许丽', '47');
INSERT INTO `customers` VALUES (2, '王五', '17376760001');

-- ----------------------------
-- Table structure for dn
-- ----------------------------
DROP TABLE IF EXISTS `dn`;
CREATE TABLE `dn`  (
  `id` int NOT NULL,
  `user_uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sale_order_id` int NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_dn_user_uuid`(`user_uuid` ASC) USING BTREE,
  INDEX `fk_dn_sale_order_id`(`sale_order_id` ASC) USING BTREE,
  CONSTRAINT `fk_dn_sale_order_id` FOREIGN KEY (`sale_order_id`) REFERENCES `sale_orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_dn_user_uuid` FOREIGN KEY (`user_uuid`) REFERENCES `users` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dn
-- ----------------------------

-- ----------------------------
-- Table structure for dn_details
-- ----------------------------
DROP TABLE IF EXISTS `dn_details`;
CREATE TABLE `dn_details`  (
  `dn_id` int NOT NULL,
  `warehouse_id` int NOT NULL,
  `product_id` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`dn_id`) USING BTREE,
  INDEX `fk_dnd_product_id`(`product_id` ASC) USING BTREE,
  INDEX `fk_dnd_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `fk_dn_id` FOREIGN KEY (`dn_id`) REFERENCES `dn` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_dnd_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_dnd_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dn_details
-- ----------------------------

-- ----------------------------
-- Table structure for grn_details
-- ----------------------------
DROP TABLE IF EXISTS `grn_details`;
CREATE TABLE `grn_details`  (
  `grn_id` int NOT NULL,
  `product_id` int NOT NULL,
  `count` int NOT NULL,
  `unit_price` double NOT NULL,
  `warehouse_id` int NOT NULL,
  PRIMARY KEY (`grn_id`, `product_id`, `warehouse_id`) USING BTREE,
  INDEX `fk_product_id`(`product_id` ASC) USING BTREE,
  INDEX `fk_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `fk_grn_id` FOREIGN KEY (`grn_id`) REFERENCES `grns` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grn_details
-- ----------------------------
INSERT INTO `grn_details` VALUES (31, 2, 11, 11.01, 1);
INSERT INTO `grn_details` VALUES (32, 2, 11, 11, 1);
INSERT INTO `grn_details` VALUES (33, 2, 11, 11, 1);
INSERT INTO `grn_details` VALUES (34, 2, 23, 23, 1);
INSERT INTO `grn_details` VALUES (35, 2, 33, 11, 1);
INSERT INTO `grn_details` VALUES (35, 3, 33, 12.21, 1);
INSERT INTO `grn_details` VALUES (35, 4, 23, 11.12, 1);
INSERT INTO `grn_details` VALUES (36, 2, 1, 131, 1);
INSERT INTO `grn_details` VALUES (36, 4, 22, 11, 1);
INSERT INTO `grn_details` VALUES (37, 5, 11, 11, 1);
INSERT INTO `grn_details` VALUES (38, 3, 12, 1.23, 2);
INSERT INTO `grn_details` VALUES (38, 5, 11, 11, 2);

-- ----------------------------
-- Table structure for grns
-- ----------------------------
DROP TABLE IF EXISTS `grns`;
CREATE TABLE `grns`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库日期',
  `type` int NOT NULL DEFAULT 0 COMMENT '入库类型 0：采购入库',
  `user_uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '入库人员',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `purchase_order_id` int NOT NULL COMMENT '采购订单id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_uuid`(`user_uuid` ASC) USING BTREE,
  INDEX `fk_purchase_order_id`(`purchase_order_id` ASC) USING BTREE,
  CONSTRAINT `fk_purchase_order_id` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_uuid` FOREIGN KEY (`user_uuid`) REFERENCES `users` (`uuid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grns
-- ----------------------------
INSERT INTO `grns` VALUES (29, '2023-06-09 07:54:28', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 10);
INSERT INTO `grns` VALUES (31, '2023-06-09 08:12:22', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 6);
INSERT INTO `grns` VALUES (32, '2023-06-09 08:15:47', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 4);
INSERT INTO `grns` VALUES (33, '2023-06-09 08:16:25', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 5);
INSERT INTO `grns` VALUES (34, '2023-06-09 08:17:26', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 10);
INSERT INTO `grns` VALUES (35, '2023-06-09 08:18:16', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 8);
INSERT INTO `grns` VALUES (36, '2023-06-09 08:18:49', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 7);
INSERT INTO `grns` VALUES (37, '2023-06-09 10:29:17', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 11);
INSERT INTO `grns` VALUES (38, '2023-06-09 13:29:45', 0, '2df68ee5-feeb-11ed-8675-f80dac07989a', '', 16);

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `product_id` int NOT NULL,
  `warehouse_id` int NOT NULL,
  `inventory` int NOT NULL,
  PRIMARY KEY (`product_id`, `warehouse_id`) USING BTREE,
  INDEX `fk_warehouse`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_warehouse` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (2, 1, 690);
INSERT INTO `inventory` VALUES (3, 1, 433);
INSERT INTO `inventory` VALUES (3, 2, 12);
INSERT INTO `inventory` VALUES (4, 1, 45);
INSERT INTO `inventory` VALUES (5, 1, 11);
INSERT INTO `inventory` VALUES (5, 2, 11);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `catalog_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_catalog`(`catalog_id` ASC) USING BTREE,
  CONSTRAINT `fk_catalog` FOREIGN KEY (`catalog_id`) REFERENCES `catalogs` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (2, '钉子', 3);
INSERT INTO `products` VALUES (3, '螺丝', 3);
INSERT INTO `products` VALUES (4, '螺丝刀', 3);
INSERT INTO `products` VALUES (5, '冰红茶', 14);

-- ----------------------------
-- Table structure for purchase_order_details
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order_details`;
CREATE TABLE `purchase_order_details`  (
  `purchase_order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `count` int NOT NULL,
  `unit_price` double NOT NULL,
  PRIMARY KEY (`purchase_order_id`, `product_id`) USING BTREE,
  INDEX `fk_pd_product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `fk_pd_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pd_purchase_order_id` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_order_details
-- ----------------------------
INSERT INTO `purchase_order_details` VALUES (4, 2, 11, 11);
INSERT INTO `purchase_order_details` VALUES (5, 2, 11, 11);
INSERT INTO `purchase_order_details` VALUES (6, 2, 11, 11.01);
INSERT INTO `purchase_order_details` VALUES (7, 2, 1, 131);
INSERT INTO `purchase_order_details` VALUES (7, 4, 22, 11);
INSERT INTO `purchase_order_details` VALUES (8, 2, 33, 11);
INSERT INTO `purchase_order_details` VALUES (8, 3, 33, 12.21);
INSERT INTO `purchase_order_details` VALUES (8, 4, 23, 11.12);
INSERT INTO `purchase_order_details` VALUES (9, 2, 12, 12);
INSERT INTO `purchase_order_details` VALUES (9, 4, 32, 12);
INSERT INTO `purchase_order_details` VALUES (10, 2, 23, 23);
INSERT INTO `purchase_order_details` VALUES (11, 5, 11, 11);
INSERT INTO `purchase_order_details` VALUES (12, 4, 11, 11);
INSERT INTO `purchase_order_details` VALUES (12, 5, 11, 11);
INSERT INTO `purchase_order_details` VALUES (13, 4, 11, 11);
INSERT INTO `purchase_order_details` VALUES (13, 5, 12, 4);
INSERT INTO `purchase_order_details` VALUES (14, 3, 12, 1.23);
INSERT INTO `purchase_order_details` VALUES (14, 5, 11, 11);
INSERT INTO `purchase_order_details` VALUES (15, 3, 12, 1.23);
INSERT INTO `purchase_order_details` VALUES (15, 5, 11, 11);
INSERT INTO `purchase_order_details` VALUES (16, 3, 12, 1.23);
INSERT INTO `purchase_order_details` VALUES (16, 5, 11, 11);

-- ----------------------------
-- Table structure for purchase_orders
-- ----------------------------
DROP TABLE IF EXISTS `purchase_orders`;
CREATE TABLE `purchase_orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_id` int NOT NULL,
  `order_date` date NOT NULL,
  `expected_delivery_date` date NOT NULL,
  `status` int NOT NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_po_supplier_id`(`supplier_id` ASC) USING BTREE,
  CONSTRAINT `fk_po_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_orders
-- ----------------------------
INSERT INTO `purchase_orders` VALUES (4, 2, '2023-06-08', '2023-06-08', 3, '');
INSERT INTO `purchase_orders` VALUES (5, 1, '2023-06-05', '2023-06-09', 3, '');
INSERT INTO `purchase_orders` VALUES (6, 3, '2023-06-06', '2023-06-09', 3, '发顺丰');
INSERT INTO `purchase_orders` VALUES (7, 2, '2023-06-09', '2023-06-09', 3, '111');
INSERT INTO `purchase_orders` VALUES (8, 2, '2023-06-09', '2023-06-09', 3, '11');
INSERT INTO `purchase_orders` VALUES (9, 3, '2023-06-09', '2023-06-09', 2, '1111');
INSERT INTO `purchase_orders` VALUES (10, 3, '2023-06-09', '2023-06-09', 3, '11');
INSERT INTO `purchase_orders` VALUES (11, 2, '2023-06-09', '2023-06-09', 3, '11');
INSERT INTO `purchase_orders` VALUES (12, 3, '2023-06-09', '2023-06-09', 1, '');
INSERT INTO `purchase_orders` VALUES (13, 3, '2023-06-09', '2023-06-09', 0, '');
INSERT INTO `purchase_orders` VALUES (14, 3, '2023-06-09', '2023-06-09', 0, '');
INSERT INTO `purchase_orders` VALUES (15, 3, '2023-06-09', '2023-06-09', 0, '顺丰');
INSERT INTO `purchase_orders` VALUES (16, 3, '2023-06-09', '2023-06-09', 3, '顺丰');

-- ----------------------------
-- Table structure for sale_order_details
-- ----------------------------
DROP TABLE IF EXISTS `sale_order_details`;
CREATE TABLE `sale_order_details`  (
  `sale_order_id` int NOT NULL,
  `product_id` int NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL,
  `unit_price` double NULL DEFAULT NULL,
  PRIMARY KEY (`sale_order_id`) USING BTREE,
  INDEX `fk_sod_product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `fk_sod_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sod_sale_order_id` FOREIGN KEY (`sale_order_id`) REFERENCES `sale_orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale_order_details
-- ----------------------------

-- ----------------------------
-- Table structure for sale_orders
-- ----------------------------
DROP TABLE IF EXISTS `sale_orders`;
CREATE TABLE `sale_orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NOT NULL COMMENT '0：待处理，1：正在处理或有异常，2：处理完成',
  `order_date` date NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dk_so_customer_id`(`customer_id` ASC) USING BTREE,
  CONSTRAINT `dk_so_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale_orders
-- ----------------------------

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of suppliers
-- ----------------------------
INSERT INTO `suppliers` VALUES (1, '器队大', '17376767714');
INSERT INTO `suppliers` VALUES (2, '王源', '17376767711');
INSERT INTO `suppliers` VALUES (3, '李三', '17376760000');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，用户编码',
  `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uuid`(`uuid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (10000, '2df68ee5-feeb-11ed-8675-f80dac07989a', 'admin', 0, '123456');

-- ----------------------------
-- Table structure for warehouses
-- ----------------------------
DROP TABLE IF EXISTS `warehouses`;
CREATE TABLE `warehouses`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `district` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `detail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouses
-- ----------------------------
INSERT INTO `warehouses` VALUES (1, '一号仓库', '广东省', '17376767723', '广州市', '黄埔区', '红山三路');
INSERT INTO `warehouses` VALUES (2, '2号仓库', '北京市', '18566866632', '市辖区', '东城区', '天安门');
INSERT INTO `warehouses` VALUES (7, '太和1号仓库', '北京市', '18566866112', '市辖区', '东城区', '北京大学1号');

-- ----------------------------
-- Procedure structure for increase_inventory
-- ----------------------------
DROP PROCEDURE IF EXISTS `increase_inventory`;
delimiter ;;
CREATE PROCEDURE `increase_inventory`(IN product_id INT, IN warehouse_id INT, IN value INT)
BEGIN
    INSERT INTO inventory (product_id, warehouse_id, inventory)
    VALUES (product_id, warehouse_id, value)
    ON DUPLICATE KEY UPDATE inventory = inventory + value;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table users
-- ----------------------------
DROP TRIGGER IF EXISTS `uuid_trigger`;
delimiter ;;
CREATE TRIGGER `uuid_trigger` BEFORE INSERT ON `users` FOR EACH ROW SET new.uuid=UUID()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
