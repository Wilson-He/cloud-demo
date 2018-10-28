package per.wilson.cloud.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * StringIdList
 *
 * @author Wilson
 * @date 2018/10/4
 */
@Setter
@Getter
public class StringIdList {

    @NotNull
    @Size(min = 1)
    private List<String> id;
}
