package tech.edwardvan.rbacmypermission.service;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.edwardvan.rbacmypermission.common.PageQuery;
import tech.edwardvan.rbacmypermission.common.PageResult;
import tech.edwardvan.rbacmypermission.common.RequestHolder;
import tech.edwardvan.rbacmypermission.dao.SysAclMapper;
import tech.edwardvan.rbacmypermission.dao.SysUserMapper;
import tech.edwardvan.rbacmypermission.exception.ParamException;
import tech.edwardvan.rbacmypermission.model.SysAcl;
import tech.edwardvan.rbacmypermission.model.SysUser;
import tech.edwardvan.rbacmypermission.param.UserParam;
import tech.edwardvan.rbacmypermission.util.IpUtil;
import tech.edwardvan.rbacmypermission.util.MD5Util;
import tech.edwardvan.rbacmypermission.util.ValidatorUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysAclMapper sysAclMapper;


    public void save(UserParam param) {
        //校验参数
        ValidatorUtil.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }

        String password = "12345678";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator(RequestHolder.getCurrentUser().getUsername());
        user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        user.setOperateTime(new Date());

        sysUserMapper.insertSelective(user);
    }

    public void update(UserParam param) {
        ValidatorUtil.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");

        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
    }


    /**
     * 检查邮箱是否已经存在
     */
    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    /**
     * 检查电话是否已经存在
     */
    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    /**
     * 通过keyword获取用户
     */
    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

    /**
     * 获取用户信息
     *
     * @param deptId    部门id
     * @param pageQuery 分页对象
     * @return
     */
    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery) {
        ValidatorUtil.check(pageQuery);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            List<SysUser> sysUserList = sysUserMapper.getPageByDeptId(deptId, pageQuery);
            return PageResult.<SysUser>builder().total(count).data(sysUserList).build();
        }
        return PageResult.<SysUser>builder().build();
    }

    /**
     * 是否拥有指定url的访问权限
     */
    public boolean hasUrlAcl(String url) {
        SysUser currentUser = RequestHolder.getCurrentUser();
        //判断是否为管理员
        if (isAdmin()) {
            return true;
        }
        //判断是否存在和url有关的权限
        List<SysAcl> aclList = sysAclMapper.getByUrl(url);
        if (CollectionUtils.isEmpty(aclList)) {
            return true;
        }
        //获取当前用户所拥有的权限
        List<SysAcl> currentUserAclList = getCurrentUserAclList();
        Set<Integer> currentUserAclIdSet = currentUserAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());

        //定义是否拥有有效的acl
        boolean hasValidAcl = false;

        for (SysAcl sysAcl : aclList) {
            if (sysAcl.getStatus() != 1) {
                continue;
            }
            hasValidAcl = true;
            if (currentUserAclIdSet.contains(sysAcl.getId())) {
                return true;
            }
        }
        //是否全部为无效的acl
        if (!hasValidAcl) {
            return true;
        }

        return false;
    }

    /**
     * 当前登录用户是否为超级管理员
     */
    public boolean isAdmin() {
        //TODO
        return false;
    }

    /**
     * 获取当前用户所拥有的权限
     */
    public List<SysAcl> getCurrentUserAclList() {
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    /**
     * 通过用户id获取用户所拥有的权限
     */
    public List<SysAcl> getUserAclList(int userId) {
        //TODO
        return null;
    }
}
