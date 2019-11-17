package tech.edwardvan.rbacmypermission.dao;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.rbacmypermission.model.SysRoleUser;

import java.util.List;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);

    List<Integer> getUserIdsByRoleId(Integer roleId);

    int deleteByRoleId(int roleId);

    void batchInsert(@Param("roleUserList") List<SysRoleUser> roleUserList);
}