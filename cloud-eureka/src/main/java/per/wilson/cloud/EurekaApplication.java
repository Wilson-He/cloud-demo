package per.wilson.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaApplication
 *
 * @author Wilson
 * @date 18-8-26
 */
@EnableEurekaServer
@SpringBootApplication
@EnableConfigServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
