package tech.edwardvan.rbacspringsecuritydemo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户model类
 *
 * @author EdwardVan
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private Date createTime;

    private Date updateTime;
}