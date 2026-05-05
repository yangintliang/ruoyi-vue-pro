CREATE TABLE `freight_basicdata_city` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `city_name` varchar(64) NOT NULL COMMENT '城市名称',
  `city_code` varchar(64) NOT NULL COMMENT '城市编码',
  `province_id` bigint NOT NULL COMMENT '所属省份业务 ID',
  `unlocode` varchar(64) DEFAULT NULL COMMENT 'UN/LOCODE',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_city_code` (`city_code`, `tenant_id`, `deleted`),
  KEY `idx_province_id` (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='货运 ERP 基础资料 - 城市';
