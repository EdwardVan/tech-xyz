package tech.edwardvan.rbacmypermission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.edwardvan.rbacmypermission.model.SysUser;
import tech.edwardvan.rbacmypermission.service.SysUserService;
import tech.edwardvan.rbacmypermission.util.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author EdwardVan
 */
@Api(value = "登录模块", tags = "登录模块接口")
@Controller
public class LoginController {

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "手机号/邮箱", defaultValue = "admin@qq.com"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", defaultValue = "123456")
    })
    @PostMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ret = request.getParameter("ret");

        SysUser sysUser = sysUserService.findByKeyword(username);

        String errorMsg = "";
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            errorMsg = "用户名或者密码不可以为空";
        } else if (sysUser == null) {
            errorMsg = "查询不到指定的用户";
        } else if (!sysUser.getPassword().equals(MD5Util.encrypt(password))) {
            errorMsg = "用户名或密码错误";
        } else if (sysUser.getStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            request.getSession().setAttribute("user", sysUser);
            if (StringUtils.isNotBlank(ret)) {
                response.sendRedirect(ret);
            } else {
                response.sendRedirect("/admin.page");
            }
            return;
        }

        request.setAttribute("error", errorMsg);
        request.setAttribute("username", username);
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        String path = "login.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        String path = "login.jsp";
        response.sendRedirect(path);
    }

    @RequestMapping("/admin.page")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }
}
