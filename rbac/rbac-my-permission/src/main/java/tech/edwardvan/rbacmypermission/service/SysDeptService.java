package tech.edwardvan.rbacmypermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.rbacmypermission.dao.SysDeptMapper;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;



}
