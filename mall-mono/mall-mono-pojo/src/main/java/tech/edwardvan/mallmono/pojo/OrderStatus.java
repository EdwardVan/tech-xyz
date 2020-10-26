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
 * 订单状态表;订单的每个状态更改都需要进行记录
10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
退货/退货，此分支流程不做，所以不加入
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_status")
/**
 * 订单状态表;订单的每个状态更改都需要进行记录
 * 10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
 * 退货/退货，此分支流程不做，所以不加入
 */
@ApiModel(value="OrderStatus对象", description="")
public class OrderStatus extends Model<OrderStatus> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID;对应订单表的主键id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单创建时间;对应[10:待付款]状态")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "支付成功时间;对应[20:已付款，待发货]状态")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "发货时间;对应[30：已发货，待收货]状态")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "交易成功时间;对应[40：交易成功]状态")
    private LocalDateTime successTime;

    @ApiModelProperty(value = "交易关闭时间;对应[50：交易关闭]状态")
    private LocalDateTime closeTime;

    @ApiModelProperty(value = "留言时间;用户在交易成功后的留言时间")
    private LocalDateTime commentTime;


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
