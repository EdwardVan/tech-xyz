package tech.edwardvan.springcloudusercommon.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Integer id;

    private String username;

    private String email;

    private String phone;

}