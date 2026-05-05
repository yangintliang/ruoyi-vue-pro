package cn.iocoder.yudao.module.freight.dal.dataobject.basicdata;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 货运 ERP 基础资料 - 城市 DO
 *
 * @author 芋道源码
 */
@TableName("freight_basicdata_city")
@KeySequence("freight_basicdata_city_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDO extends TenantBaseDO {

    /**
     * 编号
     */
    @TableId(type=IdType.ASSIGN_ID)
    private Long id;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 所属省份业务 ID
     */
    private Long provinceId;
    /**
     * UN/LOCODE
     */
    private String unlocode;

}
