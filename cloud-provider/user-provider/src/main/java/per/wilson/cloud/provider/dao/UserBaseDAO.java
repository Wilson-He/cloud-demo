package per.wilson.cloud.provider.dao;

import per.wilson.cloud.dao.BaseDAO;
import per.wilson.cloud.model.UserBase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
    * 
    * </p>
 * 
 * @author Wilson
 * @since 2018-10-28
 */
@Repository
public interface UserBaseDAO extends BaseDAO<UserBase> {
    List<UserBase> listByParam(Map<String, Object> param);
}
