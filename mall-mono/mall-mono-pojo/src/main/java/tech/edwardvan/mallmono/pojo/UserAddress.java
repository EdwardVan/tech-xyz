package tech.edwardvan.mallmono.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户地址表 
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_address")
@ApiModel(value="UserAddress对象", description="用户地址表 ")
public class UserAddress extends Model<UserAddress> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "关联用户id")
    private String userId;

    @ApiModelProperty(value = "收件人姓名")
    private String receiver;

    @ApiModelProperty(value = "收件人手机号")
    private String mobile;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String detail;

    @ApiModelProperty(value = "扩展字段")
    private String extand;

    @ApiModelProperty(value = "是否默认地址")
    private Integer isDefault;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
