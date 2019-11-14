package tech.edwardvan.rbacmypermission.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAcl {
    private Integer id;

    private String code;

    private String name;

    private Integer aclModuleId;

    private String url;
    /**
     * 类型
     * 1:菜单,2:按钮,3:其他
     */
    private Integer type;

    /**
     * 状态
     * 1:正常,0:冻结
     */
    private Integer status;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}