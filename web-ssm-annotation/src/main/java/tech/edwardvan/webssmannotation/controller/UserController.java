package tech.edwardvan.webssmannotation.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webssmannotation.model.StudentTest;
import tech.edwardvan.webssmannotation.model.User;
import tech.edwardvan.webssmannotation.service.IUserService;

@Api(value="用户模块",tags="用户模块接口")
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private StudentTest studentTest;

    @ApiOperation(value = "获取用户信息",notes = "测试Restful接口")
    @ApiImplicitParam(paramType = "path",name = "userId", value = "用户id", required = true, dataType = "int")
    @RequestMapping(value = "/info/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public User getInformation(@PathVariable(value = "userId")Integer id){
        return userService.getUserById(id);
    }

    @ApiOperation(value = "测试事务",notes = "测试事务")
    @GetMapping("/testTransactional")
    public void testTransactional(){
        userService.testTransactional();
    }

    @ApiOperation(value = "测试AOP",notes = "测试AOP")
    @GetMapping("/testAop")
    public void testAop(){
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        System.out.println(studentTest.toString());
    }
}
