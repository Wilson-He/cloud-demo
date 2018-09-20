package per.wilson.cloud.provider.user.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wilson.cloud.provider.user.service.HelloFeignService;

/**
 * HelloController
 *
 * @author Wilson
 * @date 18-8-26
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @Resource
    private HelloFeignService helloFeignService;

    @GetMapping("/hello")
    public String hello() {
        return helloFeignService.helloService();
    }
}
