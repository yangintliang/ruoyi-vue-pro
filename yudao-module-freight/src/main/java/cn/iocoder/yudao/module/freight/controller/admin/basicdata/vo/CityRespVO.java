package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 货运 ERP 基础资料 - 城市 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CityRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "城市名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "Shanghai")
    @ExcelProperty("城市名称")
    private String cityName;

    @Schema(description = "城市编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "SHA")
    @ExcelProperty("城市编码")
    private String cityCode;

    @Schema(description = "所属省份业务 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "310000")
    @ExcelProperty("所属省份业务 ID")
    private Long provinceId;

    @Schema(description = "UN/LOCODE", example = "CNSHA")
    @ExcelProperty("UN/LOCODE")
    private String unlocode;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
