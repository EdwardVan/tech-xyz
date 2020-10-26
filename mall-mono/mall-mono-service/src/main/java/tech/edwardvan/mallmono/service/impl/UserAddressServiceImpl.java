package tech.edwardvan.mallmono.service.impl;

import tech.edwardvan.mallmono.pojo.UserAddress;
import tech.edwardvan.mallmono.mapper.UserAddressMapper;
import tech.edwardvan.mallmono.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户地址表  服务实现类
 * </p>
 *
 * @author EdwardVan
 * @since 2020-10-23
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

}
