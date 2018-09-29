package per.wilson.cloud.provider.service;

import org.springframework.stereotype.Service;
import per.wilson.cloud.service.HelloService;

/**
 * UserService
 *
 * @author Wilson
 * @date 18-8-26
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello";
    }
}
