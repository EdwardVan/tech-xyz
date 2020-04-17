package tech.edwardvan.msspringcloudproductserver.service;

import tech.edwardvan.msspringcloudproductcommon.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-06
 */
public interface IProductService extends IService<Product> {

    int testProductTx();
}
