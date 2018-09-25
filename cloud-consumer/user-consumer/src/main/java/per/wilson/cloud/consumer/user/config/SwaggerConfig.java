package per.wilson.cloud.consumer.user.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import per.wilson.cloud.constant.GlobalConstant;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 *
 * @author Wilson
 * @date 18-7-10
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .pathMapping("/")
            .apiInfo(apiInfo())
            .protocols(Sets.newHashSet("http", "https"))
            .globalOperationParameters(globalParameters())
            .select()
            .apis(RequestHandlerSelectors.basePackage(GlobalConstant.BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("user-consumer")
            .termsOfServiceUrl("用户消费者模块")
            .version("latest")
            .termsOfServiceUrl("http://localhost:51001/")
            .contact(new Contact("Wilson", "http://blog.csdn.net/z28126308", "845023508@qq.com"))
            .license("Wilson_license")
            .licenseUrl("http://blog.csdn.net/z28126308")
            .build();
    }

    private List<Parameter> globalParameters() {
        List<Parameter> list = new ArrayList<>();
        list.add(new ParameterBuilder()
            .name("token")
            .modelRef(new ModelRef("string"))
            .description("令牌").defaultValue("1")
            .required(false)
            .parameterType("header")
            .build());
        return list;
    }
}
