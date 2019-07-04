package tech.edwardvan.webspringboot.dao;


import tech.edwardvan.webspringboot.model.User;


public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    Long updateByPrimaryKeySelective(User user);

    Integer deleteByPrimaryKey(Integer id);
}