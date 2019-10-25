package tech.edwardvan.rbacmypermission.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import tech.edwardvan.rbacmypermission.model.SysDept;

import java.util.List;

/**
 * 部门树节点
 * @author EdwardVan
 */
@Data
public class DeptTreeDto extends SysDept {

    List<DeptTreeDto> children = Lists.newArrayList();
}