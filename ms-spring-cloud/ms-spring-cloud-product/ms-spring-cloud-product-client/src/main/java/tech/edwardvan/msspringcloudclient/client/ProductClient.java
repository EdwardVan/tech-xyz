package tech.edwardvan.msspringcloudclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudproductcommon.api.ProductApi;
import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductSaveVo;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductUpdateVo;

/**
 * @author EdwardVan
 */
@FeignClient(name = "product-service")
public interface ProductClient extends ProductApi {

    @Override
    @GetMapping("/product/testProductTx")
    ResponseResult testProductTx();

    @Override
    @GetMapping("/product/{productId}")
    ResponseResult<Product> getProduct(@PathVariable(value = "productId") Integer productId);

    @Override
    @PostMapping("/product")
    ResponseResult addProduct(@RequestBody @Validated ProductSaveVo productSaveVo);

    @Override
    @PutMapping("/product")
    ResponseResult updateProduct(@RequestBody @Validated ProductUpdateVo productUpdateVo);

    @Override
    @DeleteMapping("/product/{productId}")
    ResponseResult deleteProduct(@PathVariable(value = "productId") Integer productId);

}
