package tech.edwardvan.osmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.edwardvan.osmybatisplus.entity.User;
import tech.edwardvan.osmybatisplus.mapper.UserMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyBatisPlusTest {

    @Autowired
    UserMapper userMapper;

    /**
     * 插入
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("username-test").setPassword("password-test").setRole(1).setRemark("remark-test")
                .setCreateTime(LocalDateTime.now()).setUpdateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        log.warn("insert:{}", insert);
    }

    /**
     * 查询
     */
    @Test
    public void select() {
        //通过主键查询
        User user = userMapper.selectById(1);
        log.warn("user:{}", user);

        //通过id集合查询
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        log.warn("users:{}", userList);

        //通过columnMap查询(key值对应数据库表中的列名,而不是对应实体对象中的字段名)
        Map<String, Object> columnMap = Map.of("username", "admin", "role", 1);
        List<User> usersByColumnMap = userMapper.selectByMap(columnMap);
        log.warn("usersByColumnMap:{}", usersByColumnMap);

        //通过条件构造器QueryWrapper
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper
                .select("id", "username", "email")
                .and(i -> i.eq("username", "admin").like("email", "@"))
                .or(i -> i.inSql("id", "select id from demo_user").isNull("email"))
                .orderByDesc("create_time");
        log.warn("queryWrapperSql:{}", queryWrapper.getSqlSegment());
        List<User> queryWrapperList = userMapper.selectList(queryWrapper);
        log.warn("queryWrapperList:{}", queryWrapperList);

    }

}
