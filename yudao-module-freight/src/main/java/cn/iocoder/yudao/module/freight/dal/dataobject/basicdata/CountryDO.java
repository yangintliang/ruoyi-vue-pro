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
 * 国家 DO
 *
 * @author 芋道源码
 */
@TableName("const_country")
@KeySequence("const_country_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDO extends TenantBaseDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 国家名称
     */
    private String countryName;
    /**
     * 国家代码
     */
    private String countryCode;
    /**
     * 国家英文名称
     */
    private String countryEnName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 时区
     */
    private String timezone;

}