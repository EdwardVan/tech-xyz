package tech.edwardvan.rbacmypermission.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.rbacmypermission.common.ServerResponse;
import tech.edwardvan.rbacmypermission.param.UserParam;
import tech.edwardvan.rbacmypermission.service.SysDeptService;
import tech.edwardvan.rbacmypermission.service.SysUserService;

import java.util.List;

/**
 * 用户Controller
 *
 * @author EdwardVan
 */
@Api(value = "用户模块", tags = "用户模块接口")
@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "保存用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id"),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "telephone", value = "电话", required = true),
            @ApiImplicitParam(paramType = "query", name = "mail", value = "邮箱", required = true),
            @ApiImplicitParam(paramType = "query", name = "deptId", value = "部门id", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "用户状态", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/save.json")
    @ResponseBody
    public ServerResponse saveDept(UserParam param) {
        sysUserService.save(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "telephone", value = "电话", required = true),
            @ApiImplicitParam(paramType = "query", name = "mail", value = "邮箱", required = true),
            @ApiImplicitParam(paramType = "query", name = "deptId", value = "部门id", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "用户状态", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/update.json")
    @ResponseBody
    public ServerResponse updateDept(UserParam param) {
        sysUserService.update(param);
        return ServerResponse.success();
    }

}
