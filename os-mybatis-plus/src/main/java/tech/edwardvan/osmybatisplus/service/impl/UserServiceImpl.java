package tech.edwardvan.osmybatisplus.service.impl;

import tech.edwardvan.osmybatisplus.entity.User;
import tech.edwardvan.osmybatisplus.mapper.UserMapper;
import tech.edwardvan.osmybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-03-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
