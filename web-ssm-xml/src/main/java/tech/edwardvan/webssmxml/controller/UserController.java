package tech.edwardvan.webssmxml.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webssmxml.common.ResponseCode;
import tech.edwardvan.webssmxml.common.ServerResponse;
import tech.edwardvan.webssmxml.model.Student;
import tech.edwardvan.webssmxml.model.User;
import tech.edwardvan.webssmxml.service.IUserService;

/**
 * 用户模块
 *
 * @author EdwardVan
 */
@Api(value = "用户模块", tags = "用户模块接口")
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    @Qualifier("student")
    private Student student;

    @Autowired
    @Qualifier("student2")
    private Student student2;

    @Autowired
    @Qualifier("student3")
    private Student student3;

    @ApiOperation(value = "获取用户信息", notes = "测试Restful接口")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "int")
    @RequestMapping(value = "/info/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getInformation(@PathVariable(value = "userId") Integer id) {
        User userById = userService.getUserById(id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), userById);
    }

}
