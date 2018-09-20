package per.wilson.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: hewx
 * @date: 2018/9/20
 * @since:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigCenterApplication.class);
  }
}
