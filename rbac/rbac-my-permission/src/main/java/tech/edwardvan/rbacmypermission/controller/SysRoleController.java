package tech.edwardvan.rbacmypermission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import tech.edwardvan.rbacmypermission.common.ServerResponse;
import tech.edwardvan.rbacmypermission.param.RoleParam;
import tech.edwardvan.rbacmypermission.service.SysRoleAclService;
import tech.edwardvan.rbacmypermission.service.SysRoleService;
import tech.edwardvan.rbacmypermission.util.StringUtil;

import java.util.List;

@Api(value = "角色模块", tags = "角色模块接口")
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleAclService sysRoleAclService;

    @RequestMapping("role.page")
    public ModelAndView page() {
        return new ModelAndView("role");
    }


    @ApiOperation(value = "保存角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "角色id"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "权限名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "type", value = "权限类型", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "权限状态", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/save.json")
    @ResponseBody
    public ServerResponse saveRole(RoleParam param) {
        sysRoleService.save(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "更新角色")
    @PostMapping("/update.json")
    @ResponseBody
    public ServerResponse updateRole(RoleParam param) {
        sysRoleService.update(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "所有角色列表")
    @GetMapping("/list.json")
    @ResponseBody
    public ServerResponse list() {
        return ServerResponse.success(sysRoleService.getAll());
    }

    @ApiOperation(value = "通过角色id获取权限模块树(包含权限列表)")
    @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色id", required = true)
    @PostMapping("aclModuleTreeByRoleId.json")
    @ResponseBody
    public ServerResponse tree(@RequestParam(name = "roleId") Integer roleId) {
        return ServerResponse.success(sysRoleAclService.aclModuleTreeByRoleId(roleId));
    }

    @ApiOperation(value = "更改角色对应的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色id", required = true),
            @ApiImplicitParam(paramType = "query", name = "aclIds", value = "权限id集合字符串", required = true)
    })
    @PostMapping("/changeAcls.json")
    @ResponseBody
    public ServerResponse changeAcls(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<Integer> aclIdList = StringUtil.splitToListInt(aclIds);
        sysRoleAclService.changeRoleAcls(roleId, aclIdList);
        return ServerResponse.success();
    }

}
