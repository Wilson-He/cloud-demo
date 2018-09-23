package per.wilson.cloud.consumer.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloFeignService
 *
 * @author Wilson
 * @date 18-8-26
 */
@FeignClient(name = "user-provider", fallback = HelloFeignServiceImpl.class)
public interface HelloFeignService {

    @GetMapping(value = "/hello")
    String helloService();

}
