package per.wilson.cloud.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: hewx
 * @date: 2018/9/20
 * @since:
 */
@Component
@Primary
@ConfigurationProperties(prefix = "swagger.resources")
@Setter
public class SwaggerResources implements SwaggerResourcesProvider {
    private Map<String, String> moduleName;
    private Map<String, String> moduleDocsUrl;

    @Override
    public List<SwaggerResource> get() {
        if(moduleName == null || moduleName.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        List<SwaggerResource> resources = new ArrayList<>();
        moduleName.forEach((k,v) -> resources.add(swaggerResource(v,moduleDocsUrl.get(k))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String url) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(url);
        swaggerResource.setSwaggerVersion("latest");
        return swaggerResource;
    }

}
