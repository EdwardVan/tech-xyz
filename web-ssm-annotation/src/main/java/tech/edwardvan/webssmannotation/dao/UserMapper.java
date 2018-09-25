package tech.edwardvan.webssmannotation.dao;


import tech.edwardvan.webssmannotation.model.User;

import java.util.List;

public interface UserMapper {


    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

}