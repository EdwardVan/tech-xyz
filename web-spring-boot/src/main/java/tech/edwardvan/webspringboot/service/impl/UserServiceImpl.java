package tech.edwardvan.webspringboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.edwardvan.webspringboot.dao.UserMapper;
import tech.edwardvan.webspringboot.model.User;
import tech.edwardvan.webspringboot.service.IUserService;

/**
 * 用户Service
 *
 * @author EdwardVan
 */
@Service
@CacheConfig(cacheNames = "user")
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 将方法的运行结果进行缓存;以后再请求相同的数据,直接从缓存中获取,不用调用方法
     * CacheManager管理多个Cache组件的,对缓存的真正CRUD操作在Cache组件中,每一个缓存组件有自己唯一的名字
     * <p>
     * 原理:
     * 1.自动配置类:{@link org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration}
     * 2.缓存配置类:{@link org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration}[默认]
     * 3.给容器中注册了一个CacheManager
     * <p>
     * 核心:
     * 1.使用CacheManager按照名字得到Cache组件
     * 2.key使用keyGenerator生成的,默认是SimpleKeyGenerator
     * <p>
     * 注解属性:
     * cacheManager:指定缓存管理器;
     * cacheNames/value:指定缓存组件的名字;将方法的返回结果放在哪个缓存中,是数组的方式,可以指定多个缓存
     * key:缓存数据使用的key;默认是使用方法参数的值
     * keyGenerator:key的生成器;可以自己指定key的生成器的组件id;key和keyGenerator二选一使用
     * condition:指定符合条件的情况下才缓存
     * unless:当unless指定的条件为true,方法的返回值就不会被缓存;可以获取到结果进行判断
     * sync:是否使用异步模式
     * <p>
     * 注意:
     * 缓存对象要实现Serializable接口
     */
//    @Cacheable(keyGenerator = "testKeyGenerator")
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @CachePut(keyGenerator = "testKeyGenerator")
    @Override
    public User updateUserSelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    /**
     * CacheEvict注解属性
     * key:指定要清除的数据key
     * allEntries:是否清除这个缓存中所有的数据
     * beforeInvocation:缓存的清除是否在方法之前执行,默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     */
    @CacheEvict(keyGenerator = "testKeyGenerator")
    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 测试异步任务
     */
    @Async
    @Override
    public void testAsync() {
        log.info("执行UserServiceImpl.testAsync开始");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行UserServiceImpl.testAsync结束");
    }

    /**
     * 测试定时任务
     *
     * second(秒), minute(分), hour(时), day of month(日), month(月), day of week(周几).
     * [0 0/5 14,18 * * ?] 每天14点整,和18点整,每隔5分钟执行一次
     * [0 15 10 ? * 1-6] 每个月的周一至周六10:15分执行一次
     * [0 0 2 ? * 6L]每个月的最后一个周六凌晨2点执行一次
     * [0 0 2 LW * ?]每个月的最后一个工作日凌晨2点执行一次
     * [0 0 2-4 ? * 1#1]每个月的第一个周一凌晨2点到4点期间,每个整点都执行一次
     */
    @Scheduled(cron = "0 * * * * *")
    public void testSchedule() {
        log.info("执行UserServiceImpl.testSchedule");
    }
}
