package per.wilson.cloud.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * LongIdVO
 *
 * @author Wilson
 * @date 2018/10/4
 */
@Setter
@Getter
public class LongId {

    @NotNull
    private Long id;
}
