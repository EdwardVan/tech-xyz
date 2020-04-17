package tech.edwardvan.msspringcloudproductserver.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tech.edwardvan.msspringcloudclient.client.UserClient;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudproductcommon.api.ProductApi;
import tech.edwardvan.msspringcloudproductcommon.model.Product;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductSaveVo;
import tech.edwardvan.msspringcloudproductcommon.pojo.ProductUpdateVo;
import tech.edwardvan.msspringcloudproductserver.service.IProductService;
import tech.edwardvan.msspringcloudusercommon.model.User;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-06
 */
@Api(value = "商品模块", tags = "商品模块接口")
@RestController
@RequestMapping("/product")
public class ProductController implements ProductApi {
    @Autowired
    IProductService productService;

    @Autowired
    UserClient userClient;

    @Override
    @GetMapping(value = "/testProductTx")
    @ApiOperation(value = "测试分布式事务")
    public ResponseResult testProductTx() {
        int i = productService.testProductTx();
        return ResponseResult.SUCCESS(i);
    }

    @Override
    @GetMapping(value = "/{productId}")
    @ApiOperation(value = "获取商品信息")
    @ApiImplicitParam(paramType = "path", name = "productId", value = "商品id", required = true, dataType = "int")
    public ResponseResult<Product> getProduct(@PathVariable(value = "productId") Integer productId) {
        ResponseResult<User> user = userClient.getUser(1);
        return ResponseResult.SUCCESS(productService.getById(productId));
    }

    @Override
    @PostMapping
    @ApiOperation(value = "新增商品信息")
    public ResponseResult addProduct(@RequestBody @Validated ProductSaveVo productSaveVo) {
        Product product = new Product();
        BeanUtils.copyProperties(productSaveVo, product);
        if (productService.save(product)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.ERROR();
    }

    @Override
    @PutMapping
    @ApiOperation(value = "更新商品信息")
    public ResponseResult updateProduct(@RequestBody @Validated ProductUpdateVo productUpdateVo) {
        Product product = new Product();
        BeanUtils.copyProperties(productUpdateVo, product);
        if (productService.updateById(product)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.ERROR();
    }

    @Override
    @DeleteMapping(value = "/{productId}")
    @ApiOperation(value = "删除商品信息")
    @ApiImplicitParam(paramType = "path", name = "productId", value = "商品id", required = true, dataType = "int")
    public ResponseResult deleteProduct(@PathVariable(value = "productId") Integer productId) {
        if (productService.removeById(productId)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.ERROR();
    }
}

