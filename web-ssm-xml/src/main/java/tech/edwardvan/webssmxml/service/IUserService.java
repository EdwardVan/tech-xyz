package tech.edwardvan.webssmxml.service;


import tech.edwardvan.webssmxml.model.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Service接口
 *
 * @author EdwardVan
 */
public interface IUserService {

    int addUser(User user);

    int addUserList(List<User> users);

    Integer deleteUserById(Integer id);

    Long updateUser(User user);

    Long updateUserSelective(User user);

    User getUserById(Integer id);

    User getUserAndCartsByNestResult(Integer userId);

    User getUserAndCartsByNestSelect(Integer userId);

    Map<String, Object> getUserMapById(Integer id);

    Map<Integer, User> getMapByParameterMap(Map parameterMap);

    List<User> getListByIds(List<Integer> ids);

    List<User> getListByUsernameLike(String usernameLike);

    List<User> getUserByDatabaseAndUsernameAndPassword(String database, String username, String password);

}
