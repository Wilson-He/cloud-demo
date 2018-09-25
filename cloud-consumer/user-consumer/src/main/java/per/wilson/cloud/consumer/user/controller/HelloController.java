package per.wilson.cloud.consumer.user.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wilson.cloud.consumer.user.User;
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

    @PostMapping("/user")
    @ApiResponses({@ApiResponse(code = 201,response = User.class,message = "success")})
    public String user(@RequestBody User user) {
        return user.toString();
    }
}
