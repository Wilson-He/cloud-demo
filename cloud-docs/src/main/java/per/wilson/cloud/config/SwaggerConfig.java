package per.wilson.cloud.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
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
@EnableSwagger2
@Configuration
public class SwaggerConfig {
  @Bean
  public Docket userProviderDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("user-provider", "user-provider", "1.0.1", "Wilson", contact(),
        "Wilson_license", "license-url", new ArrayList<>());
  }

  private Contact contact() {
    return new Contact("Wilson", "Wilson.csdn", "845023508@qq.com");
  }

}
