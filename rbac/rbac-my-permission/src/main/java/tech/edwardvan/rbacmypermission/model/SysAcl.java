package tech.edwardvan.rbacmypermission.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SysAcl {
    private Integer id;

    private String code;

    private String name;

    private Integer aclModuleId;

    private String url;

    private Integer type;

    private Integer status;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}