package tech.edwardvan.rbacmypermission.dao;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.rbacmypermission.dto.AclModuleTreeDto;
import tech.edwardvan.rbacmypermission.model.SysAclModule;

import java.util.List;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);

    int countByNameAndParentIdAndAclModuleId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

    int countByParentId(Integer id);

    List<AclModuleTreeDto> getTreeByParentId(Integer parentId);

    List<AclModuleTreeDto> getTreeIncludeAclByParentId(Integer parentId);
}