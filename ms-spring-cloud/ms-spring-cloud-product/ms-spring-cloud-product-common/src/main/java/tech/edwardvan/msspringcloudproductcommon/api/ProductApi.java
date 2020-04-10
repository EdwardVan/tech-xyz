package tech.edwardvan.msspringcloudproductcommon.api;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductSaveVo;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductUpdateVo;

/**
 * @author EdwardVan
 */
@RequestMapping("/product")
public interface ProductApi {
    @GetMapping(value = "/{productId}")
    ResponseResult<Product> getProduct(@PathVariable(value = "productId") Integer productId);

    @PostMapping
    ResponseResult addProduct(@RequestBody @Validated ProductSaveVo productSaveVo);

    @PutMapping
    ResponseResult updateProduct(@RequestBody @Validated ProductUpdateVo productUpdateVo);

    @DeleteMapping(value = "/{productId}")
    ResponseResult deleteProduct(@PathVariable(value = "productId") Integer productId);
}
