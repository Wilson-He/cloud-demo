package per.wilson.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
