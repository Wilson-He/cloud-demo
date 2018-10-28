package per.wilson.cloud.utils;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author: Wilson
 * @date: 2018/9/30
 * @since:
 */
public class ListUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 树状返回对象列表
     *
     * @param list            源对象列表(如菜单列表、分类列表)
     * @param idGetFunc       对象id get方法
     * @param parentIdGetFunc 对象parentId get方法
     * @param setSonsFunc     对象的子对象列表set方法(如Classify::setSubClassifies)
     * @param <T>             源对象Class
     * @param <P>             源对象id Class
     */
    public static <T, P> List<T> tree(List<T> list, Function<T, P> idGetFunc,
                                      Function<T, P> parentIdGetFunc, BiConsumer<T, List<T>> setSonsFunc) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        int size = list.size();
        // id-obj映射
        Map<P, T> currentMap = new HashMap<>(size);
        // parentId-List<son>映射
        Map<P, List<T>> sonsMap = new HashMap<>(size);
        for (T each : list) {
            currentMap.put(idGetFunc.apply(each), each);
            P parentId = parentIdGetFunc.apply(each);
            if (sonsMap.containsKey(parentId)) {
                sonsMap.get(parentId).add(each);
            } else {
                sonsMap.put(parentId, Lists.newArrayList(each));
            }
        }
        currentMap.forEach((k, v) -> setSonsFunc.accept(v, sonsMap.get(k)));
        return sonsMap.get(null);
    }

    /**
     * 提取列表对象某字段成列表
     */
    public static <T, V> List<V> extract(List<T> list, Function<T, V> fieldFuc) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<V> fields = new ArrayList<>(list.size());
        for (T each : list) {
            fields.add(fieldFuc.apply(each));
        }
        return fields;
    }

    /**
     * 提取列表对象2字段成映射
     */
    public static <T, K, V> Map<K, V> extract(List<T> list, Function<T, K> keyFunc,
                                              Function<T, V> valueFuc) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for (T each : list) {
            map.put(keyFunc.apply(each), valueFuc.apply(each));
        }
        return map;
    }

    public static <T> List<T> add(boolean condition, List<T> list, T obj) {
        if (condition) {
            list.add(obj);
        }
        return list;
    }

    private ListUtils() {
    }
}
