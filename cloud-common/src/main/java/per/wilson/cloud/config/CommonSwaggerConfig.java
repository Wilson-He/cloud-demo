package per.wilson.cloud.config;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import per.wilson.cloud.config.properties.SwaggerProperties;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 *
 * @author Wilson
 * @date 2018/9/29
 */
@Configuration
@EnableSwagger2
@Slf4j
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class CommonSwaggerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonSwaggerConfig.class);

    @Resource
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket userProviderDocket() {
        LOGGER.info("init swagger:" + swaggerProperties.toString());
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .groupName(swaggerProperties.getGroupName())
                .apiInfo(swaggerProperties.apiInfo())
                .protocols(Sets.newHashSet("http", "https"))
                .globalResponseMessage(RequestMethod.GET, responseMessageList())
                .globalResponseMessage(RequestMethod.PUT, responseMessageList())
                .globalResponseMessage(RequestMethod.POST, responseMessageList())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private List<ResponseMessage> responseMessageList() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(buildResponseMessage(401, "请先登录"));
        list.add(buildResponseMessage(404, "页面不存在"));
        list.add(buildResponseMessage(406, "权限不足"));
        list.add(buildResponseMessage(500, "系统内部错误"));
        return list;
    }

    private ResponseMessage buildResponseMessage(int code, String msg) {
        return new ResponseMessageBuilder()
                .code(code)
                .message(msg)
                .build();
    }
}
