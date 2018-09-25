package tech.edwardvan.webssmxml.dao;


import tech.edwardvan.webssmxml.model.User;

import java.util.List;

public interface UserMapper {


    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

}