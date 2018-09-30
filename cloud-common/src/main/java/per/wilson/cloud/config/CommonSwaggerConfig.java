package per.wilson.cloud.config;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Slf4j
public class CommonSwaggerConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonSwaggerConfig.class);

  @Resource
  private SwaggerProperties swaggerProperties;

  @Bean
  public Docket userProviderDocket() {
    LOGGER.info("swagger init:" + swaggerProperties.toString());
    return new Docket(DocumentationType.SWAGGER_2)
        .pathMapping("/")
        .host(swaggerProperties.getHost())
        .groupName(swaggerProperties.getGroupName())
        .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
        .apiInfo(swaggerProperties.apiInfo())
        .protocols(Sets.newHashSet("http", "https"))
        .select()
        .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
        .paths(PathSelectors.any())
        .build();
  }
}
