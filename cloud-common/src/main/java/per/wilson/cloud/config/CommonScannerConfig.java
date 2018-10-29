package per.wilson.cloud.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * CommonScannerConfig
 *
 * @author Wilson
 * @date 2018/10/25
 */
@Configuration
@ComponentScan
@ConditionalOnProperty(value = "mybatis.plus.enabled", havingValue = "true")
public class CommonScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Properties ymlProperties) throws IOException {
        System.err.println("------------init mapperScannerConfigurer-------------------");
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(resourcePatternResolver.getResources("classpath*:application-mybatis.yml"));
        String daoPackage = ymlProperties.getProperty("mybatis.dao-package");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(daoPackage);
        return mapperScannerConfigurer;
    }
}
