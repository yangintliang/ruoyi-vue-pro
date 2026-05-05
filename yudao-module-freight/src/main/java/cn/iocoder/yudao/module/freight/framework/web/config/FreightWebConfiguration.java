package cn.iocoder.yudao.module.freight.framework.web.config;

import cn.iocoder.yudao.framework.swagger.config.YudaoSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * freight 模块的 web 组件的 Configuration。
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class FreightWebConfiguration {

    /**
     * freight 模块的 API 分组。
     */
    @Bean
    public GroupedOpenApi freightGroupedOpenApi() {
        return YudaoSwaggerAutoConfiguration.buildGroupedOpenApi("freight");
    }

}
