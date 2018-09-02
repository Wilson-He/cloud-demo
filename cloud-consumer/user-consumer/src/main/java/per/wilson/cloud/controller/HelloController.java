package per.wilson.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wilson.cloud.service.HelloFeignService;

import javax.annotation.Resource;

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
    public String index() {
        return helloFeignService.helloService();
    }
}
