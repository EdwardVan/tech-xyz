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
import tech.edwardvan.rbacmypermission.dto.DeptTreeDto;
import tech.edwardvan.rbacmypermission.param.DeptParam;
import tech.edwardvan.rbacmypermission.service.SysDeptService;

import java.util.List;

/**
 * 部门Controller
 *
 * @author EdwardVan
 */
@Api(value = "部门模块", tags = "部门模块接口")
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;


    @RequestMapping("/dept.page")
    public ModelAndView deptPage() {
        return new ModelAndView("dept");
    }

    @ApiOperation(value = "保存部门")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "部门id"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "部门名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级部门id", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "seq", value = "排序", required = true),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/save.json")
    @ResponseBody
    public ServerResponse saveDept(DeptParam param) {
        sysDeptService.save(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "更新部门")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "部门id", required = true),
            @ApiImplicitParam(paramType = "query", name = "name", value = "部门名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级部门id", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "seq", value = "排序", required = true),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注")
    })
    @PostMapping("/update.json")
    @ResponseBody
    public ServerResponse updateDept(DeptParam param) {
        sysDeptService.update(param);
        return ServerResponse.success();
    }

    @ApiOperation(value = "删除部门")
    @ApiImplicitParam(paramType = "query", name = "id", value = "部门id", required = true)
    @GetMapping("/delete.json")
    @ResponseBody
    public ServerResponse deleteDept(@RequestParam("id") int id) {
        sysDeptService.delete(id);
        return ServerResponse.success();
    }

    @ApiOperation(value = "获取部门树")
    @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级部门id", defaultValue = "0")
    @GetMapping("tree.json")
    @ResponseBody
    public ServerResponse tree(@RequestParam(name = "parentId", defaultValue = "0") Integer parentId) {
        List<DeptTreeDto> deptTree = sysDeptService.getDeptTree(parentId);
        return ServerResponse.success(deptTree);
    }

}
