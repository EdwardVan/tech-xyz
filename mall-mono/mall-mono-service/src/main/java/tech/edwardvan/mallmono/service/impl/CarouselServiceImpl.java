package tech.edwardvan.mallmono.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.edwardvan.mallmono.pojo.Carousel;
import tech.edwardvan.mallmono.mapper.CarouselMapper;
import tech.edwardvan.mallmono.service.ICarouselService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 轮播图  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements ICarouselService {

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        LambdaQueryWrapper<Carousel> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Carousel::getIsShow, isShow).orderByAsc(Carousel::getSort);
        return baseMapper.selectList(wrapper);
    }
}
