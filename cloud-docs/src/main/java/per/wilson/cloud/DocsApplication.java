package per.wilson.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: hewx
 * @date: 2018/9/20
 * @since:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class DocsApplication {

  public static void main(String[] args) {
    SpringApplication.run(DocsApplication.class);
  }
}
