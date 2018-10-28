package per.wilson.cloud.consumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserConsumerApp
 *
 * @author Wilson
 * @date 18-8-22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrixDashboard
//@EnableCircuitBreaker
//@EnableTurbine
@RestController
@Api
public class UserConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApp.class, args);
    }

/*    @Value("${test.username}")
    private String username;

    @GetMapping("/username")
    public String username(){
        return username;
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }*/

    @ApiOperation("首页")
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
