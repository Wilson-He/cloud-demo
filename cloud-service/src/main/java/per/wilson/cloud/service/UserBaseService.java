package per.wilson.cloud.service;

import per.wilson.cloud.model.UserBase;
import per.wilson.cloud.service.base.BaseService;

import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 
 * </p>
 * 
 * @author Wilson
 * @since 2018-10-28
 */
public interface UserBaseService extends BaseService<UserBase> {
    <K, V> Map<K, V> map(Function<UserBase, K> key, Function<UserBase, V> val);
}
