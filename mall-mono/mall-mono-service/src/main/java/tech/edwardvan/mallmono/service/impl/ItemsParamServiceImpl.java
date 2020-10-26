package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.ItemsParam;
import tech.edwardvan.mallmono.mapper.ItemsParamMapper;
import tech.edwardvan.mallmono.service.IItemsParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品参数  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class ItemsParamServiceImpl extends ServiceImpl<ItemsParamMapper, ItemsParam> implements IItemsParamService {

}
