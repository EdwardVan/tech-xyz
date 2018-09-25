package tech.edwardvan.webdubboprovider.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.webdubboapi.model.User;
import tech.edwardvan.webdubboapi.service.IUserService;
import tech.edwardvan.webdubboprovider.dao.UserMapper;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
