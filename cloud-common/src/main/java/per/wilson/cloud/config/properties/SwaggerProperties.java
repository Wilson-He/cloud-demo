package per.wilson.cloud.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * SwaggerProperties
 *
 * @author Wilson
 * @date 2018/9/29
 */
@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {
    private String title;
    private String serviceUrl;
    private String description;
    private String license;
    private String licenseUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String host;
    private String groupName;
    private String basePackage;


    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl(serviceUrl)
                .description(description)
                .licenseUrl(licenseUrl)
                .license(license)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }
}
