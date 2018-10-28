package per.wilson.cloud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseDAO
 *
 * @author Wilson
 * @since 2018-10-28
 */
public interface BaseDAO<T> extends BaseMapper<T> {
    int insertBatch(@Param("list") List<T> list);
}
