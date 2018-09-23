package per.wilson.cloud.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: hewx
 * @date: 2018/9/20
 * @since:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .protocols(Sets.newHashSet("http", "https"))
        .pathMapping("/")
        .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("微服务模块文档")
        .description("文档说明")
        .termsOfServiceUrl("http://localhost:51001/")
        .contact(new Contact("Wilson", "http://blog.csdn.net/z28126308", "845023508@qq.com"))
        .license("Wilson_license")
        .version("latest")
        .build();
  }
}
