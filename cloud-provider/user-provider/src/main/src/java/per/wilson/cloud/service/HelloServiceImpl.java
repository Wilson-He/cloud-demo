package per.wilson.cloud.service;

import org.springframework.stereotype.Service;

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
