package tech.edwardvan.msspringcloudproductcommon.api;

import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductSaveVo;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductUpdateVo;

/**
 * @author EdwardVan
 */
public interface ProductApi {
    ResponseResult<Product> getProduct(Integer productId);

    ResponseResult addProduct(ProductSaveVo productSaveVo);

    ResponseResult updateProduct(ProductUpdateVo productUpdateVo);

    ResponseResult deleteProduct(Integer productId);
}
