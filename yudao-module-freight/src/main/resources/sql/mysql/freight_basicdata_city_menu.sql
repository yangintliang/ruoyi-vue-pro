-- 将 ${basicdata_parent_id} 替换为“基础资料”菜单的实际主键 ID。
INSERT INTO `system_menu`
(`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`,
 `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
('城市管理', '', 2, 10, ${basicdata_parent_id}, 'city', 'ep:location', 'freight/basicdata/city/index', 'FreightBasicdataCity', 0, b'1', b'1', b'1',
 '1', NOW(), '1', NOW(), b'0');

SET @city_menu_id = LAST_INSERT_ID();

INSERT INTO `system_menu`
(`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`,
 `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
('城市查询', 'freight:city:query', 3, 1, @city_menu_id, '', '', '', '', 0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'),
('城市创建', 'freight:city:create', 3, 2, @city_menu_id, '', '', '', '', 0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'),
('城市更新', 'freight:city:update', 3, 3, @city_menu_id, '', '', '', '', 0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'),
('城市删除', 'freight:city:delete', 3, 4, @city_menu_id, '', '', '', '', 0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'),
('城市导出', 'freight:city:export', 3, 5, @city_menu_id, '', '', '', '', 0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0');
