package tech.edwardvan.rbacmypermission.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("/save.json")
    @ResponseBody
    public ServerResponse saveDept(DeptParam param) {
        sysDeptService.save(param);
        return ServerResponse.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public ServerResponse updateDept(DeptParam param) {
        sysDeptService.update(param);
        return ServerResponse.success();
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public ServerResponse delete(@RequestParam("id") int id) {
        sysDeptService.delete(id);
        return ServerResponse.success();
    }

    @RequestMapping("tree.json")
    @ResponseBody
    public ServerResponse tree(@RequestParam(name = "parentId", defaultValue = "0") Integer parentId) {
        List<DeptTreeDto> deptTree = sysDeptService.getDeptTree(parentId);
        return ServerResponse.success(deptTree);
    }

}
