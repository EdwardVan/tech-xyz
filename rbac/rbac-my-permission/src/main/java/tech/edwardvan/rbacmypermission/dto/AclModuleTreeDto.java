package tech.edwardvan.rbacmypermission.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import tech.edwardvan.rbacmypermission.model.SysAclModule;

import java.util.List;

/**
 * 权限模块树
 */
@Data
public class AclModuleTreeDto extends SysAclModule {

    List<AclModuleTreeDto> children = Lists.newArrayList();
    /**
     * 该权限模块下的权限列表
     */
    List<AclDto> aclList = Lists.newArrayList();
}
