package per.wilson.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserConsumerApp
 *
 * @author Wilson
 * @date 18-8-22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableTurbine
@Controller
public class UserConsumerApp {
    //http://localhost:52001/hystrix.stream
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApp.class, args);
    }

    @RequestMapping("/")
    public String home(){
        return "forward:/hystrix";
    }
}
