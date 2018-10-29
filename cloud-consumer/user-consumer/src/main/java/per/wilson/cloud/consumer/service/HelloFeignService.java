package per.wilson.cloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import per.wilson.cloud.vo.AddUserBaseVO;

/**
 * HelloFeignService-fallback服务降级，可省略fallback
 *
 * @author Wilson
 * @date 18-8-26
 */
@FeignClient(name = "user-provider")
public interface HelloFeignService {

    @GetMapping(value = "/hello")
    String helloService();

    @PostMapping(value = "/userBase/")
    Object insert(@RequestBody AddUserBaseVO vo);

    @GetMapping(value = "/userBase/page")
    Object pageUser(@RequestParam("page") Integer page,@RequestParam("size") Integer size);

}
