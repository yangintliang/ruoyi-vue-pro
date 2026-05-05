package cn.iocoder.yudao.module.freight.controller.admin.basicdata;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryImportExcelVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryImportRespVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryPageReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryRespVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountrySaveReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountrySimpleRespVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CountryDO;
import cn.iocoder.yudao.module.freight.service.basicdata.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;

@Tag(name = "管理后台 - 国家")
@RestController
@RequestMapping("/freight/country")
@Validated
public class CountryController {

    @Resource
    private CountryService countryService;

    @PostMapping("/create")
    @Operation(summary = "创建国家")
    @PreAuthorize("@ss.hasPermission('freight:country:create')")
    public CommonResult<Long> createCountry(@Valid @RequestBody CountrySaveReqVO createReqVO) {
        return success(countryService.createCountry(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "修改国家")
    @PreAuthorize("@ss.hasPermission('freight:country:update')")
    public CommonResult<Boolean> updateCountry(@Valid @RequestBody CountrySaveReqVO updateReqVO) {
        countryService.updateCountry(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除国家")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('freight:country:delete')")
    public CommonResult<Boolean> deleteCountry(@RequestParam("id") Long id) {
        countryService.deleteCountry(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得国家")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('freight:country:query')")
    public CommonResult<CountryRespVO> getCountry(@RequestParam("id") Long id) {
        CountryDO country = countryService.getCountry(id);
        return success(BeanUtils.toBean(country, CountryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得国家分页")
    @PreAuthorize("@ss.hasPermission('freight:country:query')")
    public CommonResult<PageResult<CountryRespVO>> getCountryPage(@Valid CountryPageReqVO pageReqVO) {
        PageResult<CountryDO> pageResult = countryService.getCountryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CountryRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得国家精简列表", description = "主要用于前端的下拉选项")
    public CommonResult<List<CountrySimpleRespVO>> getCountrySimpleList() {
        List<CountryDO> list = countryService.getCountrySimpleList();
        return success(convertList(list, country -> new CountrySimpleRespVO(
                country.getId(), country.getCountryName(), country.getCountryCode(), country.getCountryEnName())));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出国家 Excel")
    @PreAuthorize("@ss.hasPermission('freight:country:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCountryExcel(@Valid CountryPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CountryDO> list = countryService.getCountryList(pageReqVO);
        ExcelUtils.write(response, "国家.xls", "数据", CountryRespVO.class,
                BeanUtils.toBean(list, CountryRespVO.class));
    }

    // ---- 导入功能 ----

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入国家模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        List<CountryImportExcelVO> list = Arrays.asList(
                CountryImportExcelVO.builder().countryName("中国").countryCode("CN").countryEnName("China").sort(1).timezone("Asia/Shanghai").build(),
                CountryImportExcelVO.builder().countryName("美国").countryCode("US").countryEnName("United States").sort(2).timezone("America/New_York").build()
        );
        ExcelUtils.write(response, "国家导入模板.xls", "国家列表",
                CountryImportExcelVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入国家")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('freight:country:import')")
    public CommonResult<CountryImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<CountryImportExcelVO> list = ExcelUtils.read(file, CountryImportExcelVO.class);
        return success(countryService.importCountryList(list, updateSupport));
    }

}
