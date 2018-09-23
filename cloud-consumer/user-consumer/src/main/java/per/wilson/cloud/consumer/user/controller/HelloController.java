package per.wilson.cloud.consumer.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wilson.cloud.consumer.user.service.HelloFeignService;

/**
 * HelloController
 *
 * @author Wilson
 * @date 18-8-26
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @Autowired
    private HelloFeignService helloFeignService;

    @GetMapping("/hello")
    public String hello() {
        return helloFeignService.helloService();
    }
}
