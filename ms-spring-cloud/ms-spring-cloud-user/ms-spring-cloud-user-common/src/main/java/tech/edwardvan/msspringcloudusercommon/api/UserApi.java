package tech.edwardvan.msspringcloudusercommon.api;

import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringcloudusercommon.pojo.UserSaveVo;
import tech.edwardvan.msspringcloudusercommon.pojo.UserUpdateVo;

/**
 * @author EdwardVan
 */
public interface UserApi {

    ResponseResult<User> getUser(Integer userId);

    ResponseResult addUser(UserSaveVo userSaveVo);

    ResponseResult updateUser(UserUpdateVo userUpdateVo);

    ResponseResult deleteUser(Integer userId);

    ResponseResult testUserTx();
}
