package tech.edwardvan.webspringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.edwardvan.webspringboot.dao.UserMapper;
import tech.edwardvan.webspringboot.model.User;
import tech.edwardvan.webspringboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    
    /**
     * 将方法的运行结果进行缓存;以后再请求相同的数据,直接从缓存中获取,不用调用方法
     * CacheManager管理多个Cache组件的,对缓存的真正CRUD操作在Cache组件中,每一个缓存组件有自己唯一的名字
     *
     * 原理:
     *   1.自动配置类:{@link org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration}
     *   2.缓存配置类:{@link org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration}[默认]
     *   3.给容器中注册了一个CacheManager
     *
     * 核心:
     *   1.使用CacheManager按照名字得到Cache组件
     *   2.key使用keyGenerator生成的,默认是SimpleKeyGenerator
     *
     * 注解属性:
     *   cacheManager:指定缓存管理器;
     *   cacheNames/value:指定缓存组件的名字;将方法的返回结果放在哪个缓存中,是数组的方式,可以指定多个缓存
     *   key:缓存数据使用的key;默认是使用方法参数的值
     *   keyGenerator:key的生成器;可以自己指定key的生成器的组件id;key和keyGenerator二选一使用
     *   condition:指定符合条件的情况下才缓存
     *   unless:当unless指定的条件为true,方法的返回值就不会被缓存;可以获取到结果进行判断
     *   sync:是否使用异步模式
     *
     * 注意:
     *   缓存对象要实现Serializable接口
     */
    @Cacheable(cacheNames = "user")
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
