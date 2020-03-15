package tech.edwardvan.webspringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webspringboot.common.ResponseCode;
import tech.edwardvan.webspringboot.common.ServerResponse;
import tech.edwardvan.webspringboot.model.User;
import tech.edwardvan.webspringboot.service.IUserService;


/**
 * @author EdwardVan
 */
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

    @PutMapping
    public ServerResponse updateUser(@RequestBody User user) {
        userService.updateUserSelective(user);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    @GetMapping("testAsync")
    public void testAsync() {
        log.info("执行UserController.testAsync开始");
        userService.testAsync();
        log.info("执行UserController.testAsync结束");
    }
}
