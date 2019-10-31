package tech.edwardvan.rbacmypermission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.edwardvan.rbacmypermission.common.ServerResponse;
import tech.edwardvan.rbacmypermission.param.AclModuleParam;
import tech.edwardvan.rbacmypermission.service.SysAclModuleService;

@Api(value = "权限模块", tags = "权限模块接口")
@Controller
@RequestMapping("/sys/aclModule")
@Slf4j
public class SysAclModuleController {

    @Autowired
    private SysAclModuleService sysAclModuleService;

    @RequestMapping("/acl.page")
    public ModelAndView page() {
        return new ModelAndView("acl");
    }

    @ApiOperation(value = "保存权限模块")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "权限模块id"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "权限模块名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级权限模块id", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "seq", value = "排序", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "权限模块状态", required = true),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/save.json")
    @ResponseBody
    public ServerResponse saveAclModule(AclModuleParam param) {
        sysAclModuleService.save(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "更新权限模块")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "权限模块id", required = true),
            @ApiImplicitParam(paramType = "query", name = "name", value = "权限模块名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级权限模块id", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "seq", value = "排序", required = true),
            @ApiImplicitParam(paramType = "query", name = "status", value = "权限模块状态", required = true),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/update.json")
    @ResponseBody
    public ServerResponse updateAclModule(AclModuleParam param) {
        sysAclModuleService.update(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "删除权限模块")
    @ApiImplicitParam(paramType = "query", name = "id", value = "权限模块id", required = true)
    @GetMapping("/delete.json")
    @ResponseBody
    public ServerResponse delete(@RequestParam("id") int id) {
        sysAclModuleService.delete(id);
        return ServerResponse.success();
    }


    @ApiOperation(value = "获取权限模块树(不包含权限列表)")
    @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级权限模块id", defaultValue = "0")
    @GetMapping("tree.json")
    @ResponseBody
    public ServerResponse tree(@RequestParam(name = "parentId", defaultValue = "0") Integer parentId) {
        return ServerResponse.success(sysAclModuleService.getAclModuleTree(parentId));
    }

}
