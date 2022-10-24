CREATE TABLE `user_role` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                             `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
                             `role_id` int(8) unsigned NOT NULL COMMENT '角色ID',
                             `application_name` varchar(128) NOT NULL COMMENT '应用名',
                             `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 | 0、未删除 1、已删除',
                             `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `uniq_user_id_role_id` (`user_id`,`role_id`) USING BTREE,
                             KEY `idx_user_id_application_name` (`user_id`,`application_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

CREATE TABLE `system_role` (
                               `role_id` int(8) unsigned NOT NULL COMMENT '角色ID',
                               `role_name` varchar(32) NOT NULL COMMENT '角色名称',
                               `parent_role_id` int(8) unsigned DEFAULT NULL COMMENT '父角色ID',
                               `application_name` varchar(128) NOT NULL COMMENT '应用名',
                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 | 0、未删除 1、已删除',
                               `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
                               PRIMARY KEY (`role_id`) USING BTREE,
                               KEY `idx_application_name` (`application_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

CREATE TABLE `system_authority` (
                                    `authority_id` int(8) unsigned NOT NULL COMMENT '权限ID',
                                    `authority` varchar(32) NOT NULL COMMENT '权限CODE',
                                    `authority_name` varchar(32) NOT NULL COMMENT '权限名称',
                                    `parent_authority_id` int(8) unsigned DEFAULT NULL COMMENT '父权限ID',
                                    `application_name` varchar(128) NOT NULL COMMENT '应用名',
                                    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 | 0、未删除 1、已删除',
                                    `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
                                    PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';

CREATE TABLE `system_role_authority` (
                                         `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                         `role_id` int(8) unsigned NOT NULL COMMENT '角色ID',
                                         `authority_id` int(8) unsigned NOT NULL COMMENT '权限ID',
                                         `application_name` varchar(128) NOT NULL COMMENT '应用名',
                                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 | 0、未删除 1、已删除',
                                         `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
                                         PRIMARY KEY (`id`) USING BTREE,
                                         UNIQUE KEY `uniq_role_id_authority_id` (`role_id`,`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色权限表';

CREATE TABLE `user_authority` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
                                  `authority_id` int(8) unsigned NOT NULL COMMENT '权限ID',
                                  `application_name` varchar(128) NOT NULL COMMENT '应用名',
                                  `authority_end_time` timestamp NOT NULL COMMENT '权限截止日期',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 | 0、未删除 1、已删除',
                                  `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE KEY `uniq_user_id_authority_id` (`user_id`,`authority_id`) USING BTREE,
                                  KEY `idx_user_id_application_name` (`user_id`,`application_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限表';