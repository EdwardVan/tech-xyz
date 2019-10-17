package tech.edwardvan.rbacmypermission.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class TestController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String getInformation() {
        return "test";
    }


}
