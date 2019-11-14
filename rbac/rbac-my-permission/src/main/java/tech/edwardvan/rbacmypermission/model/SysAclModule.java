package tech.edwardvan.rbacmypermission.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 权限模块module
 *
 * @author EdwardVan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAclModule {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;
    /**
     * 状态
     * 1:正常,0:冻结
     */
    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}