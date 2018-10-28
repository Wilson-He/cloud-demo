package per.wilson.cloud.provider.service.impl;

import org.springframework.stereotype.Service;
import per.wilson.cloud.model.UserBase;
import per.wilson.cloud.provider.dao.UserBaseDAO;
import per.wilson.cloud.service.UserBaseService;
import per.wilson.cloud.service.base.BaseServiceImpl;
import per.wilson.cloud.utils.ListUtils;

import javax.annotation.Resource;
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
@Service
public class UserBaseServiceImpl extends BaseServiceImpl<UserBase> implements UserBaseService {
    @Resource
    private UserBaseDAO userBaseDAO;

    @Override
    public <K, V> Map<K, V> map(Function<UserBase, K> key, Function<UserBase, V> val) {
        return ListUtils.extract(userBaseDAO.selectList(null),key,val);
    }
}
