package tech.edwardvan.rbacspringsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author EdwardVan
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping(value = "/{userId}")
    public void getUser(@PathVariable(value = "userId") Integer id) {
        log.info(id.toString());
    }
}
