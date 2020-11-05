package tech.edwardvan.mallmono.pojo.vo;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.edwardvan.mallmono.pojo.Items;
import tech.edwardvan.mallmono.pojo.ItemsImg;
import tech.edwardvan.mallmono.pojo.ItemsParam;
import tech.edwardvan.mallmono.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品详情VO
 *
 * @author EdwardVan
 */
@ApiModel(value="商品信息详情VO", description="商品信息详情VO")
@Data
@AllArgsConstructor
public class ItemInfoVO {

    /**
     * 商品信息
     */
    private Items item;
    /**
     * 商品图片
     */
    private List<ItemsImg> itemImgList;
    /**
     * 商品规格
     */
    private List<ItemsSpec> itemSpecList;
    /**
     * 商品参数
     */
    private ItemsParam itemParam;
}
