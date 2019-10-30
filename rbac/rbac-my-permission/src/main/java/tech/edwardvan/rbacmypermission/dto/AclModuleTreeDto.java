package tech.edwardvan.rbacmypermission.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import tech.edwardvan.rbacmypermission.model.SysAclModule;

import java.util.List;

@Data
public class AclModuleTreeDto extends SysAclModule {

    List<AclModuleTreeDto> children = Lists.newArrayList();
}
