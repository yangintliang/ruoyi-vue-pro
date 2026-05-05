package cn.iocoder.yudao.module.freight.dal.mysql.basicdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CityPageReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 城市 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CityMapper extends BaseMapperX<CityDO> {

    default PageResult<CityDO> selectPage(CityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CityDO>()
                .likeIfPresent(CityDO::getCityName, reqVO.getCityName())
                .eqIfPresent(CityDO::getCityCode, reqVO.getCityCode())
                .eqIfPresent(CityDO::getProvinceId, reqVO.getProvinceId())
                .eqIfPresent(CityDO::getUnlocode, reqVO.getUnlocode())
                .betweenIfPresent(CityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CityDO::getId));
    }

    default List<CityDO> selectList(CityPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CityDO>()
                .likeIfPresent(CityDO::getCityName, reqVO.getCityName())
                .eqIfPresent(CityDO::getCityCode, reqVO.getCityCode())
                .eqIfPresent(CityDO::getProvinceId, reqVO.getProvinceId())
                .eqIfPresent(CityDO::getUnlocode, reqVO.getUnlocode())
                .betweenIfPresent(CityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CityDO::getId));
    }

    default List<CityDO> selectSimpleList() {
        return selectList(new LambdaQueryWrapperX<CityDO>()
                .orderByAsc(CityDO::getCityName)
                .orderByDesc(CityDO::getId));
    }

    default CityDO selectByCityCode(String cityCode) {
        return selectOne(CityDO::getCityCode, cityCode);
    }

}
