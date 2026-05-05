package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 货运 ERP 基础资料 - 城市新增/修改 Request VO")
@Data
public class CitySaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "城市名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "Shanghai")
    @NotEmpty(message = "城市名称不能为空")
    private String cityName;

    @Schema(description = "城市编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "SHA")
    @NotEmpty(message = "城市编码不能为空")
    private String cityCode;

    @Schema(description = "所属省份业务 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "310000")
    @NotNull(message = "所属省份业务 ID 不能为空")
    private Long provinceId;

    @Schema(description = "UN/LOCODE", example = "CNSHA")
    private String unlocode;

}
