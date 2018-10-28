package per.wilson.cloud.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: hewx
 * @date: 2018/10/23
 * @since:
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final char UNDERLINE = '_';

    /**
     * <p>
     * 字符串下划线转驼峰格式
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    /**
     * <p>
     * 字符串下划线转驼峰格式
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * 首字母转换小写
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(param.length());
        sb.append(param.substring(0, 1).toLowerCase());
        sb.append(param.substring(1));
        return sb.toString();
    }

    /**
     * <p>
     * 判断字符串是否为纯大写字母
     * </p>
     *
     * @param str 要匹配的字符串
     */
    public static boolean isUpperCase(String str) {
        return match("^[A-Z]+$", str);
    }

    /**
     * <p>
     * 正则表达式匹配
     * </p>
     *
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private StringUtils() {
    }
}
