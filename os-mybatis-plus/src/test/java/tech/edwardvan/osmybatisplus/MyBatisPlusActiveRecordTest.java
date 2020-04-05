package tech.edwardvan.osmybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.edwardvan.osmybatisplus.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyBatisPlusActiveRecordTest {

    /**
     * 插入(字段选择插入)
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("test")
                .setPassword("123456")
                .setEmail("test@qq.com")
                .setRole(1)
                .insertOrUpdate();
        log.warn("user.id:{}", user.getId());
    }

    /**
     * 通过id查询
     */
    @Test
    public void selectById() {
        User user = new User().setId(19).selectById();
        log.warn("user:{}", user);
    }

    /**
     * 通过id更新
     */
    @Test
    public void updateById() {
        boolean b = new User().setId(1).setEmail("ed@qq.com").updateById();
        log.warn("b:{}", b);
    }

    /**
     * 根据主键删除
     */
    @Test
    public void deleteById() {
        boolean b = new User().setId(14).deleteById();
        log.warn("b:{}", b);
    }
}
