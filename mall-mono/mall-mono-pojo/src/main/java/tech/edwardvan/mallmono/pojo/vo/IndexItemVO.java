package tech.edwardvan.mallmono.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 首页展示的商品信息
 *
 * @author EdwardVan
 */
@Data
public class IndexItemVO {
    @ApiModelProperty(value = "商品id")
    private String itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "商品图片地址")
    private String itemUrl;
}
