package cn.iocoder.yudao.module.freight.service.basicdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryImportExcelVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryImportRespVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryPageReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountrySaveReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CountryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 国家 Service 接口
 *
 * @author 芋道源码
 */
public interface CountryService {

    Long createCountry(@Valid CountrySaveReqVO createReqVO);

    void updateCountry(@Valid CountrySaveReqVO updateReqVO);

    void deleteCountry(Long id);

    CountryDO getCountry(Long id);

    PageResult<CountryDO> getCountryPage(CountryPageReqVO pageReqVO);

    List<CountryDO> getCountrySimpleList();

    List<CountryDO> getCountryList(CountryPageReqVO exportReqVO);

    CountryImportRespVO importCountryList(List<CountryImportExcelVO> importList, boolean isUpdateSupport);

}
