package per.wilson.cloud.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * LongIdList
 *
 * @author Wilson
 * @date 2018/10/4
 */
@Getter
@Setter
public class LongIdList {

    @NotNull
    @Size(min = 1)
    private List<Long> id;
}
