package tech.edwardvan.rbacmypermission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.rbacmypermission.common.PageQuery;
import tech.edwardvan.rbacmypermission.common.ServerResponse;
import tech.edwardvan.rbacmypermission.param.AclParam;
import tech.edwardvan.rbacmypermission.service.SysAclService;

/**
 * @author EdwardVan
 */
@Api(value = "权限模块", tags = "权限模块接口")
@Controller
@RequestMapping("/sys/acl")
@Slf4j
public class SysAclController {

    @Autowired
    private SysAclService sysAclService;

    @ApiOperation(value = "保存权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "权限id"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "权限名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "aclModuleId", value = "权限模块id", required = true),
            @ApiImplicitParam(paramType = "query", name = "url", value = "权限点URL"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "权限类型", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "权限状态", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "seq", value = "排序", required = true),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/save.json")
    @ResponseBody
    public ServerResponse saveAcl(AclParam param) {
        sysAclService.save(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "更新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "权限id", required = true),
            @ApiImplicitParam(paramType = "query", name = "name", value = "权限名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "aclModuleId", value = "权限模块id", required = true),
            @ApiImplicitParam(paramType = "query", name = "url", value = "权限点URL"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "权限类型", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "权限状态", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "seq", value = "排序", required = true),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/update.json")
    @ResponseBody
    public ServerResponse updateAcl(AclParam param) {
        sysAclService.update(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "通过部门id获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "aclModuleId", value = "权限模块id", required = true),
            @ApiImplicitParam(paramType = "query", name = "pageNumber", value = "当前页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示条数", defaultValue = "10")
    })
    @GetMapping("/page.json")
    @ResponseBody
    public ServerResponse page(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery pageQuery) {
        return ServerResponse.success(sysAclService.getPageByAclModuleId(aclModuleId, pageQuery));
    }
}
