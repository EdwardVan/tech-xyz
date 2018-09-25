package tech.edwardvan.webdubboconsumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.edwardvan.webdubboapi.model.User;
import tech.edwardvan.webdubboapi.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/info/{userId}")
    @ResponseBody
    public User getInformation(@PathVariable(value = "userId")Integer id){
        return userService.getUserById(id);
    }

}
