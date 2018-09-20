package per.wilson.cloud.provider.user.config;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import per.wilson.cloud.provider.user.constant.GlobalConstant;
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
    public Docket userProviderDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .pathMapping("/")
            .groupName("user-consumer")
            .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
            .apiInfo(apiInfo())
            .protocols(Sets.newHashSet("http", "https"))
            .globalOperationParameters(globalParameters())
            .select()
            .apis(RequestHandlerSelectors.basePackage(GlobalConstant.BASE_PACKAGE + ".consumer"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("user-consumer", "user-consumer", "1.0.1", "Wilson", contact(),
            "Wilson_license", "license-url", new ArrayList<>());
    }

    private Contact contact() {
        return new Contact("Wilson", "Wilson.csdn", "845023508@qq.com");
    }

    private List<Parameter> globalParameters() {
        List<Parameter> list = new ArrayList<>();
        list.add(new ParameterBuilder()
            .name("debug")
            .modelRef(new ModelRef("string"))
            .description("令牌").defaultValue("1")
            .required(false)
            .parameterType("header")
            .build());
        return list;
    }
}
