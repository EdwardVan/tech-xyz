package tech.edwardvan.msspringcloudproductserver.service.impl;

import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductserver.mapper.ProductMapper;
import tech.edwardvan.msspringcloudproductserver.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
