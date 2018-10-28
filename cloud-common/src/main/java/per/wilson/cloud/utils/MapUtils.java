package per.wilson.cloud.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * MapUtils
 *
 * @author Wilson
 * @date 2018/10/2
 */
public class MapUtils {

    private Map<Object, Object> map;

    public static MapUtils build() {
        return new MapUtils(16);
    }

    public static MapUtils build(int capacity) {
        return new MapUtils(capacity);
    }

    public MapUtils put(Object key, Object value) {
        map.put(key, value);
        return this;
    }

    public MapUtils put(boolean condition, Object key, Object value) {
        if (condition) {
            map.put(key, value);
        }
        return this;
    }

    public MapUtils putIfAbsent(Object key, Object value) {
        map.putIfAbsent(key, value);
        return this;
    }

    public MapUtils remove(Object key) {
        map.remove(key);
        return this;
    }

    public MapUtils clear() {
        map.clear();
        return this;
    }

    public Map<Object, Object> map() {
        return map;
    }

    private MapUtils(int capacity) {
        map = new HashMap<>(capacity);
    }
}
