package tech.edwardvan.rbacmypermission.service;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.edwardvan.rbacmypermission.common.RequestHolder;
import tech.edwardvan.rbacmypermission.dao.SysAclMapper;
import tech.edwardvan.rbacmypermission.dao.SysAclModuleMapper;
import tech.edwardvan.rbacmypermission.dto.AclModuleTreeDto;
import tech.edwardvan.rbacmypermission.exception.ParamException;
import tech.edwardvan.rbacmypermission.model.SysAclModule;
import tech.edwardvan.rbacmypermission.param.AclModuleParam;
import tech.edwardvan.rbacmypermission.util.IpUtil;
import tech.edwardvan.rbacmypermission.util.ValidatorUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 权限模块Service
 *
 * @author EdwardVan
 */
@Service
public class SysAclModuleService {

    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    @Autowired
    private SysAclMapper sysAclMapper;

    public void save(AclModuleParam param) {
        ValidatorUtil.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule aclModule = SysAclModule.builder().name(param.getName()).parentId(param.getParentId()).seq(param.getSeq())
                .status(param.getStatus()).remark(param.getRemark()).build();
        aclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
        aclModule.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        aclModule.setOperateTime(new Date());
        sysAclModuleMapper.insertSelective(aclModule);
    }

    public void update(AclModuleParam param) {
        ValidatorUtil.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule before = sysAclModuleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的权限模块不存在");

        SysAclModule after = SysAclModule.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId()).seq(param.getSeq())
                .status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());

        //更新数据库
        sysAclModuleMapper.updateByPrimaryKeySelective(after);
    }


    private boolean checkExist(Integer parentId, String aclModuleName, Integer aclModuleId) {
        return sysAclModuleMapper.countByNameAndParentIdAndAclModuleId(parentId, aclModuleName, aclModuleId) > 0;
    }

    public void delete(int aclModuleId) {
        SysAclModule aclModule = sysAclModuleMapper.selectByPrimaryKey(aclModuleId);
        Preconditions.checkNotNull(aclModule, "待删除的权限模块不存在，无法删除");
        if (sysAclModuleMapper.countByParentId(aclModule.getId()) > 0) {
            throw new ParamException("当前模块下面有子模块，无法删除");
        }
        //todo
//        if (sysAclMapper.countByAclModuleId(aclModule.getId()) > 0) {
//            throw new ParamException("当前模块下面有用户，无法删除");
//        }
        sysAclModuleMapper.deleteByPrimaryKey(aclModuleId);
    }

    public List<AclModuleTreeDto> getAclModuleTree(Integer parentId) {
        return sysAclModuleMapper.getTreeByParentId(parentId);
    }
}
