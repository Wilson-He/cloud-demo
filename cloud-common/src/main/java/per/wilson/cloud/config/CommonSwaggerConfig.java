package per.wilson.cloud.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import per.wilson.cloud.config.properties.SwaggerProperties;
import per.wilson.cloud.constant.GlobalConstant;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * SwaggerConfig
 *
 * @author Wilson
 * @date 2018/9/29
 */
@Configuration
@EnableSwagger2
@ComponentScan
public class CommonSwaggerConfig {
    @Resource
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket userProviderDocket() {
        System.err.println("swagger init:" + swaggerProperties.toString());
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .apiInfo(swaggerProperties.apiInfo())
                .protocols(Sets.newHashSet("http", "https"))
                .select()
                .apis(RequestHandlerSelectors.basePackage(GlobalConstant.BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
}
