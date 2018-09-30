package per.wilson.cloud.provider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author Wilson
 * @date 2018/9/29
 */
@RestController
@RequestMapping("/")
@Api(description = "菜单控制器")
public class IndexController {
     /*   @Value("${test.username}")
    private String username;*/

    @GetMapping("/hello")
    public String hello() {
        System.err.println("here is producer HelloController");
        return "hello producer";
    }

    @GetMapping("/username")
    @ApiOperation("username")
    public String username(){
        return "username";
    }
}
