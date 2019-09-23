package tech.edwardvan.springcloudorderserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.springcloud.client.UserClient;
import tech.edwardvan.springcloudusercommon.entity.User;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    UserClient userClient;

    @GetMapping(value = "/{userId}")
    public User getOrder(@PathVariable(value = "userId") Integer id) {
        User user = userClient.getUser(id);
        return user;
    }

}
