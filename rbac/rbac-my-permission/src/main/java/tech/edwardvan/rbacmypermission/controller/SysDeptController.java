package tech.edwardvan.rbacmypermission.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.edwardvan.rbacmypermission.service.SysDeptService;

/**
 * 部门Controller
 *
 * @author EdwardVan
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;


}
