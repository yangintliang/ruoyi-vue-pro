package cn.iocoder.yudao.module.freight.service.basicdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CityPageReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CitySaveReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CityDO;
import cn.iocoder.yudao.module.freight.dal.mysql.basicdata.CityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.freight.enums.ErrorCodeConstants.CITY_CODE_EXISTS;
import static cn.iocoder.yudao.module.freight.enums.ErrorCodeConstants.CITY_NOT_EXISTS;

/**
 * 城市 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCity(CitySaveReqVO createReqVO) {
        validateCityCodeUnique(null, createReqVO.getCityCode());

        CityDO city = BeanUtils.toBean(createReqVO, CityDO.class);
        cityMapper.insert(city);
        return city.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCity(CitySaveReqVO updateReqVO) {
        validateCityExists(updateReqVO.getId());
        validateCityCodeUnique(updateReqVO.getId(), updateReqVO.getCityCode());

        CityDO updateObj = BeanUtils.toBean(updateReqVO, CityDO.class);
        cityMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCity(Long id) {
        validateCityExists(id);
        cityMapper.deleteById(id);
    }

    @Override
    public CityDO getCity(Long id) {
        return cityMapper.selectById(id);
    }

    @Override
    public PageResult<CityDO> getCityPage(CityPageReqVO pageReqVO) {
        return cityMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CityDO> getCitySimpleList() {
        return cityMapper.selectSimpleList();
    }

    @Override
    public List<CityDO> getCityList(CityPageReqVO exportReqVO) {
        return cityMapper.selectList(exportReqVO);
    }

    private void validateCityExists(Long id) {
        if (cityMapper.selectById(id) == null) {
            throw exception(CITY_NOT_EXISTS);
        }
    }

    private void validateCityCodeUnique(Long id, String cityCode) {
        CityDO city = cityMapper.selectByCityCode(cityCode);
        if (city == null) {
            return;
        }
        if (id == null || !Objects.equals(city.getId(), id)) {
            throw exception(CITY_CODE_EXISTS);
        }
    }

}
