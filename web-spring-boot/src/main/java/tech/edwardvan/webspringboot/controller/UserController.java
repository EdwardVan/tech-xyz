package tech.edwardvan.webspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webspringboot.common.ResponseCode;
import tech.edwardvan.webspringboot.common.ServerResponse;
import tech.edwardvan.webspringboot.model.User;
import tech.edwardvan.webspringboot.service.IUserService;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @DeleteMapping(value = "/{userId}")
    public ServerResponse deleteUser(@PathVariable(value = "userId") Integer id) {
        userService.deleteUserById(id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    @RabbitListener(queues = {"testQueue"})
    public void receiveMessage(Message message) {
        System.out.println(message);
    }
}
