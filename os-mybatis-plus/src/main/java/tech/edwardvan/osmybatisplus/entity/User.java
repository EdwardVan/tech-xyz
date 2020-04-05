package tech.edwardvan.osmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户
 * <p>
 * 添加逻辑删除字段
 * alter table demo_user add del_flag int(1) default 0
 * 添加多租户字段
 * alter table demo_user add tenant_id varchar(100)
 *
 * @author EdwardVan
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@TableName("demo_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户密码,MD5加密
     */
    private String password;

    private String email;

    private String phone;

    /**
     * 找回密码问题
     */
    private String question;

    /**
     * 找回密码答案
     */
    private String answer;

    /**
     * 角色0-管理员,1-普通用户
     */
    private Integer role;

    /**
     * 备注信息,表中没有对应信息,需要用exist排除
     */
    @TableField(exist = false)
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后一次更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标识
     * 查询后返回数据中不需要携带此字段
     */
    @TableField(select = false)
    private Integer delFlag;

    /**
     * 多租户id
     */
    private String tenantId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
