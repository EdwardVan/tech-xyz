package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.Users;
import tech.edwardvan.mallmono.mapper.UsersMapper;
import tech.edwardvan.mallmono.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
