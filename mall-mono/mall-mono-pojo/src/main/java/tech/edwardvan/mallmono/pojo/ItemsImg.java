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
 * 商品图片 
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("items_img")
@ApiModel(value="ItemsImg对象", description="商品图片 ")
public class ItemsImg extends Model<ItemsImg> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "商品外键id 商品外键id")
    private String itemId;

    @ApiModelProperty(value = "图片地址 图片地址")
    private String url;

    @ApiModelProperty(value = "顺序 图片顺序，从小到大")
    private Integer sort;

    @ApiModelProperty(value = "是否主图 是否主图，1：是，0：否")
    private Integer isMain;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
