package per.wilson.cloud.model;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * PageResult
 *
 * @author Wilson
 * @date 2018/10/2
 */
@Setter
@Getter
@ToString
public class PageResult<T> implements Serializable {

    // 当前页
    private Integer page;
    // 当前页数目
    private Integer size;
    // 总页数
    private Integer totalPage;
    // 总数目
    private Long totalSize;
    // 分页结果列表
    private List<T> list;

    public static final PageResult<Object> EMPTY = new PageResult<>(1, 0, 1, 0L,
            Collections.emptyList());

    public PageResult(Integer page, Integer size, Integer totalPage, Long totalSize, List<T> list) {
        this.page = page;
        this.size = size;
        this.totalPage = totalPage;
        this.totalSize = totalSize;
        this.list = list;
    }


    public PageResult(PageInfo<T> pageInfo) {
        this.page = pageInfo.getPageNum();
        this.size = pageInfo.getPageSize();
        this.totalPage = pageInfo.getPages();
        this.totalSize = pageInfo.getTotal();
    }

    public boolean checkEmpty() {
        return list == null || list.isEmpty();
    }
}
