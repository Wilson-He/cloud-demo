package per.wilson.cloud.service.base;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import per.wilson.cloud.dao.BaseDAO;

import java.util.List;
import java.util.Optional;

/**
 * BaseServiceImpl
 *
 * @author Wilson
 * @since 2018-10-28
 */
public class BaseServiceImpl<T> implements BaseService<T>{
    @Autowired
    protected BaseDAO<T> baseDAO;

    @Override
    public T getByField(String field, Object value) {
        return Optional.ofNullable(baseDAO.selectList(new EntityWrapper<T>().eq(field, value).last("limit 0,1")))
                .map(e -> e.isEmpty() ? null : e.get(0))
                .orElse(null);
    }

    @Override
    public T getByWrapper(Wrapper<T> wrapper) {
       return Optional.ofNullable(baseDAO.selectList(wrapper.last("limit 0,1")))
                .map(e -> e.isEmpty() ? null : e.get(0))
                .orElse(null);
    }

    @Override
    public List<T> listByWrapper(Wrapper<T> wrapper) {
        return baseDAO.selectList(wrapper);
    }

    @Override
    public Page<T> pageByWrapper(Page<T> page,Wrapper<T> wrapper) {
        return page;
    }

    @Override
    public boolean insert(T t) {
        return baseDAO.insert(t) == 1;
    }

    @Override
    public boolean insertBatch(List<T> list) {
        return list.isEmpty() || baseDAO.insertBatch(list) == list.size();
    }

    @Override
    public boolean deleteByField(String field, Object value) {
        return baseDAO.delete(new EntityWrapper<T>().eq(field, value)) >= 0;
    }

    @Override
    public boolean update(T t){
        return baseDAO.updateById(t) == 1;
    }
}
