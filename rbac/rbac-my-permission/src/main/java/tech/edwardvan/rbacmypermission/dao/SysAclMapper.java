package tech.edwardvan.rbacmypermission.dao;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.rbacmypermission.common.PageQuery;
import tech.edwardvan.rbacmypermission.model.SysAcl;

import java.util.List;

public interface SysAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    int countByNameAndAclModuleIdAndAclId(@Param("aclModuleId") int aclModuleId, @Param("name") String name, @Param("id") Integer id);

    int countByAclModuleId(int aclModuleId);

    List<SysAcl> getPageByAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("page") PageQuery page);

    List<SysAcl> getAclDtosByAclModuleId(@Param("aclModuleId") int aclModuleId);
}