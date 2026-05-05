package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 国家新增/修改 Request VO")
@Data
public class CountrySaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "国家名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "中国")
    @NotEmpty(message = "国家名称不能为空")
    private String countryName;

    @Schema(description = "国家代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "CN")
    @NotEmpty(message = "国家代码不能为空")
    private String countryCode;

    @Schema(description = "国家英文名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "China")
    @NotEmpty(message = "国家英文名称不能为空")
    private String countryEnName;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "时区", example = "Asia/Shanghai")
    private String timezone;

}