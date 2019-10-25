package tech.edwardvan.rbacmypermission.service;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.rbacmypermission.dao.SysDeptMapper;
import tech.edwardvan.rbacmypermission.dto.DeptTreeDto;
import tech.edwardvan.rbacmypermission.exception.ParamException;
import tech.edwardvan.rbacmypermission.model.SysDept;
import tech.edwardvan.rbacmypermission.param.DeptParam;
import tech.edwardvan.rbacmypermission.util.ValidatorUtil;

import java.util.Date;
import java.util.List;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public List<DeptTreeDto> getDeptTree(Integer parentId) {
        return sysDeptMapper.getTreeByParentId(parentId);
    }

    public void save(DeptParam param) {
        //校验参数
        ValidatorUtil.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        //拼装对象
        SysDept sysDept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        sysDept.setOperateIp("ip");
        sysDept.setOperator("admin");
        sysDept.setOperateTime(new Date());
        //插入数据库
        sysDeptMapper.insertSelective(sysDept);
    }

    public void update(DeptParam param) {
        //校验参数
        ValidatorUtil.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");

        SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setOperateIp("ip");
        after.setOperator("admin");
        after.setOperateTime(new Date());

        //更新数据库
        sysDeptMapper.updateByPrimaryKeySelective(after);
    }

    public void delete(int deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        Preconditions.checkNotNull(dept, "待删除的部门不存在，无法删除");
        //判断是否存在子部门
        if (sysDeptMapper.countByParentId(deptId) > 0) {
            throw new ParamException("当前部门下面有子部门，无法删除");
        }
        //判断是否存在用户
        //TODO
        sysDeptMapper.deleteByPrimaryKey(deptId);
    }

    /**
     * 检查部门名称是否已经存在
     *
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    private boolean checkExist(Integer parentId, String name, Integer id) {
        return sysDeptMapper.countByNameAndParentIdAndDeptId(parentId, name, id) > 0;
    }
}
