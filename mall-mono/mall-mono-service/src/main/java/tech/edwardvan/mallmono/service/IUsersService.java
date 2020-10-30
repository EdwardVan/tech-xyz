package tech.edwardvan.mallmono.service;

import tech.edwardvan.mallmono.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.edwardvan.mallmono.pojo.bo.UserLogin;
import tech.edwardvan.mallmono.pojo.bo.UserRegist;

/**
 * <p>
 * 用户表  服务类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
public interface IUsersService extends IService<Users> {
    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 用户名是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userRegist 创建用户信息
     * @return 数据库中保存的对象
     */
    Users createUser(UserRegist userRegist);

    /**
     * 查询用户信息-登录
     * @param userLogin
     * @return
     */
    Users queryUserForLogin(UserLogin userLogin);
}
