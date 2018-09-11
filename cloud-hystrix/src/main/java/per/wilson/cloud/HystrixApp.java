package per.wilson.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * author: hewx
 * date: 2018/9/11
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApp.class);
    }
}
