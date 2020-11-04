package tech.edwardvan.mallmono.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tech.edwardvan.mallmono.common.exception.MallIllegalArgumentException;
import tech.edwardvan.mallmono.common.utils.CookieUtils;
import tech.edwardvan.mallmono.common.utils.JsonUtils;
import tech.edwardvan.mallmono.common.utils.ServerResponse;
import tech.edwardvan.mallmono.pojo.Users;
import tech.edwardvan.mallmono.pojo.bo.UserLogin;
import tech.edwardvan.mallmono.pojo.bo.UserRegist;
import tech.edwardvan.mallmono.service.IUsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 注册登录  前端控制器
 * </p>
 *
 * @author EdwardVan
 */
@Api(value = "注册登录", tags = "注册登录")
@RestController
@RequestMapping("passport")
@Validated
public class PassportController {

    @Autowired
    private IUsersService usersService;

    /**
     * 检测用户名是否存在
     */
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在")
    @GetMapping("usernameIsExist")
    public ServerResponse usernameIsExist(@NotBlank(message = "用户名不能为空") String username) {
        //查找用户名是否存在
        if (usersService.queryUsernameIsExist(username)) {
            throw new MallIllegalArgumentException("用户名已经存在");
        }
        return ServerResponse.success();
    }

    /**
     * 用户注册
     *
     * @param userRegist 注册信息
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("regist")
    public ServerResponse regist(HttpServletRequest request, HttpServletResponse response, @Validated @RequestBody UserRegist userRegist) {
        //两次密码输入是否一致
        if (!userRegist.getPassword().equals(userRegist.getConfirmPassword())) {
            throw new MallIllegalArgumentException("两次密码输入不一致");
        }
        //查找用户名是否存在
        if (usersService.queryUsernameIsExist(userRegist.getUsername())) {
            throw new MallIllegalArgumentException("用户名已经存在");
        }
        Users user = usersService.createUser(userRegist);
        user.setPassword(null).setMobile(null).setRealname(null).setCreatedTime(null).setUpdatedTime(null);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return ServerResponse.success(user);
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("login")
    public ServerResponse login(HttpServletRequest request, HttpServletResponse response, @Validated @RequestBody UserLogin userLogin) {
        Users user = usersService.queryUserForLogin(userLogin);
        if (user == null) {
            throw new MallIllegalArgumentException("账号或者密码错误");
        }
        user.setPassword(null).setMobile(null).setRealname(null).setCreatedTime(null).setUpdatedTime(null);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return ServerResponse.success(user);
    }

    /**
     * 用户退出登录
     */
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录")
    @PostMapping("logout")
    public ServerResponse logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "user");
        return ServerResponse.success();
    }
}

