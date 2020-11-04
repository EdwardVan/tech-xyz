package tech.edwardvan.mallmono.service;

import tech.edwardvan.mallmono.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.edwardvan.mallmono.pojo.vo.CategoryItemVO;
import tech.edwardvan.mallmono.pojo.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 * 商品分类  服务类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 获取所有根节点分类
     *
     * @return 分类集合
     */
    List<Category> queryAllRootLevelCategorys();

    /**
     * 获取指定分类id对应的子分类集合
     *
     * @param id 分类id
     * @return 子分类集合
     */
    List<CategoryVO> getSubCategorys(Integer id);

    /**
     * 获取分类及对应的商品集合
     *
     * @param id 分类id
     * @return 分类-商品集合
     */
    List<CategoryItemVO> getCategoryItems(Integer id);
}
