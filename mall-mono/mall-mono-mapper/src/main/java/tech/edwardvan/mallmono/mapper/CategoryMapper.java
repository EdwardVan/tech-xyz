package tech.edwardvan.mallmono.mapper;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.mallmono.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.edwardvan.mallmono.pojo.vo.CategoryItemVO;
import tech.edwardvan.mallmono.pojo.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 * 商品分类  Mapper 接口
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 通过id获取子分类
     *
     * @param id 分类id
     * @return 子分类集合
     */
    List<CategoryVO> getSubCategorysById(@Param("id") Integer id);

    /**
     * 通过分类id 获取分类-商品集合
     *
     * @param id 分类id
     * @return 分类-商品集合
     */
    List<CategoryItemVO> getCategoryItemsById(@Param("id") Integer id);
}
