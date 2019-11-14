package tech.edwardvan.rbacmypermission.dto;

import lombok.Data;
import tech.edwardvan.rbacmypermission.model.SysAcl;

/**
 * @author EdwardVan
 */
@Data
public class AclDto extends SysAcl {

    /**
     * 是否要默认选中
     */
    private boolean checked = false;
}
