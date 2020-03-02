package tech.edwardvan.webspringboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
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