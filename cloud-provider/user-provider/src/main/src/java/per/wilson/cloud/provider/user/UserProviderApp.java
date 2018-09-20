package per.wilson.cloud.provider.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserProvideApp
 *
 * @author Wilson
 * @date 18-8-22
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/")
@Api
public class UserProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApp.class);
    }

    @Value("${test.username}")
    String username;

    @GetMapping("/username")
    @ApiOperation("username")
    public String username(){
        return username;
    }
}
