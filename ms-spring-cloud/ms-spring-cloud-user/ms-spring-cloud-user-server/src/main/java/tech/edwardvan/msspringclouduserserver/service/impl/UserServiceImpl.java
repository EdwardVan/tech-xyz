package tech.edwardvan.msspringclouduserserver.service.impl;

import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringclouduserserver.mapper.UserMapper;
import tech.edwardvan.msspringclouduserserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
