package tech.edwardvan.mallmono.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.edwardvan.mallmono.pojo.Category;
import tech.edwardvan.mallmono.mapper.CategoryMapper;
import tech.edwardvan.mallmono.pojo.vo.CategoryItemVO;
import tech.edwardvan.mallmono.pojo.vo.CategoryVO;
import tech.edwardvan.mallmono.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<Category> queryAllRootLevelCategorys() {
        LambdaQueryWrapper<Category> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Category::getType, 1);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<CategoryVO> getSubCategorys(Integer id) {
        return baseMapper.getSubCategorysById(id);
    }

    @Override
    public List<CategoryItemVO> getCategoryItems(Integer id) {
        return baseMapper.getCategoryItemsById(id);
    }
}
