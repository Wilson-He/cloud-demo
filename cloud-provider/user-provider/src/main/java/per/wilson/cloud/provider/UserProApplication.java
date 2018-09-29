package per.wilson.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import per.wilson.cloud.config.CommonSwaggerConfig;

/**
 * UserProApplication
 *
 * @author Wilson
 * @date 2018/9/29
 */
@SpringBootApplication
@Import(CommonSwaggerConfig.class)
public class UserProApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProApplication.class);
    }
}
