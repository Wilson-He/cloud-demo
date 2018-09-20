package per.wilson.cloud.provider.user.service;

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
        return "hystrix return";
    }
}
