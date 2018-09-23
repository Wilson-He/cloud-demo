package per.wilson.cloud;

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
public class UserProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApp.class);
    }

    @Value("${test.username}")
    private String username;

    @GetMapping("/username")
    public String username(){
        return username;
    }

    @GetMapping("/hello")
    public String hello(){
        System.err.println("here is producer HelloController");
        return "hello producer";
    }
}
