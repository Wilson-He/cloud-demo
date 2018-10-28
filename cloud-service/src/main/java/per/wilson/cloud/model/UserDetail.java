package per.wilson.cloud.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

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
@TableName("user_detail")
@ApiModel("")
public class UserDetail implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("")
    private Integer id;

    @ApiModelProperty("")
    private Integer age;

    @ApiModelProperty("")
    private String sex;

    @ApiModelProperty("")
    private Instant createTime;

    @ApiModelProperty("")
    private Instant updateTime;

    @TableLogic
    @JSONField(serialize = false)
    @ApiModelProperty(value = "", hidden = true)
    private String isDelete;

    @ApiModelProperty("")
    private String description;

}
