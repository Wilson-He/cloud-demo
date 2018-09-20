package per.wilson.cloud.config;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * @author: hewx
 * @date: 2018/9/20
 * @since:
 */
@Component
public class SwaggerResources implements SwaggerResourcesProvider {
  @Resource
  InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    resources.add(swaggerResource("user-provider", "http://localhost:51001/v2/api-docs", "0.0.1"));
    resources.add(swaggerResource("user-consumer", "http://localhost:52001/v2/api-docs", "0.0.0.1"));
    return resources;
  }

  private SwaggerResource swaggerResource(String name, String url, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setUrl(url);
    return swaggerResource;
  }
}
