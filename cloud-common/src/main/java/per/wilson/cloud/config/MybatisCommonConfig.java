package per.wilson.cloud.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import per.wilson.cloud.utils.ListUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MybatisCommonConfig
 * <li>MybatisScannerConfigurer放进该配置类配置将报错</li>
 * <li>该类若不以Mybatis为名称前缀将报错</li>
 *
 * @author Wilson
 * @date 2018/10/3
 */
@Slf4j
@Setter
@Configuration
@ConfigurationProperties("mybatis")
@ConditionalOnProperty(value = "mybatis.plus.enabled", havingValue = "true")
@ToString
public class MybatisCommonConfig {

    private String mappersPath;
    private String modelPackage;
    private String logicDelete = "1";
    private String logicNotDelete = "0";
    private Boolean sqlPrintPlugin = true;
    private Boolean optimisticLockerPlugin = false;
    private Boolean paginationPlugin = true;
    private Boolean pageHelperPlugin = true;
    private String dialect = "mysql";
    private String daoPackage;
    private BeanDefinitionRegistry registry;

    @PostConstruct
    public void init() {
        log.info("init CommonMybatisConfig{mappers-path:{},model-package:{},dao-package{}", mappersPath,
                modelPackage, daoPackage);
    }

    @Bean
    public TransactionFactory transactionFactory() {
        return new SpringManagedTransactionFactory();
    }

    @Bean
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        return configuration;
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    public MybatisSqlSessionFactoryBean sqlSessionFactory(MybatisConfiguration mybatisConfiguration,
                                                          DataSource dataSource,
                                                          GlobalConfiguration globalConfig) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources(mappersPath));
        // 设置映射类所在包
        sqlSessionFactory.setTypeAliasesPackage(modelPackage);
        sqlSessionFactory.setConfiguration(mybatisConfiguration);
        sqlSessionFactory.setPlugins(interceptors());
        sqlSessionFactory.setGlobalConfig(globalConfig);
        return sqlSessionFactory;
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue(logicDelete);
        conf.setLogicNotDeleteValue(logicNotDelete);
        conf.setDbType(dialect);
        conf.setIdType(IdType.ID_WORKER.getKey());
        return conf;
    }

    private Interceptor[] interceptors() {
        if (!(pageHelperPlugin || paginationPlugin || optimisticLockerPlugin || sqlPrintPlugin)) {
            return null;
        }
        List<Interceptor> list = new ArrayList<>(4);
        ListUtils.add(pageHelperPlugin, list, createPageInterceptor());
        ListUtils.add(paginationPlugin, list, new PaginationInterceptor());
        ListUtils.add(optimisticLockerPlugin, list, new OptimisticLockerInterceptor());
        ListUtils.add(sqlPrintPlugin, list, new PerformanceInterceptor());
        Interceptor[] interceptors = new Interceptor[list.size()];
        list.toArray(interceptors);
        return interceptors;
    }

    private PageInterceptor createPageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", dialect);
        properties.put("offsetAsPageNum", "true");
        properties.put("reasonable", "true");
        properties.put("rowBoundsWithCount", "true");
        properties.put("pageSizeZero", "true");
        properties.put("params", "pageNum=start;pageSize=limit;pageSizeZero=zero;count=countSql");
        properties.put("supportMethodsArguments", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}