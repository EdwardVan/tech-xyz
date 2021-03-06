package tech.edwardvan.webspringboot.service;


import tech.edwardvan.webspringboot.model.User;

public interface IUserService {

    User getUserById(Integer id);

    User updateUserSelective(User user);

    Integer deleteUserById(Integer id);

    void testAsync();
}
