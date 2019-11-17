package tech.edwardvan.rbacmypermission.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.edwardvan.rbacmypermission.common.RequestHolder;
import tech.edwardvan.rbacmypermission.dao.SysRoleUserMapper;
import tech.edwardvan.rbacmypermission.dao.SysUserMapper;
import tech.edwardvan.rbacmypermission.model.SysRoleAcl;
import tech.edwardvan.rbacmypermission.model.SysRoleUser;
import tech.edwardvan.rbacmypermission.model.SysUser;
import tech.edwardvan.rbacmypermission.util.IpUtil;

import java.util.*;

@Service
public class SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    public Map usersByRoleId(Integer roleId) {
        List<SysUser> selectedUserList = Lists.newArrayList();
        List<SysUser> unselectedUserList = Lists.newLinkedList();

        //获取该角色已经拥有用户id
        List<Integer> userIdsByRoleId = sysRoleUserMapper.getUserIdsByRoleId(roleId);
        //获取所有的用户
        List<SysUser> allUserList = sysUserMapper.getAll();

        for (SysUser sysUser : allUserList) {
            if (sysUser.getStatus() == 1 && !userIdsByRoleId.contains(sysUser.getId())) {
                unselectedUserList.add(sysUser);
            } else {
                selectedUserList.add(sysUser);
            }
        }
        HashMap<String, List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected", unselectedUserList);
        return map;
    }

    @Transactional
    public void changeRoleUsers(int roleId, List<Integer> userIdList) {
        //获取当前角色拥有的权限
        List<Integer> originUserIds = sysRoleUserMapper.getUserIdsByRoleId(roleId);
        //判断权限列表是否未作修改
        if (originUserIds.size() == userIdList.size()) {
            Set<Integer> originUserIdSet = Sets.newHashSet(originUserIds);
            Set<Integer> aclIdSet = Sets.newHashSet(userIdList);
            originUserIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originUserIdSet)) {
                return;
            }
        }
        //开始删除
        sysRoleUserMapper.deleteByRoleId(roleId);
        //判断是否为空
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        //开始插入
        List<SysRoleUser> roleUserList = Lists.newArrayList();
        for (Integer userId : userIdList) {
            SysRoleUser roleUser = SysRoleUser.builder().roleId(roleId).userId(userId).operator(RequestHolder.getCurrentUser().getUsername())
                    .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())).operateTime(new Date()).build();
            roleUserList.add(roleUser);
        }
        sysRoleUserMapper.batchInsert(roleUserList);
    }
}
