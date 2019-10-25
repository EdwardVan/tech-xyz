package tech.edwardvan.rbacmypermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.rbacmypermission.dao.SysDeptMapper;
import tech.edwardvan.rbacmypermission.dto.DeptTreeDto;

import java.util.List;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public List<DeptTreeDto> getDeptTree(Integer parentId){
        return sysDeptMapper.getTreeByParentId(parentId);
    }

}
