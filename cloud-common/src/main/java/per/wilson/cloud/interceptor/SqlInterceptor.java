package per.wilson.cloud.interceptor;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * StatementInterceptor
 *
 * @author Wilson
 * @date 2018/10/3
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class,
    ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "batch", args = Statement.class),
    @Signature(type = StatementHandler.class, method = "update", args = Statement.class)})
public class SqlInterceptor implements Interceptor {

  @Override
  public Object intercept(org.apache.ibatis.plugin.Invocation invocation) throws Throwable {
    long t = System.currentTimeMillis();
    Object result = invocation.proceed();
    long cost = System.currentTimeMillis() - t;
    StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    BoundSql boundSql = statementHandler.getBoundSql();
    DefaultParameterHandler parameterHandler = (DefaultParameterHandler) statementHandler
        .getParameterHandler();
    Field field = parameterHandler.getClass().getDeclaredField("configuration");
    field.setAccessible(true);
    System.out.println("执行时间:" + cost + "ms - sql:\n"
        + getSql(boundSql, (Configuration) field.get(parameterHandler)));
    return result;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {

  }

  private String getSql(BoundSql boundSql, Configuration configuration) {
    String sql = "\t"
        .concat(boundSql.getSql().replaceAll("\\n\\s+", "\n").replaceAll("\\n", "\n\t"));
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    Object parameterObject = boundSql.getParameterObject();
    TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    if (parameterMappings != null) {
      for (int i = 0; i < parameterMappings.size(); i++) {
        ParameterMapping parameterMapping = parameterMappings.get(i);
        if (parameterMapping.getMode() != ParameterMode.OUT) {
          Object value;
          String propertyName = parameterMapping.getProperty();
          if (boundSql.hasAdditionalParameter(propertyName)) {
            value = boundSql.getAdditionalParameter(propertyName);
          } else if (parameterObject == null) {
            value = null;
          } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            value = parameterObject;
          } else {
            //If parameterObject type is map,parameterObject will be instance of metaObject
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            value = metaObject.getValue(propertyName);
          }
          sql = replacePlaceholder(sql, value);
        }
      }
    }
    return sql;
  }

  private String replacePlaceholder(String sql, Object propertyValue) {
    String result;
    if (propertyValue != null) {
      if (propertyValue instanceof String) {
        result = "'" + propertyValue + "'";
      } else if (propertyValue instanceof Date) {
        result = "'" + DateFormatUtils.format((Date) propertyValue, "yyyy-MM-dd HH:mm:ss") + "'";
      } else {
        result = propertyValue.toString();
      }
    } else {
      result = "null";
    }
    return sql.replaceFirst("\\?", result);
  }
}