package tech.edwardvan.msspringcloudclient.fallback;

import org.springframework.stereotype.Component;
import tech.edwardvan.msspringcloudclient.client.ProductClient;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductSaveVo;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductUpdateVo;

/**
 * @author EdwardVan
 */
@Component
public class ProductFallbackService implements ProductClient {

    @Override
    public ResponseResult testProductTx() {
        return ResponseResult.ERROR("测试事务失败");
    }

    @Override
    public ResponseResult<Product> getProduct(Integer productId) {
        return ResponseResult.ERROR("获取商品失败");
    }

    @Override
    public ResponseResult addProduct(ProductSaveVo productSaveVo) {
        return ResponseResult.ERROR("新增商品失败");
    }

    @Override
    public ResponseResult updateProduct(ProductUpdateVo productUpdateVo) {
        return ResponseResult.ERROR("更新商品失败");
    }

    @Override
    public ResponseResult deleteProduct(Integer productId) {
        return ResponseResult.ERROR("删除商品失败");
    }
}
