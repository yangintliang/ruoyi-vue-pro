package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 国家精简 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountrySimpleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "国家名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "中国")
    private String countryName;

    @Schema(description = "国家代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "CN")
    private String countryCode;

    @Schema(description = "国家英文名称", example = "China")
    private String countryEnName;

}