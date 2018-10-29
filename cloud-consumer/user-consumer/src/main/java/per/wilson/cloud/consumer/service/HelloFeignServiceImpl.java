package per.wilson.cloud.consumer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import per.wilson.cloud.vo.AddUserBaseVO;

/**
 * FallbackService
 *
 * @author Wilson
 * @date 18-8-26
 */
@Component
public class HelloFeignServiceImpl /*implements HelloFeignService*/ {
//    @Override
    public String helloService() {
        return "hystrix return";
    }

//    @Override
    public String insert(AddUserBaseVO vo) {
        return "insert";
    }

//    @Override
    public String pageUser(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
        return "pageUser";
    }
}
