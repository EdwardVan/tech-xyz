package tech.edwardvan.msspringclouduserserver.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringclouduserserver.mapper.UserMapper;
import tech.edwardvan.msspringclouduserserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @GlobalTransactional
    public int testUserTx() {
        User user = new User();
        user.setUsername("username-tx")
                .setPassword("password-tx").setRole(1)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());
        return baseMapper.insert(user);
    }

}
