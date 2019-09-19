package tech.edwardvan.springclouduserserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.springcloudusercommon.entity.User;
import tech.edwardvan.springcloudusercommon.pojo.ResponseCode;
import tech.edwardvan.springcloudusercommon.pojo.ServerResponse;
import tech.edwardvan.springclouduserserver.service.IUserService;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{userId}")
    public ServerResponse getUser(@PathVariable(value = "userId") Integer id) {
        User userById = userService.getUserById(id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), userById);
    }

    @PostMapping
    public ServerResponse insertUser(@RequestBody User user) {
        int i = userService.addUser(user);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), i);
    }

    @PutMapping
    public ServerResponse updateUser(@RequestBody User user) {
        userService.updateUserSelective(user);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    @DeleteMapping(value = "/{userId}")
    public ServerResponse deleteUser(@PathVariable(value = "userId") Integer id) {
        userService.deleteUserById(id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }
}
