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
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("items")
@ApiModel(value="Items对象", description="商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表")
public class Items extends Model<Items> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "商品名称 商品名称")
    private String itemName;

    @ApiModelProperty(value = "分类外键id 分类id")
    private Integer catId;

    @ApiModelProperty(value = "一级分类外键id")
    private Integer rootCatId;

    @ApiModelProperty(value = "累计销售 累计销售")
    private Integer sellCounts;

    @ApiModelProperty(value = "上下架状态 上下架状态,1:上架 2:下架")
    private Integer onOffStatus;

    @ApiModelProperty(value = "商品内容 商品内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
