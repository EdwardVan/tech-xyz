package tech.edwardvan.osmybatisplus;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.edwardvan.osmybatisplus.entity.User;
import tech.edwardvan.osmybatisplus.service.IUserService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyBatisPlusServiceTest {

    @Autowired
    IUserService userService;

    /**
     * 查询操作
     */
    @Test
    public void getOne() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getRole, 1), false);
        log.warn("user:{}", user);
    }

    @Test
    public void list() {
        List<User> list = userService.lambdaQuery().eq(User::getRole, 1).list();
        log.warn("list:{}", list);
    }

    @Test
    public void update() {
        boolean update = userService.lambdaUpdate().set(User::getEmail, "test@qq.com").eq(User::getId, 1).update();
        log.warn("update:{}", update);
    }
}
