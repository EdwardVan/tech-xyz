package tech.edwardvan.rbacmypermission.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SysRole {
    private Integer id;

    private String name;
    /**
     * 角色的类型
     * 1:管理员角色
     * 2:其他
     */
    private Integer type;

    /**
     * 状态
     * 1:可用
     * 0:冻结
     */
    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}