package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.OrderItems;
import tech.edwardvan.mallmono.mapper.OrderItemsMapper;
import tech.edwardvan.mallmono.service.IOrderItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品关联表  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper, OrderItems> implements IOrderItemsService {

}
