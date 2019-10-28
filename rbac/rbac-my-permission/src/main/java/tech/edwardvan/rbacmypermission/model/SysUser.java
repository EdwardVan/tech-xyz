package tech.edwardvan.rbacmypermission.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SysUser {
    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String password;

    private Integer deptId;

    /**
     * 状态
     * 1:正常,0:冻结状态,2:删除
     */
    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;

}