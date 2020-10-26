package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.ItemsComments;
import tech.edwardvan.mallmono.mapper.ItemsCommentsMapper;
import tech.edwardvan.mallmono.service.IItemsCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsComments> implements IItemsCommentsService {

}
