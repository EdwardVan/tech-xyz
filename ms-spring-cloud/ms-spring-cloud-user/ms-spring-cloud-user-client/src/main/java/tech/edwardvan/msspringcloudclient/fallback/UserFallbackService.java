package tech.edwardvan.msspringcloudclient.fallback;

import org.springframework.stereotype.Component;
import tech.edwardvan.msspringcloudclient.client.UserClient;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringcloudusercommon.pojo.UserSaveVo;
import tech.edwardvan.msspringcloudusercommon.pojo.UserUpdateVo;

/**
 * @author EdwardVan
 */
@Component
public class UserFallbackService implements UserClient {
    @Override
    public ResponseResult<User> getUser(Integer userId) {
        return ResponseResult.ERROR("获取用户失败");
    }

    @Override
    public ResponseResult addUser(UserSaveVo userSaveVo) {
        return ResponseResult.ERROR("添加用户失败");
    }

    @Override
    public ResponseResult updateUser(UserUpdateVo userUpdateVo) {
        return ResponseResult.ERROR("更新用户失败");
    }

    @Override
    public ResponseResult deleteUser(Integer userId) {
        return ResponseResult.ERROR("删除用户失败");
    }
}
