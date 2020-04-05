package tech.edwardvan.msspringcloudproductcommon.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author EdwardVan
 */
@Data
public class ProductUpdateVo implements Serializable {

    @ApiModelProperty(value = "商品id")
    @NotEmpty(message = "商品id 不能为空")
    private Integer id;

    @ApiModelProperty(value = "分类id,对应demo_category表的主键")
    @NotEmpty(message = "分类id 不能为空")
    private Integer categoryId;

    @ApiModelProperty(value = "商品名称")
    @NotEmpty(message = "商品名称 不能为空")
    private String name;

    @ApiModelProperty(value = "商品副标题")
    private String subtitle;

    @ApiModelProperty(value = "产品主图,url相对地址")
    private String mainImage;

    @ApiModelProperty(value = "图片地址,json格式,扩展用")
    private String subImages;

    @ApiModelProperty(value = "商品详情")
    private String detail;

    @ApiModelProperty(value = "价格,单位-元保留两位小数")
    @NotNull(message = "价格 不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "库存数量")
    @NotNull(message = "库存数量 不能为空")
    private Integer stock;

    @ApiModelProperty(value = "商品状态.1-在售 2-下架")
    private Integer status;
}
