package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryImportExcelVO {

    @ExcelProperty("国家名称")
    private String countryName;

    @ExcelProperty("国家代码")
    private String countryCode;

    @ExcelProperty("国家英文名称")
    private String countryEnName;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("时区")
    private String timezone;

}