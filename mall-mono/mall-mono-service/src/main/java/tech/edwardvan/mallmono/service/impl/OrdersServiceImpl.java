package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.Orders;
import tech.edwardvan.mallmono.mapper.OrdersMapper;
import tech.edwardvan.mallmono.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表; 服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
