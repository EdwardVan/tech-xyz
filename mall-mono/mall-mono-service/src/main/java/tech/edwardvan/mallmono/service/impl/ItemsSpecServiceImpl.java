package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.ItemsSpec;
import tech.edwardvan.mallmono.mapper.ItemsSpecMapper;
import tech.edwardvan.mallmono.service.IItemsSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class ItemsSpecServiceImpl extends ServiceImpl<ItemsSpecMapper, ItemsSpec> implements IItemsSpecService {

}
