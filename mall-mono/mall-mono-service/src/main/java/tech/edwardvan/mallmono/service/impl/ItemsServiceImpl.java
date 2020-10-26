package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.Items;
import tech.edwardvan.mallmono.mapper.ItemsMapper;
import tech.edwardvan.mallmono.service.IItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

}
