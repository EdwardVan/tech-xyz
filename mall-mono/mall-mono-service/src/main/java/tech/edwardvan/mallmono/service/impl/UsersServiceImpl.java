package tech.edwardvan.mallmono.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tech.edwardvan.mallmono.common.enums.Sex;
import tech.edwardvan.mallmono.pojo.Users;
import tech.edwardvan.mallmono.mapper.UsersMapper;
import tech.edwardvan.mallmono.pojo.bo.UserLogin;
import tech.edwardvan.mallmono.pojo.bo.UserRegist;
import tech.edwardvan.mallmono.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Override
    public boolean queryUsernameIsExist(String username) {
        LambdaQueryWrapper<Users> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Users::getUsername, username);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users createUser(UserRegist userRegist) {
        Users users = new Users()
                .setUsername(userRegist.getUsername())
                .setPassword(DigestUtils.md5DigestAsHex(userRegist.getPassword().getBytes()))
                .setNickname(userRegist.getUsername())
                .setFace("/")
                .setSex(Sex.secret.value);
        baseMapper.insert(users);
        return users;
    }

    @Override
    public Users queryUserForLogin(UserLogin userLogin) {
        LambdaQueryWrapper<Users> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Users::getUsername, userLogin.getUsername()).eq(Users::getPassword, DigestUtils.md5DigestAsHex(userLogin.getPassword().getBytes()));
        Users users = baseMapper.selectOne(wrapper);
        return users;
    }

}
