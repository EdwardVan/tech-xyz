package tech.edwardvan.webssmxml.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webssmxml.common.ResponseCode;
import tech.edwardvan.webssmxml.common.ServerResponse;
import tech.edwardvan.webssmxml.dao.UserMapper;
import tech.edwardvan.webssmxml.model.Student;
import tech.edwardvan.webssmxml.model.User;
import tech.edwardvan.webssmxml.service.IUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户模块
 *
 * @author EdwardVan
 */
@Api(value = "用户模块", tags = "用户模块接口")
@RestController
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

    /**
        SpringMVC注解
        1.@RequestMapping,路径映射
            value:指定请求路径
                1.普通的具体值.例如:value = "/user"
                2.含某变量的一类值,使用@PathVariable注解提取路径中的变量值.例如:value = "/info/{userId}"
                3.或关系.例如:value={"/info","/info2"}
            method:用于约束请求的谓词类型.指定请求谓词的类型如GET,POST,HEAD,OPTIONS,PUT,PATCH,DELETE,TRACE,仅有POST、PUT以及PATCH会包含请求体
            params:指定请求中必须有特定参数与值
            headers:指定请求中必须有特定header值
            consumes:指定处理请求的提交内容类型(Content-Type),如果类型不一致则会报415错误,例如：application/json,text/html
            produces:指定返回的内容类型(Accept),如果类型不一致则会报406错误
        2.@RequestParam,获取请求参数
            value:即url 路径中参数的名字
            required:是否必须,默认为true,表示请求中一定要有相应的参数,否者会抛出异常
            defaultValue:默认值,表示若请求中没有同名参数时的默认值,设置该参数时,自动将required设为false
        3.@PathVariable,绑定URL 模板变量值
        4.@RequestHeader,可以把Request请求header部分的值绑定到方法的参数上
        5.@CookieValue,可以把Request header中关于cookie的值绑定到方法的参数上
        6.@SessionAttributes,有选择地指定ModelMap中的哪些属性需要转存到session中
        7.@RequestBody,作用在形参列表上,表示将HTTP 请求正文出入到方法中,使用适合HTTPMessageConverter 将请求体写入某个对象
        8.@ResponsBody,表示将该方法的返回结果直接写入到HTTP response body 中
     */

    /**
        Swagger注释
        1.@Api,用于controller类上,具体类的描述
        2.@ApiOperation,用在controller的方法上,具体方法的描述
        3.@ApiModelProperty,用在出入参数对象的字段上,对象参数的描述
        4.@ApiResponses,用在controller的方法上,
        5.@ApiResponse,用在@ApiResponses里边
        6.@ApiImplicitParams,用在controller的方法上,非对象参数集的描述
        7.@ApiImplicitParam,用在@ApiImplicitParams的方法里边
            paramType,查询参数类型
                query:直接跟参数完成自动映射赋值
                path:以地址的形式提交数据
                body:以流的形式提交,仅支持POST
                header:参数在request headers里边提交
                form:以form表单的形式提交,仅支持POST
            dataType,参数的数据类型,只作为标志说明,并没有实际验证
            name:接收参数名
            value:接收参数的意义描述
            required:参数是否必填
            defaultValue:默认值
        8.@ApiModel,用在返回对象类上,返回对象的描述
     */

    @ApiOperation(value = "获取用户")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true)
    @GetMapping(value = "/{userId}")
    public ServerResponse getUser(@PathVariable(value = "userId") Integer id) {
        User userById = userService.getUserById(id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), userById);
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true)
    @DeleteMapping(value = "/{userId}")
    public ServerResponse deleteUser(@PathVariable(value = "userId") Integer id) {
        userService.deleteUserById(id);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户", required = true)
    @PostMapping
    public ServerResponse addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户", required = true)
    @PutMapping
    public ServerResponse updateUser(@RequestBody User user) {
        userService.updateUserSelective(user);
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }
}
