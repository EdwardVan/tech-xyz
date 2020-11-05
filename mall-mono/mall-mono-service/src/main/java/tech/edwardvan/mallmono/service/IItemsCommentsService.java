package tech.edwardvan.mallmono.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.edwardvan.mallmono.pojo.ItemsComments;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.edwardvan.mallmono.pojo.vo.CommentLevelCountsVO;
import tech.edwardvan.mallmono.pojo.vo.ItemCommentVO;

/**
 * <p>
 * 商品评价表  服务类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface IItemsCommentsService extends IService<ItemsComments> {

    /**
     * 查询商品的评论数目
     *
     * @param itemId 商品id
     * @return
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 获取商品评价
     * @param page 分页对象
     * @param itemId 商品id
     * @param level 商品评价等级
     * @return 分页对象
     */
    Page<ItemCommentVO> queryPagedComments(Page page, String itemId, Integer level);
}
