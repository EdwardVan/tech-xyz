package tech.edwardvan.webspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webspringboot.common.ResponseCode;
import tech.edwardvan.webspringboot.common.ServerResponse;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/{userId}")
    public ServerResponse getUser(@PathVariable(value = "userId") Integer id) {
        logger.debug("id:",id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

}
