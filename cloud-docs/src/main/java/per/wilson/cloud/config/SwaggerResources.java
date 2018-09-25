package per.wilson.cloud.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hewx
 * @date: 2018/9/20
 * @since:
 */
@Component
public class SwaggerResources implements SwaggerResourcesProvider {

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    resources.add(swaggerResource("用户提供者模块", "/user-provider/v2/api-docs"));
    resources.add(swaggerResource("用户消费者模块", "/user-consumer/v2/api-docs"));
    return resources;
  }

  private SwaggerResource swaggerResource(String name, String url) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setUrl(url);
    return swaggerResource;
  }
}
