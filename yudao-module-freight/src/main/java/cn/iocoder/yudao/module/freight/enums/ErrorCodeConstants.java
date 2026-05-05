package cn.iocoder.yudao.module.freight.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Freight 错误码枚举类。
 *
 * freight 系统，使用 1-040-000-000 段。
 */
public interface ErrorCodeConstants {

    // ========== Freight 基础资料 - 城市 1-040-000-000 ==========
    ErrorCode CITY_NOT_EXISTS = new ErrorCode(1_040_000_000, "城市不存在");
    ErrorCode CITY_CODE_EXISTS = new ErrorCode(1_040_000_001, "城市编码已存在");

    // ========== Freight 基础资料 - 国家 1-040-000-002 ==========
    ErrorCode COUNTRY_NOT_EXISTS = new ErrorCode(1_040_000_002, "国家不存在");
    ErrorCode COUNTRY_CODE_DUPLICATE = new ErrorCode(1_040_000_003, "国家代码已存在");
    ErrorCode COUNTRY_NAME_NOT_NULL = new ErrorCode(1_040_000_004, "国家名称不能为空");
    ErrorCode COUNTRY_IMPORT_LIST_IS_EMPTY = new ErrorCode(1_040_000_005, "导入国家数据不能为空！");

}
