package per.wilson.cloud.interceptor;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.wilson.cloud.utils.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * ParameterValidation
 *
 * @author Wilson
 * @date 18-7-11
 */
@Component
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final static String PATTERN_ARG = "arg\\d+";

  @Resource
  private ReloadableResourceBundleMessageSource messageSource;

  /**
   * post api请求vo属性参数校验错误处理
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object postParamExceptionHandler(MethodArgumentNotValidException exception)
      throws NoSuchFieldException {
    List<String> errors = new ArrayList<>();
    Class voClass = exception.getParameter().getParameterType();
    for (FieldError each : exception.getBindingResult().getFieldErrors()) {
      String defaultMsg = each.getDefaultMessage();
      if (!Objects.equals(defaultMsg,
          messageSource.getMessage(each.getCode(), each.getArguments(), Locale.getDefault()))) {
        errors.add(defaultMsg);
        continue;
      }
      String fieldName = each.getField();
      Field field = voClass.getDeclaredField(fieldName);
      field.setAccessible(true);
      ApiModelProperty modelProperty = field.getAnnotation(ApiModelProperty.class);
      String head;
      if (modelProperty == null || StringUtils.isEmpty(modelProperty.value())) {
        head = fieldName;
      } else {
        String value = modelProperty.value();
        head = value.contains("(") ? value.split("\\(")[0] : value;
      }
      errors.add(StringUtils.join(head, defaultMsg));
    }
    return msg(HttpStatus.BAD_REQUEST, errors);
  }

  /**
   * get api 请求参数校验错误处理,参数需加@RequestParam
   */
 /* @ExceptionHandler({ConstraintViolationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object getMappingParamExceptionHandler(ConstraintViolationException exception) {
    List<ConstraintViolation<?>> list = exception.getConstraintViolations()
        .stream()
        .filter(e -> e.getPropertyPath().toString().contains("arg"))
        .collect(Collectors.toList());
    List<String> errors = new ArrayList<>(list.size());
    ConstraintViolation first = list.get(0);
    System.err.println("first:" + first);
    System.err.println("all:" + exception.getConstraintViolations());
    String methodName = first.getPropertyPath().toString().split("\\.")[0];
    int paramSize = first.getExecutableParameters().length;
    Method method = Arrays.stream(first.getRootBeanClass().getMethods())
        .filter(e -> e.getParameters().length == paramSize && methodName.equals(e.getName()))
        .findFirst()
        .orElse(null);
    Parameter[] parameters = method.getParameters();
    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    List<String> paramNames = Arrays.asList(parameterNameDiscoverer.getParameterNames(method));
    for (ConstraintViolation each : list) {
      if (!each.getMessageTemplate().startsWith("{")) {
        errors.add(each.getMessageTemplate());
        continue;
      }
      String parameterIndex = each.getPropertyPath().toString().split("\\.")[1];
      int index = Integer.parseInt(parameterIndex.substring(parameterIndex.length() - 1));
      Parameter parameter = parameters[index];
      ApiParam param = parameter.getDeclaredAnnotation(ApiParam.class);
      String head;
      if (param == null || StringUtils.isEmpty(param.value())) {
        head = paramNames.get(index);
      } else {
        String value = param.value();
        //常量分隔(如:状态(1-xx,0-xxx) 截取'状态')
        head = value.contains("(") ? value.split("\\(")[0] : value;
      }
      errors.add(StringUtils.join(head, each.getMessage()));
    }
    return msg(HttpStatus.BAD_REQUEST, errors);
  }*/
  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object getParamExceptionHandler(ConstraintViolationException exception) {
    ConstraintViolation first = new ArrayList<>(exception.getConstraintViolations()).get(0);
    if (!first.getMessageTemplate().startsWith("{")) {
      return msg(HttpStatus.BAD_REQUEST, first.getMessageTemplate());
    }
    String[] methodParam = first.getPropertyPath().toString().split("\\.");
    // 方法的参数数目
    int paramSize = first.getExecutableParameters().length;
    Method method = Arrays.stream(first.getRootBeanClass().getMethods())
        .filter(e -> e.getParameters().length == paramSize && methodParam[0].equals(e.getName()))
        .findFirst()
        .orElse(null);
    Parameter[] parameters = method.getParameters();
    // 校验出错的参数名
    String validationParamName = methodParam[1];
    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    List<String> paramNames = Arrays.asList(parameterNameDiscoverer.getParameterNames(method));
    if (validationParamName.matches(PATTERN_ARG)) {
      int index = Integer.parseInt(StringUtils.substringAfterLast(validationParamName, "arg"));
      return getApiParamResponse(parameters[index], paramNames.get(index), first.getMessage());
    }
    int index = paramNames.indexOf(validationParamName);
    return getApiParamResponse(parameters[index], validationParamName, first.getMessage());
  }

  private Object getApiParamResponse(Parameter parameter, String paramName, String msg) {
    ApiParam param = parameter.getDeclaredAnnotation(ApiParam.class);
    if (param == null || StringUtils.isEmpty(param.value())) {
      return msg(HttpStatus.BAD_REQUEST, StringUtils.join(paramName, msg));
    } else {
      //常量分隔(如:状态(1-xx,0-xxx) 截取'状态')
      return msg(HttpStatus.BAD_REQUEST,
          StringUtils.join(StringUtils.substringBefore(param.value(), "("), msg));
    }
  }

  @ExceptionHandler(UnauthenticatedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Object unauthenticatedExceptionHandler() {
    return msg(HttpStatus.UNAUTHORIZED, "请先登录");
  }

  @ExceptionHandler({UnknownAccountException.class, IncorrectCredentialsException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Object accountExceptionHandler() {
    return msg(HttpStatus.UNAUTHORIZED, "帐号密码不匹配");
  }

  @ExceptionHandler(AuthorizationException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public Object unauthorizedExceptionHandler() {
    return msg(HttpStatus.NOT_ACCEPTABLE, "权限不足");
  }

  private Object msg(HttpStatus statusCode, Object msg) {
    Map<String, Object> map = new HashMap<>(2);
    map.put("code", statusCode.value());
    map.put("msg", msg);
    return new ResponseEntity<>(map, statusCode);
  }

}
