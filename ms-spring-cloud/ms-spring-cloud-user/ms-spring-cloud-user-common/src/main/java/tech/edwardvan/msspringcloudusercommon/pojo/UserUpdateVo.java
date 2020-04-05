package tech.edwardvan.msspringcloudusercommon.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 新增User
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-05
 */
@Data
@ApiModel(value = "更新User对象", description = "")
public class UserUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotEmpty(message = "用户密码不能为空")
    private String password;
    @ApiModelProperty(value = "邮箱密码")
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "电话密码")
    @NotEmpty(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "找回密码问题")
    private String question;

    @ApiModelProperty(value = "找回密码答案")
    private String answer;

    @ApiModelProperty(value = "角色0-管理员,1-普通用户")
    @Range(min = 0, max = 1, message = "角色只能输入0或1")
    private Integer role;
}
