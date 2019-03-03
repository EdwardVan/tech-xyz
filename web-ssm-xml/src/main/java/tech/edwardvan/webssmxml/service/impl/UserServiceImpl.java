package tech.edwardvan.webssmxml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.webssmxml.dao.UserMapper;
import tech.edwardvan.webssmxml.model.User;
import tech.edwardvan.webssmxml.service.IUserService;

import java.util.List;
import java.util.Map;

/**
 * 用户Service实现类
 *
 * @author EdwardVan
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int addUserList(List<User> users) {
        return userMapper.insertUsers(users);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Long updateUserSelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserAndCartsByNestResult(Integer userId) {
        return userMapper.selectUserAndCartsByNestResult(userId);
    }

    @Override
    public User getUserAndCartsByNestSelect(Integer userId) {
        return userMapper.selectUserAndCartsByNestSelect(userId);
    }

    @Override
    public Map<String, Object> getUserMapById(Integer id) {
        return userMapper.selectByPrimaryKeyReturnMap(id);
    }

    @Override
    public Map<String, User> getMapByParameterMap(Map parameterMap) {
        return userMapper.selectByParameterMapReturnMap(parameterMap);
    }

    @Override
    public List<User> getListByIds(List<Integer> ids) {
        return userMapper.selectByPrimaryKeys(ids);
    }

    @Override
    public List<User> getListByUsernameLike(String usernameLike) {
        return userMapper.selectByUsernameLikeReturnList(usernameLike);
    }

    @Override
    public List<User> getUserByDatabaseAndUsernameAndPassword(String database, String username, String password) {
        return userMapper.selectByDatabaseAndUsernameAndPassword(database, username, password);
    }
}
