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
@Primary
public class SwaggerResources implements SwaggerResourcesProvider {

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    resources.add(swaggerResource("user-provider", "/user-provider/v2/api-docs", "0.1"));
    resources.add(swaggerResource("user-consumer", "/user-consumer/v2/api-docs", "0.1"));
    return resources;
  }

  private SwaggerResource swaggerResource(String name, String url, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setUrl(url);
    return swaggerResource;
  }
}
