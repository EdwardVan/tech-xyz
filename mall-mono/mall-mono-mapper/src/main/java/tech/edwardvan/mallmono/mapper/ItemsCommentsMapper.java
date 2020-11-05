package tech.edwardvan.mallmono.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tech.edwardvan.mallmono.pojo.ItemsComments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.edwardvan.mallmono.pojo.vo.ItemCommentVO;

/**
 * <p>
 * 商品评价表  Mapper 接口
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface ItemsCommentsMapper extends BaseMapper<ItemsComments> {

    /**
     * 获取商品评论
     *
     * @param page   分页对象
     * @param itemId 商品id
     * @param level  商品评价等级
     * @return
     */
    Page<ItemCommentVO> queryItemComments(Page page, @Param("itemId") String itemId, @Param("level") Integer level);
}
