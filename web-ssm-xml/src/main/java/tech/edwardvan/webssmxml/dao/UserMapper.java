package tech.edwardvan.webssmxml.dao;


import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import tech.edwardvan.webssmxml.model.User;

import java.util.List;
import java.util.Map;

/**
 * 用户dao类
 *
 * @author EdwardVan
 */
public interface UserMapper {

    int insert(User user);

    int insertUsers(@Param("users") List<User> users);

    Integer deleteByPrimaryKey(Integer id);

    Long updateByPrimaryKey(User user);

    Long updateByPrimaryKeySelective(User user);

    User selectByPrimaryKey(Integer id);

    User selectUserAndCartsByNestResult(Integer userId);

    User selectUserAndCartsByNestSelect(Integer userId);

    Map<String, Object> selectByPrimaryKeyReturnMap(Integer id);

    @MapKey("username")
    Map<Integer, User> selectByParameterMapReturnMap(Map parameterMap);

    List<User> selectByUsernameLikeReturnList(String usernameLike);

    List<User> selectByPrimaryKeys(@Param("ids") List<Integer> ids);

    List<User> selectByDatabaseAndUsernameAndPassword(String database, @Param("username") String username, @Param("password") String password);

}