package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.Category;
import tech.edwardvan.mallmono.mapper.CategoryMapper;
import tech.edwardvan.mallmono.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
