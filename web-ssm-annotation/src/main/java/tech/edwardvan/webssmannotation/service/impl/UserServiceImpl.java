package tech.edwardvan.webssmannotation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.edwardvan.webssmannotation.dao.UserMapper;
import tech.edwardvan.webssmannotation.model.User;
import tech.edwardvan.webssmannotation.service.IUserService;

import java.util.Date;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void testTransactional() {
        User user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("test1");
        user1.setRole(1);
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("test2");
        user2.setRole(1);
        user2.setCreateTime(new Date());
        user2.setUpdateTime(new Date());
        userMapper.insert(user1);
        int i = 1/0;
        userMapper.insert(user2);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
