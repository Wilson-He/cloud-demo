package per.wilson.cloud.consumer.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.ToString;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Wilson
 * @since 2018-07-10
 */
@TableName("tb_user")
@ApiModel("")
@ToString
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("")
    @NotBlank
    private String password;

    @ApiModelProperty("")
    @NotNull
    @Future
    private Instant createTime;

    @ApiModelProperty("")
    private Instant updateTime;

    @TableLogic
    @JSONField(serialize = false)
    @ApiModelProperty(value = "", hidden = true)
    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
