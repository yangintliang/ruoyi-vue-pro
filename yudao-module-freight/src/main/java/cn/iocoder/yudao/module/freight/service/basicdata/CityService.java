package cn.iocoder.yudao.module.freight.service.basicdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CityPageReqVO;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CitySaveReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CityDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 城市 Service 接口
 *
 * @author 芋道源码
 */
public interface CityService {

    Long createCity(@Valid CitySaveReqVO createReqVO);

    void updateCity(@Valid CitySaveReqVO updateReqVO);

    void deleteCity(Long id);

    CityDO getCity(Long id);

    PageResult<CityDO> getCityPage(CityPageReqVO pageReqVO);

    List<CityDO> getCitySimpleList();

    List<CityDO> getCityList(CityPageReqVO exportReqVO);

}
