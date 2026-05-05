package cn.iocoder.yudao.module.freight.service.basicdata;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryImportExcelVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryImportRespVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryPageReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountrySaveReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CountryDO;
import cn.iocoder.yudao.module.freight.dal.mysql.basicdata.CountryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.freight.enums.ErrorCodeConstants.COUNTRY_CODE_DUPLICATE;
import static cn.iocoder.yudao.module.freight.enums.ErrorCodeConstants.COUNTRY_IMPORT_LIST_IS_EMPTY;
import static cn.iocoder.yudao.module.freight.enums.ErrorCodeConstants.COUNTRY_NAME_NOT_NULL;
import static cn.iocoder.yudao.module.freight.enums.ErrorCodeConstants.COUNTRY_NOT_EXISTS;

/**
 * 国家 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryMapper countryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCountry(CountrySaveReqVO createReqVO) {
        validateCountryCodeUnique(null, createReqVO.getCountryCode());

        CountryDO country = BeanUtils.toBean(createReqVO, CountryDO.class);
        countryMapper.insert(country);
        return country.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCountry(CountrySaveReqVO updateReqVO) {
        validateCountryExists(updateReqVO.getId());
        validateCountryCodeUnique(updateReqVO.getId(), updateReqVO.getCountryCode());

        CountryDO updateObj = BeanUtils.toBean(updateReqVO, CountryDO.class);
        countryMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCountry(Long id) {
        validateCountryExists(id);
        countryMapper.deleteById(id);
    }

    @Override
    public CountryDO getCountry(Long id) {
        return countryMapper.selectById(id);
    }

    @Override
    public PageResult<CountryDO> getCountryPage(CountryPageReqVO pageReqVO) {
        return countryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CountryDO> getCountrySimpleList() {
        return countryMapper.selectSimpleList();
    }

    @Override
    public List<CountryDO> getCountryList(CountryPageReqVO exportReqVO) {
        return countryMapper.selectList(exportReqVO);
    }

    // ========== 校验方法 ==========

    private void validateCountryExists(Long id) {
        if (countryMapper.selectById(id) == null) {
            throw exception(COUNTRY_NOT_EXISTS);
        }
    }

    private void validateCountryCodeUnique(Long id, String countryCode) {
        CountryDO country = countryMapper.selectByCountryCode(countryCode);
        if (country == null) {
            return;
        }
        if (id == null || !Objects.equals(country.getId(), id)) {
            throw exception(COUNTRY_CODE_DUPLICATE);
        }
    }

    // ========== 导入逻辑 ==========

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CountryImportRespVO importCountryList(List<CountryImportExcelVO> importList, boolean isUpdateSupport) {
        // 1. 过滤空数据
        importList = CollUtil.filterNew(importList, item -> Objects.nonNull(item.getCountryName()));
        if (CollUtil.isEmpty(importList)) {
            throw exception(COUNTRY_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 初始化结果
        CountryImportRespVO respVO = CountryImportRespVO.builder().createNames(new ArrayList<>())
                .updateNames(new ArrayList<>()).failureNames(new LinkedHashMap<>()).build();

        // 3. 逐条处理
        importList.forEach(importItem -> {
            try {
                validateForImport(importItem);
            } catch (Exception ex) {
                respVO.getFailureNames().put(importItem.getCountryName(), ex.getMessage());
                return;
            }

            CountryDO exist = countryMapper.selectByCountryCode(importItem.getCountryCode());

            if (exist == null) {
                CountryDO newObj = BeanUtils.toBean(importItem, CountryDO.class);
                countryMapper.insert(newObj);
                respVO.getCreateNames().add(importItem.getCountryName());
                return;
            }

            if (!isUpdateSupport) {
                respVO.getFailureNames().put(importItem.getCountryName(),
                        StrUtil.format("国家【{}】已存在", importItem.getCountryName()));
                return;
            }

            CountryDO updateObj = BeanUtils.toBean(importItem, CountryDO.class).setId(exist.getId());
            countryMapper.updateById(updateObj);
            respVO.getUpdateNames().add(importItem.getCountryName());
        });

        return respVO;
    }

    private void validateForImport(CountryImportExcelVO importItem) {
        if (StrUtil.isEmptyIfStr(importItem.getCountryName())) {
            throw exception(COUNTRY_NAME_NOT_NULL);
        }
    }

}
