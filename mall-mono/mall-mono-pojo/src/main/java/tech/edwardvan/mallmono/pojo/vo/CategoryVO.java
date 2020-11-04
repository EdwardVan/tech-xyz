package tech.edwardvan.mallmono.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分类展示对象(包含子分类)
 *
 * @author EdwardVan
 */
@Data
public class CategoryVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型")
    private Integer type;

    @ApiModelProperty(value = "父id")
    private Integer fatherId;

    /**
     * 子分类集合
     */
    List<CategoryVO> childs;
}
