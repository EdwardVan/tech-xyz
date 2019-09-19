package tech.edwardvan.springclouduserserver.dao;


import tech.edwardvan.springcloudusercommon.entity.User;

public interface UserMapper {

    int insert(User user);

    User selectByPrimaryKey(Integer id);

    Long updateByPrimaryKeySelective(User user);

    Integer deleteByPrimaryKey(Integer id);
}