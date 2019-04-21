package tech.edwardvan.webspringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.webspringboot.dao.UserMapper;
import tech.edwardvan.webspringboot.model.User;
import tech.edwardvan.webspringboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
