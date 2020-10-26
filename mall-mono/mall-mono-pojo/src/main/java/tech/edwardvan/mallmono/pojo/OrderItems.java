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
 * 订单商品关联表 
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_items")
@ApiModel(value="OrderItems对象", description="订单商品关联表 ")
public class OrderItems extends Model<OrderItems> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "归属订单id")
    private String orderId;

    @ApiModelProperty(value = "商品id")
    private String itemId;

    @ApiModelProperty(value = "商品图片")
    private String itemImg;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "规格id")
    private String itemSpecId;

    @ApiModelProperty(value = "规格名称")
    private String itemSpecName;

    @ApiModelProperty(value = "成交价格")
    private Integer price;

    @ApiModelProperty(value = "购买数量")
    private Integer buyCounts;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
