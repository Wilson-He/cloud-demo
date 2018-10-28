package per.wilson.cloud.provider.dao;

import per.wilson.cloud.dao.BaseDAO;
import per.wilson.cloud.model.UserDetail;
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
public interface UserDetailDAO extends BaseDAO<UserDetail> {
    List<UserDetail> listByParam(Map<String, Object> param);
}
