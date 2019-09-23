package tech.edwardvan.springclouduserserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.springcloudusercommon.entity.User;
import tech.edwardvan.springclouduserserver.service.IUserService;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable(value = "userId") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Integer insertUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUserSelective(user);
    }

    @DeleteMapping(value = "/{userId}")
    public Integer deleteUser(@PathVariable(value = "userId") Integer id) {
        return userService.deleteUserById(id);
    }
}
