package tech.edwardvan.mallmono.pojo.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/**
 * <p>
 * 用户登录
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@ApiModel("用户登录对象")
@Data
@Accessors(chain = true)
public class UserLogin {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能少于6位")
    private String password;
}
