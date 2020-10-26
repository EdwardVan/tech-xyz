package tech.edwardvan.mallmono.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品分类 
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category")
@ApiModel(value="Category对象", description="商品分类 ")
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型")
    private Integer type;

    @ApiModelProperty(value = "父id")
    private Integer fatherId;

    @ApiModelProperty(value = "图标")
    private String logo;

    @ApiModelProperty(value = "口号")
    private String slogan;

    @ApiModelProperty(value = "分类图")
    private String catImage;

    @ApiModelProperty(value = "背景颜色")
    private String bgColor;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
