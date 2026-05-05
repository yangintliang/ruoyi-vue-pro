package cn.iocoder.yudao.module.freight.dal.mysql.basicdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo.CountryPageReqVO;
import cn.iocoder.yudao.module.freight.dal.dataobject.basicdata.CountryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 国家 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CountryMapper extends BaseMapperX<CountryDO> {

    default PageResult<CountryDO> selectPage(CountryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CountryDO>()
                .likeIfPresent(CountryDO::getCountryName, reqVO.getCountryName())
                .eqIfPresent(CountryDO::getCountryCode, reqVO.getCountryCode())
                .likeIfPresent(CountryDO::getCountryEnName, reqVO.getCountryEnName())
                .betweenIfPresent(CountryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CountryDO::getId));
    }

    default List<CountryDO> selectList(CountryPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CountryDO>()
                .likeIfPresent(CountryDO::getCountryName, reqVO.getCountryName())
                .eqIfPresent(CountryDO::getCountryCode, reqVO.getCountryCode())
                .likeIfPresent(CountryDO::getCountryEnName, reqVO.getCountryEnName())
                .betweenIfPresent(CountryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CountryDO::getId));
    }

    default List<CountryDO> selectSimpleList() {
        return selectList(new LambdaQueryWrapperX<CountryDO>()
                .orderByAsc(CountryDO::getSort)
                .orderByDesc(CountryDO::getId));
    }

    default CountryDO selectByCountryCode(String countryCode) {
        return selectOne(CountryDO::getCountryCode, countryCode);
    }

}