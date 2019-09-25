package tech.edwardvan.springcloud.fallback;

import org.springframework.stereotype.Component;
import tech.edwardvan.springcloud.client.UserClient;
import tech.edwardvan.springcloudusercommon.entity.User;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setUsername("服务Provider无法调用");
        return user;
    }

    @Override
    public Integer insertUser(User user) {
        return 0;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public Integer deleteUser(Integer id) {
        return 0;
    }
}