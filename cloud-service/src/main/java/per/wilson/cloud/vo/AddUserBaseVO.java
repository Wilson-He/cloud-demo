package per.wilson.cloud.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import per.wilson.cloud.model.UserBase;

/**
 * AddUserBaseVO
 *
 * @author Wilson
 * @since 2018-10-28
 */
@Getter
@Setter
@ToString
@ApiModel("添加vo")
public class AddUserBaseVO{
    String name;

    public UserBase toUserBase(){
        UserBase model = new UserBase();
        BeanUtils.copyProperties(this,model);
        return model;
    }

}
