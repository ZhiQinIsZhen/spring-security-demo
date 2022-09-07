CREATE TABLE `auth_application`  (
                                     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                     `application_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用名',
                                     `dubbo_group` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'dubbo组',
                                     `dubbo_version` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'dubbo版本号',
                                     `dubbo_timeout` int(10) NOT NULL DEFAULT 1000 COMMENT 'dubbo超时时间',
                                     `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                     `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 | 0、未删除 1、已删除',
                                     `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本号',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE INDEX `uniq_application_name`(`application_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '认证服务应用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_application
-- ----------------------------
INSERT INTO `auth_application` VALUES (1, 'open-api', 'user', '1.0.0', 1000000, '2022-09-02 15:18:21', '2022-09-02 15:18:21', 0, 0);