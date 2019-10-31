package tech.edwardvan.rbacmypermission.dao;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.rbacmypermission.model.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int countByNameAndRoleId(@Param("name") String name, @Param("id") Integer id);

    List<SysRole> selectAll();
}