package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 国家 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CountryRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "国家名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "中国")
    @ExcelProperty("国家名称")
    private String countryName;

    @Schema(description = "国家代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "CN")
    @ExcelProperty("国家代码")
    private String countryCode;

    @Schema(description = "国家英文名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "China")
    @ExcelProperty("国家英文名称")
    private String countryEnName;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("排序")
    private Integer sort;

    @Schema(description = "时区", example = "Asia/Shanghai")
    @ExcelProperty("时区")
    private String timezone;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}