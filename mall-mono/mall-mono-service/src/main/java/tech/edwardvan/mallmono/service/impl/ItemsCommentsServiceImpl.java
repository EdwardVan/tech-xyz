package tech.edwardvan.mallmono.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.edwardvan.mallmono.common.enums.CommentLevel;
import tech.edwardvan.mallmono.common.utils.DesensitizationUtils;
import tech.edwardvan.mallmono.pojo.ItemsComments;
import tech.edwardvan.mallmono.mapper.ItemsCommentsMapper;
import tech.edwardvan.mallmono.pojo.vo.CommentLevelCountsVO;
import tech.edwardvan.mallmono.pojo.vo.ItemCommentVO;
import tech.edwardvan.mallmono.service.IItemsCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsComments> implements IItemsCommentsService {

    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        LambdaQueryWrapper<ItemsComments> goodWrapper = Wrappers.lambdaQuery();
        ;
        Integer goodCounts = baseMapper.selectCount(goodWrapper.eq(ItemsComments::getItemId, itemId).eq(ItemsComments::getCommentLevel, CommentLevel.GOOD.value));
        Integer normalCounts = baseMapper.selectCount(goodWrapper.eq(ItemsComments::getItemId, itemId).eq(ItemsComments::getCommentLevel, CommentLevel.NORMAL.value));
        Integer badCounts = baseMapper.selectCount(goodWrapper.eq(ItemsComments::getItemId, itemId).eq(ItemsComments::getCommentLevel, CommentLevel.BAD.value));
        Integer totalCounts = goodCounts + normalCounts + badCounts;
        return new CommentLevelCountsVO(totalCounts, goodCounts, normalCounts, badCounts);
    }

    @Override
    public Page<ItemCommentVO> queryPagedComments(Page page, String itemId, Integer level) {
        Page<ItemCommentVO> resultPage = baseMapper.queryItemComments(page, itemId, level);
        //信息脱敏
        resultPage.getRecords().forEach(r -> r.setNickname(DesensitizationUtils.commonDisplay(r.getNickname())));
        return resultPage;
    }
}
