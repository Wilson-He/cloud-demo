package per.wilson.cloud.service;

import org.springframework.stereotype.Component;

/**
 * FallbackService
 *
 * @author Wilson
 * @date 18-8-26
 */
@Component
public class HelloFeignServiceImpl implements HelloFeignService{
    @Override
    public String helloService() {
        System.err.println("hello consumer feign????????????");
        return "hello consumer feign";
    }
}