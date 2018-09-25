package tech.edwardvan.webdubboprovider.dao;


import tech.edwardvan.webdubboapi.model.User;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

}