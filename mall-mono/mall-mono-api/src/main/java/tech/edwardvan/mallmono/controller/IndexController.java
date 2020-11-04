package tech.edwardvan.mallmono.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import tech.edwardvan.mallmono.common.enums.YesOrNo;
import tech.edwardvan.mallmono.common.utils.ServerResponse;
import tech.edwardvan.mallmono.pojo.Carousel;
import tech.edwardvan.mallmono.pojo.Category;
import tech.edwardvan.mallmono.pojo.vo.CategoryItemVO;
import tech.edwardvan.mallmono.pojo.vo.CategoryVO;
import tech.edwardvan.mallmono.service.ICarouselService;
import tech.edwardvan.mallmono.service.ICategoryService;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * <p>
 * 首页  前端控制器
 * </p>
 *
 * @author EdwardVan
 */
@Api(value = "首页", tags = "首页")
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private ICarouselService carouselService;

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "获取轮播图", notes = "获取轮播图")
    @GetMapping("carousel")
    public ServerResponse carousel() {
        List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.value);
        return ServerResponse.success(carousels);
    }

    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)")
    @GetMapping("rootCategory")
    public ServerResponse rootCategory() {
        List<Category> categories = categoryService.queryAllRootLevelCategorys();
        return ServerResponse.success(categories);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类")
    @ApiImplicitParam(paramType = "path", name = "id", value = "分类id", required = true)
    @GetMapping("subCategory/{id}")
    public ServerResponse subCategory(@PathVariable(value = "id") Integer id) {
        List<CategoryVO> subCategorys = categoryService.getSubCategorys(id);
        return ServerResponse.success(subCategorys);
    }

    @ApiOperation(value = "获取商品-分类", notes = "获取商品-分类")
    @ApiImplicitParam(paramType = "path", name = "id", value = "分类id", required = true)
    @GetMapping("categoryItems/{id}")
    public ServerResponse categoryItems(@PathVariable(value = "id") Integer id) {
        List<CategoryItemVO> categoryItems = categoryService.getCategoryItems(id);
        return ServerResponse.success(categoryItems);
    }

}

