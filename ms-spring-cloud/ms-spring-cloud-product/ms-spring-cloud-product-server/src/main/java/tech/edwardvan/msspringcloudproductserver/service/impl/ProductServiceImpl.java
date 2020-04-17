package tech.edwardvan.msspringcloudproductserver.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import tech.edwardvan.msspringcloudclient.client.UserClient;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductserver.mapper.ProductMapper;
import tech.edwardvan.msspringcloudproductserver.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    UserClient userClient;

    @Override
    @GlobalTransactional
    public int testProductTx() {
        Product product = new Product().setCategoryId(1).setName("test-tx").setPrice(BigDecimal.valueOf(100)).setStock(100);
        int insert = baseMapper.insert(product);
        ResponseResult responseResult = userClient.testUserTx();
        return insert;
    }
}
