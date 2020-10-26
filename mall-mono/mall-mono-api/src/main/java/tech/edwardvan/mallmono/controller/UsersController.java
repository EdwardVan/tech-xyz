package tech.edwardvan.mallmono.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import tech.edwardvan.mallmono.pojo.Users;
import tech.edwardvan.mallmono.service.IUsersService;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @GetMapping("getByID")
    public Object getUser(String id){
        Users byId = usersService.getById(id);
        return byId;
    }
}

