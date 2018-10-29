package per.wilson.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wilson.cloud.CommonBaseScan;

/**
 * UserProApplication
 *
 * @author Wilson
 * @date 2018/9/29
 */
@SpringBootApplication
@Import(CommonBaseScan.class)
@EnableDiscoveryClient
@RestController
public class UserProApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProApplication.class);
    }

    @GetMapping("/hello")
    public Object hello(){
        return "hello";
    }

}
