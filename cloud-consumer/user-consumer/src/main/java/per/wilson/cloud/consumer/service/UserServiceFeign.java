package per.wilson.cloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloFeignService
 *
 * @author Wilson
 * @date 18-8-26
 */
@FeignClient(name = "user-provider")
public interface UserServiceFeign {

    @GetMapping(value = "/page")
    Object map();

}
