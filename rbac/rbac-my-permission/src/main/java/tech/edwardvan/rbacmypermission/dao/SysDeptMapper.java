package tech.edwardvan.rbacmypermission.dao;

import org.apache.ibatis.annotations.Param;
import tech.edwardvan.rbacmypermission.dto.DeptTreeDto;
import tech.edwardvan.rbacmypermission.model.SysDept;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<DeptTreeDto> getTreeByParentId(Integer parentId);

    int countByNameAndParentIdAndDeptId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

    int countByParentId(int parentId);
}