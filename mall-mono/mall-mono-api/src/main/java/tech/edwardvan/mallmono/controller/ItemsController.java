package tech.edwardvan.mallmono.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.edwardvan.mallmono.common.utils.ServerResponse;

import tech.edwardvan.mallmono.pojo.Items;
import tech.edwardvan.mallmono.pojo.ItemsImg;
import tech.edwardvan.mallmono.pojo.ItemsParam;
import tech.edwardvan.mallmono.pojo.ItemsSpec;
import tech.edwardvan.mallmono.pojo.vo.CommentLevelCountsVO;
import tech.edwardvan.mallmono.pojo.vo.ItemCommentVO;
import tech.edwardvan.mallmono.pojo.vo.ItemInfoVO;
import tech.edwardvan.mallmono.service.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 前端控制器
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Api(value = "商品详情页", tags = "商品详情页")
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private IItemsService itemsService;

    @Autowired
    private IItemsImgService itemsImgService;

    @Autowired
    private IItemsSpecService itemsSpecService;

    @Autowired
    private IItemsParamService itemsParamService;

    @Autowired
    private IItemsCommentsService itemsCommentsService;

    @ApiOperation(value = "获取商品详情", notes = "获取商品详情")
    @ApiImplicitParam(paramType = "path", name = "itemId", value = "商品id", required = true)
    @GetMapping("/info/{itemId}")
    public ServerResponse subCategory(@NotBlank @PathVariable(value = "itemId") String itemId) {
        Items item = itemsService.getById(itemId);
        List<ItemsImg> itemImgList = itemsImgService.list(Wrappers.<ItemsImg>lambdaQuery().eq(ItemsImg::getItemId, itemId));
        List<ItemsSpec> itemSpecList = itemsSpecService.list(Wrappers.<ItemsSpec>lambdaQuery().eq(ItemsSpec::getItemId, itemId));
        ItemsParam itemParam = itemsParamService.getOne(Wrappers.<ItemsParam>lambdaQuery().eq(ItemsParam::getItemId, itemId));
        ItemInfoVO itemInfoVO = new ItemInfoVO(item, itemImgList, itemSpecList, itemParam);
        return ServerResponse.success(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @ApiImplicitParam(paramType = "path", name = "itemId", value = "商品id", required = true)
    @GetMapping("/commentLevel/{itemId}")
    public ServerResponse commentLevel(@PathVariable(value = "itemId") String itemId) {
        CommentLevelCountsVO commentLevelCountsVO = itemsCommentsService.queryCommentCounts(itemId);
        return ServerResponse.success(commentLevelCountsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "itemId", value = "商品id", required = true),
            @ApiImplicitParam(paramType = "query", name = "level", value = "评价等级", required = false),
            @ApiImplicitParam(paramType = "query", name = "current", value = "当前页", required = false),
            @ApiImplicitParam(paramType = "query", name = "size", value = "每页显示条数", required = false)
    })
    @GetMapping("/comments")
    public ServerResponse comments(@NotBlank String itemId, Integer level, Long current, Long size) {
        Page<ItemCommentVO> page = new Page<>();
        if (current != null) {
            page.setCurrent(current);
        }
        if (size != null) {
            page.setSize(size);
        }
        page = itemsCommentsService.queryPagedComments(page, itemId, level);
        return ServerResponse.success(page);
    }

}

