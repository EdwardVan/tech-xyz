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
 * 轮播图 
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("carousel")
@ApiModel(value="Carousel对象", description="轮播图 ")
public class Carousel extends Model<Carousel> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "图片 图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "背景色")
    private String backgroundColor;

    @ApiModelProperty(value = "商品id 商品id")
    private String itemId;

    @ApiModelProperty(value = "商品分类id 商品分类id")
    private String catId;

    @ApiModelProperty(value = "轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类")
    private Integer type;

    @ApiModelProperty(value = "轮播图展示顺序")
    private Integer sort;

    @ApiModelProperty(value = "是否展示")
    private Integer isShow;

    @ApiModelProperty(value = "创建时间 创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间 更新")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
