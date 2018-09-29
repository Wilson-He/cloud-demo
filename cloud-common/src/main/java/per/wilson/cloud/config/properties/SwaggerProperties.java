package per.wilson.cloud.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Setter
@Getter
@Component
@ConfigurationProperties("swagger")
@ToString
public class SwaggerProperties {
    private String title;
    private String serviceUrl;
    private String descrpition;
    private String license;
    private String licenseUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;


    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl(serviceUrl)
                .description(descrpition)
                .licenseUrl(licenseUrl)
                .license(license)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }
}
