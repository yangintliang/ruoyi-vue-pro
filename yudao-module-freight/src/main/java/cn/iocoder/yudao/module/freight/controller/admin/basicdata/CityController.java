package cn.iocoder.yudao.module.freight.controller.admin.basicdata;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CityPageReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CityRespVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CitySaveReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CityDO;
import cn.iocoder.yudao.module.freight.service.basicdata.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;

@Tag(name = "管理后台 - 货运 ERP 基础资料 - 城市")
@RestController
@RequestMapping("/freight/city")
@Validated
public class CityController {

    @Resource
    private CityService cityService;

    @PostMapping("/create")
    @Operation(summary = "创建城市")
    @PreAuthorize("@ss.hasPermission('freight:city:create')")
    public CommonResult<Long> createCity(@Valid @RequestBody CitySaveReqVO createReqVO) {
        return success(cityService.createCity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新城市")
    @PreAuthorize("@ss.hasPermission('freight:city:update')")
    public CommonResult<Boolean> updateCity(@Valid @RequestBody CitySaveReqVO updateReqVO) {
        cityService.updateCity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除城市")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('freight:city:delete')")
    public CommonResult<Boolean> deleteCity(@RequestParam("id") Long id) {
        cityService.deleteCity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得城市")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('freight:city:query')")
    public CommonResult<CityRespVO> getCity(@RequestParam("id") Long id) {
        CityDO city = cityService.getCity(id);
        return success(BeanUtils.toBean(city, CityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得城市分页")
    @PreAuthorize("@ss.hasPermission('freight:city:query')")
    public CommonResult<PageResult<CityRespVO>> getCityPage(@Valid CityPageReqVO pageReqVO) {
        PageResult<CityDO> pageResult = cityService.getCityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CityRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得城市精简列表", description = "主要用于前端的下拉选项")
    public CommonResult<List<CityRespVO>> getCitySimpleList() {
        List<CityDO> list = cityService.getCitySimpleList();
        return success(convertList(list, city -> new CityRespVO()
                .setId(city.getId())
                .setCityName(city.getCityName())
                .setCityCode(city.getCityCode())
                .setProvinceId(city.getProvinceId())
                .setUnlocode(city.getUnlocode())));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出城市 Excel")
    @PreAuthorize("@ss.hasPermission('freight:city:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCityExcel(@Valid CityPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CityDO> list = cityService.getCityList(pageReqVO);
        ExcelUtils.write(response, "城市.xls", "数据", CityRespVO.class,
                BeanUtils.toBean(list, CityRespVO.class));
    }

}
