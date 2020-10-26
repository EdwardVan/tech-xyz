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
 * 商品参数 
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("items_param")
@ApiModel(value="ItemsParam对象", description="商品参数 ")
public class ItemsParam extends Model<ItemsParam> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品参数id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "商品外键id")
    private String itemId;

    @ApiModelProperty(value = "产地 产地，例：中国江苏")
    private String producPlace;

    @ApiModelProperty(value = "保质期 保质期，例：180天")
    private String footPeriod;

    @ApiModelProperty(value = "品牌名 品牌名，例：三只大灰狼")
    private String brand;

    @ApiModelProperty(value = "生产厂名 生产厂名，例：大灰狼工厂")
    private String factoryName;

    @ApiModelProperty(value = "生产厂址 生产厂址，例：大灰狼生产基地")
    private String factoryAddress;

    @ApiModelProperty(value = "包装方式 包装方式，例：袋装")
    private String packagingMethod;

    @ApiModelProperty(value = "规格重量 规格重量，例：35g")
    private String weight;

    @ApiModelProperty(value = "存储方法 存储方法，例：常温5~25°")
    private String storageMethod;

    @ApiModelProperty(value = "食用方式 食用方式，例：开袋即食")
    private String eatMethod;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
