package per.wilson.cloud.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author: hewx
 * @date: 2018/10/25
 * @since:
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static GenericApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = (GenericApplicationContext) applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> void registerBean(String beanName, Class<T> beanClass, Supplier<T> supplier,
                                        BeanDefinitionCustomizer... customizers) {
        applicationContext.registerBean(beanName, beanClass, supplier, customizers);
    }

}
