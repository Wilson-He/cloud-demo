package per.wilson.cloud.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.Instant;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 * 
 * @author Wilson
 * @since 2018-10-28
 */
@ToString
@Setter
@Getter
@Accessors(chain = true)
@TableName("user_base")
@ApiModel("")
public class UserBase implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private Instant createTime;

    @ApiModelProperty("")
    private Instant updateTime;

    @TableLogic
    @JSONField(serialize = false)
    @ApiModelProperty(value = "", hidden = true)
    private String isDelete;

}
