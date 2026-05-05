package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 国家分页 Request VO")
@Data
public class CountryPageReqVO extends PageParam {

    @Schema(description = "国家名称，模糊匹配", example = "中国")
    private String countryName;

    @Schema(description = "国家代码，精确匹配", example = "CN")
    private String countryCode;

    @Schema(description = "国家英文名称，模糊匹配", example = "China")
    private String countryEnName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}