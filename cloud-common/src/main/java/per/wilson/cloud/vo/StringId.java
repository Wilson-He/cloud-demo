package per.wilson.cloud.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * StringIdVO
 *
 * @author Wilson
 * @date 2018/10/4
 */
@Setter
@Getter
public class StringId {

    @NotBlank
    @Size(min = 1)
    private String id;
}
