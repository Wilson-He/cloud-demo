package per.wilson.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * DataSourceProperties
 *
 * @author Wilson
 * @date 18-7-10
 */
@Slf4j
@Setter
@Getter
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ConfigurationProperties("spring.tx")
public class DataSourceConfig {
    private Map<String, String> attributes;

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    @ConditionalOnProperty(value = "jdbc.druid.enabled", havingValue = "true")
    public DruidDataSource dataSource() {
        log.info("-----------------init jdbc success-------------------");
        return new DruidDataSource();
    }

    /**
     * 配置事务管理器
     *
     * @return 事务管理器
     */
    @Bean("transactionManager")
    @ConditionalOnBean(DataSource.class)
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 初始化事务拦截器，配置类须注解@EnableTransactionManagement才可生效
     */
    @Bean
    @ConditionalOnBean(PlatformTransactionManager.class)
    public TransactionInterceptor transactionInterceptor(
            PlatformTransactionManager transactionManager) {
        Properties properties = new Properties();
        properties.put("*", "PROPAGATION_REQUIRED");
        String format = "%s*";
        if (attributes != null) {
            attributes.forEach((k, v) -> properties.put(String.format(format, k), v));
        }
        log.info("tx properties:" + properties);
        return new TransactionInterceptor(transactionManager, properties);
    }
}
