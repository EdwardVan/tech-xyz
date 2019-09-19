package tech.edwardvan.springclouduserserver.service;


import tech.edwardvan.springcloudusercommon.entity.User;

public interface IUserService {

    User getUserById(Integer id);

    int addUser(User user);

    User updateUserSelective(User user);

    Integer deleteUserById(Integer id);

}
