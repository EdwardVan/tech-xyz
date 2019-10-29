package tech.edwardvan.rbacmypermission.dao;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.rbacmypermission.common.PageQuery;
import tech.edwardvan.rbacmypermission.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int countByMail(@Param("mail") String mail, @Param("id") Integer id);

    int countByTelephone(@Param("telephone") String telephone, @Param("id") Integer id);

    SysUser findByKeyword(@Param("keyword") String keyword);

    List<SysUser> getPageByDeptId(@Param("deptId") int deptId, @Param("page") PageQuery page);

    int countByDeptId(@Param("deptId") int deptId);
}