package tech.edwardvan.mallmono.service;

import tech.edwardvan.mallmono.pojo.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 轮播图  服务类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface ICarouselService extends IService<Carousel> {

    public List<Carousel> queryAll(Integer isShow);
}
