package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 货运 ERP 基础资料 - 城市分页 Request VO")
@Data
public class CityPageReqVO extends PageParam {

    @Schema(description = "城市名称", example = "Shanghai")
    private String cityName;

    @Schema(description = "城市编码", example = "SHA")
    private String cityCode;

    @Schema(description = "所属省份业务 ID", example = "310000")
    private Long provinceId;

    @Schema(description = "UN/LOCODE", example = "CNSHA")
    private String unlocode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
