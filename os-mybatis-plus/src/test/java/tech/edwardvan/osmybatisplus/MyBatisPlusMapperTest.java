package tech.edwardvan.osmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.edwardvan.osmybatisplus.entity.User;
import tech.edwardvan.osmybatisplus.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyBatisPlusMapperTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 插入数据
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("username-test")
                .setPassword("password-test").setRole(1)
                .setRemark("remark-test")
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        log.warn("insert:{}", insert);
    }

    /**
     * 根据 ID 查询
     */
    @Test
    public void selectById() {
        User user = userMapper.selectById(1);
        log.warn("user:{}", user);
    }

    /**
     * 根据条件返回一条记录
     * 结果为多条时报错
     */
    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("username", "admin");
        User user = userMapper.selectOne(queryWrapper);
        log.warn("user:{}", user);
    }

    /**
     * 根据 ID 批量查询
     */
    @Test
    public void selectBatchIds() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        log.warn("selectBatchIds:{}", userList);
    }

    /**
     * 根据 columnMap 条件查询
     * key值对应数据库表中的列名,而不是对应实体对象中的字段名
     */
    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = Map.of("username", "admin", "role", 1);
        List<User> users = userMapper.selectByMap(columnMap);
        log.warn("selectByMap:{}", users);
    }

    /**
     * 通过条件构造器查询
     */
    @Test
    public void selectList() {
        //通过条件构造器QueryWrapper
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper
                .select("id", "username", "email")
                .nested(i -> i.eq("username", "admin").like("email", "@").apply("date_format(create_time,'%Y') = {0}", "2020"))
                .or(i -> i.inSql("id", "select id from demo_user").isNull("email"))
                .orderByDesc("create_time");
        log.warn("queryWrapperSql:{}", queryWrapper.getSqlSegment());
        List<User> queryWrapperList = userMapper.selectList(queryWrapper);
        log.warn("queryWrapperList:{}", queryWrapperList);

        //实体作为条件构造器构造方法的参数
        User paraUser = new User().setUsername("admin").setRole(1);
        QueryWrapper<User> userQueryWrapper = Wrappers.query(paraUser);
        List<User> userQueryWrapperList = userMapper.selectList(userQueryWrapper);
        log.warn("userQueryWrapperList:{}", userQueryWrapperList);
    }

    /**
     * 通过条件构造器查询,返回Map
     */
    @Test
    public void selectMaps() {
        User paraUser = new User().setUsername("admin").setRole(1);
        QueryWrapper<User> userQueryWrapper = Wrappers.query(paraUser);
        userQueryWrapper.select("id", "username", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        log.warn("maps:{}", maps);
    }


    /**
     * 通过条件构造器查询,只返回第一个字段的值
     */
    @Test
    public void selectObjs() {
        User paraUser = new User().setRole(1);
        QueryWrapper<User> userQueryWrapper = Wrappers.query(paraUser);
        userQueryWrapper.select("id", "username", "email");
        List<Object> objects = userMapper.selectObjs(userQueryWrapper);
        log.warn("objects:{}", objects);
    }

    /**
     * 通过条件构造器统计
     */
    @Test
    public void selectCount() {
        User paraUser = new User().setRole(1);
        QueryWrapper<User> userQueryWrapper = Wrappers.query(paraUser);
        Integer integer = userMapper.selectCount(userQueryWrapper);
        log.warn("integer:{}", integer);

    }

    /**
     * 通过Lambda条件构造器统计
     */
    @Test
    public void lambdaQueryWrapper() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper
                .select(User::getId, User::getUsername)
                .nested(i -> i.eq(User::getUsername, "admin").like(User::getEmail, "@"))
                .or(i -> i.inSql(User::getId, "select id from demo_user").isNull(User::getEmail))
                .orderByDesc(User::getCreateTime);
        List<Map<String, Object>> maps = userMapper.selectMaps(lambdaQueryWrapper);
        log.warn("maps:{}", maps);
    }

    /**
     * 通过Lambda条件构造器统计
     */
    @Test
    public void lambdaQueryChainWrapper() {
        Integer count = new LambdaQueryChainWrapper<User>(userMapper)
                .nested(i -> i.eq(User::getUsername, "admin").like(User::getEmail, "@"))
                .or(i -> i.inSql(User::getId, "select id from demo_user").isNull(User::getEmail))
                .orderByDesc(User::getCreateTime).count();
        log.warn("count:{}", count);
    }

    /**
     * 自定义sql
     */
    @Test
    public void getAll() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(User::getUsername, "admin");
        List<User> all = userMapper.getAll(lambdaQueryWrapper);
        log.warn("getAll:{}", all);
        List<User> selectAll = userMapper.selectAll(lambdaQueryWrapper);
        log.warn("selectAll:{}", selectAll);
    }

    /**
     * 分页查询
     */
    @Test
    public void selectPage() {
        Page<User> page = new Page<>(1, 5);
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(User::getRole, 1);
        Page<User> userPage = userMapper.selectPage(page, lambdaQueryWrapper);
        log.warn("userPage:{}", userPage);
    }

    /**
     * 分页查询
     */
    @Test
    public void selectMapsPage() {
        Page page = new Page<>(1, 5);
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.select(User::getId, User::getUsername).eq(User::getRole, 1);
        Page mapsPage = userMapper.selectMapsPage(page, lambdaQueryWrapper);
        log.warn("mapsPage:{}", mapsPage);
    }

    /**
     * 更新
     */
    @Test
    public void updateById() {
        User user = new User().setId(1).setEmail("admin@qq.com");
        int i = userMapper.updateById(user);
        log.warn("i:{}", i);
    }

    /**
     * 更新
     */
    @Test
    public void update() {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.set(User::getEmail, "edwardvan@qq.com").ge(User::getRole, 1).between(User::getId, 1, 4);
        int update = userMapper.update(null, lambdaUpdateWrapper);
        log.warn("update:{}", update);
    }


    /**
     * 根据id删除
     */
    @Test
    public void deleteById() {
        int i = userMapper.deleteById(8);
        log.warn("i:{}", i);
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.ge(User::getId, 7);
        int delete = userMapper.delete(lambdaQueryWrapper);
        log.warn("delete:{}", delete);
    }

}
