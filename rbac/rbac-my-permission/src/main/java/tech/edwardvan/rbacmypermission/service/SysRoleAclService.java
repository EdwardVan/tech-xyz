package tech.edwardvan.rbacmypermission.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.edwardvan.rbacmypermission.common.RequestHolder;
import tech.edwardvan.rbacmypermission.dao.SysAclModuleMapper;
import tech.edwardvan.rbacmypermission.dao.SysRoleAclMapper;
import tech.edwardvan.rbacmypermission.dto.AclDto;
import tech.edwardvan.rbacmypermission.dto.AclModuleTreeDto;
import tech.edwardvan.rbacmypermission.model.SysRoleAcl;
import tech.edwardvan.rbacmypermission.util.IpUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleAclService {

    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    public List<AclModuleTreeDto> aclModuleTreeByRoleId(Integer roleId) {

        //获取包含权限的权限模块树
        List<AclModuleTreeDto> tree = sysAclModuleMapper.getTreeIncludeAclByParentId(0);
        //获取当前角色拥有的权限
        List<Integer> aclIds = sysRoleAclMapper.getAclIdsByRoleId(roleId);

        handleAclModuleTree(tree, aclIds);

        return tree;
    }

    /**
     * 递归处理权限模块树中AclDto中的checked值
     *
     * @param tree   权限模块树
     * @param aclIds 当前角色拥有的权限
     */
    private void handleAclModuleTree(List<AclModuleTreeDto> tree, List<Integer> aclIds) {
        if (CollectionUtils.isNotEmpty(tree) && CollectionUtils.isNotEmpty(aclIds)) {
            for (AclModuleTreeDto aclModuleTreeDto : tree) {
                //获取该权限模块下的子权限模块
                List<AclModuleTreeDto> children = aclModuleTreeDto.getChildren();
                handleAclModuleTree(children, aclIds);
                //获取该权限节点下的权限
                List<AclDto> aclList = aclModuleTreeDto.getAclList();
                if (CollectionUtils.isNotEmpty(aclList)) {
                    for (AclDto aclDto : aclList) {
                        Integer aclId = aclDto.getId();
                        if (aclIds.contains(aclId)) {
                            aclDto.setChecked(true);
                        }
                    }
                }
            }
        }
    }

    @Transactional
    public void changeRoleAcls(int roleId, List<Integer> aclIdList) {
        //获取当前角色拥有的权限
        List<Integer> originAclIds = sysRoleAclMapper.getAclIdsByRoleId(roleId);
        //判断权限列表是否未作修改
        if (originAclIds.size() == aclIdList.size()) {
            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIds);
            Set<Integer> aclIdSet = Sets.newHashSet(aclIdList);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return;
            }
        }
        //开始删除
        sysRoleAclMapper.deleteByRoleId(roleId);
        //判断是否为空
        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        //开始插入
        List<SysRoleAcl> roleAclList = Lists.newArrayList();
        for (Integer aclId : aclIdList) {
            SysRoleAcl roleAcl = SysRoleAcl.builder().roleId(roleId).aclId(aclId).operator(RequestHolder.getCurrentUser().getUsername())
                    .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())).operateTime(new Date()).build();
            roleAclList.add(roleAcl);
        }
        sysRoleAclMapper.batchInsert(roleAclList);
    }
}
