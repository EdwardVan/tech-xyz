package tech.edwardvan.mallmono.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分类-商品
 *
 * @author EdwardVan
 */
@Data
public class CategoryItemVO {
    @ApiModelProperty(value = "分类id")
    private Integer catId;

    @ApiModelProperty(value = "分类名称")
    private String catName;

    @ApiModelProperty(value = "口号")
    private String slogan;

    @ApiModelProperty(value = "分类图")
    private String catImage;

    @ApiModelProperty(value = "背景颜色")
    private String bgColor;

    /**
     * 商品集合
     */
    List<IndexItemVO> items;
}
