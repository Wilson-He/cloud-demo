package per.wilson.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloService
 *
 * @author Wilson
 * @date 18-8-22
 */
@FeignClient(name = "user-provider")
public interface HelloService {
    @RequestMapping("/user-provider/hello")
    String sayHello();
}
