package tech.edwardvan.msspringcloudclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.msspringcloudclient.fallback.UserFallbackService;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudusercommon.api.UserApi;
import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringcloudusercommon.pojo.UserSaveVo;
import tech.edwardvan.msspringcloudusercommon.pojo.UserUpdateVo;

/**
 * @author EdwardVan
 */
@FeignClient(name = "user-service", fallback = UserFallbackService.class)
public interface UserClient extends UserApi {

    @Override
    @GetMapping("/user/{userId}")
    ResponseResult<User> getUser(@PathVariable(value = "userId") Integer userId);

    @Override
    @PostMapping("/user")
    ResponseResult addUser(@RequestBody @Validated UserSaveVo userSaveVo);

    @Override
    @PutMapping("/user")
    ResponseResult updateUser(@RequestBody @Validated UserUpdateVo userUpdateVo);

    @Override
    @DeleteMapping("/user/{userId}")
    ResponseResult deleteUser(@PathVariable(value = "userId") Integer userId);
}
