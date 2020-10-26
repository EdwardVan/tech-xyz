package tech.edwardvan.mallmono.service;

import tech.edwardvan.mallmono.pojo.ItemsSpec;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface IItemsSpecService extends IService<ItemsSpec> {

}
