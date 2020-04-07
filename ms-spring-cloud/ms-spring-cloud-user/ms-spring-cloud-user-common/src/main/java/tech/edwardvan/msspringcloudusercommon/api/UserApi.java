package tech.edwardvan.msspringcloudusercommon.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringcloudusercommon.pojo.UserSaveVo;
import tech.edwardvan.msspringcloudusercommon.pojo.UserUpdateVo;

/**
 * @author EdwardVan
 */
@RequestMapping("/user")
public interface UserApi {

    @GetMapping(value = "/{userId}")
    ResponseResult<User> getUser(@PathVariable(value = "userId") Integer userId);

    @PostMapping
    ResponseResult addUser(@RequestBody @Validated UserSaveVo userSaveVo);

    @PutMapping
    ResponseResult updateUser(@RequestBody @Validated UserUpdateVo userUpdateVo);

    @DeleteMapping(value = "/{userId}")
    ResponseResult deleteUser(@PathVariable(value = "userId") Integer userId);
}
