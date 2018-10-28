package per.wilson.cloud.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;

/**
 * ObjectUtils
 *
 * @author Wilson
 * @date 18-7-10
 */
public class ObjectUtils {

    public static <T> T copyProperties(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> Method toGetMethod(Class<T> t, String field) {
        String getter = StringUtils.join("get", StringUtils.capitalize(field));
        try {
            return t.getMethod(getter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Method toSetMethod(Class<T> t, String field, Class<?> clazz) {
        String setter = StringUtils.join("set", StringUtils.capitalize(field));
        try {
            return t.getMethod(setter, clazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
