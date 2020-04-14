package tech.edwardvan.msspringclouduserserver.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringclouduserserver.handler.UserBlockHandler;
import tech.edwardvan.msspringcloudusercommon.api.UserApi;
import tech.edwardvan.msspringcloudusercommon.model.User;
import tech.edwardvan.msspringcloudusercommon.pojo.UserSaveVo;
import tech.edwardvan.msspringcloudusercommon.pojo.UserUpdateVo;
import tech.edwardvan.msspringclouduserserver.service.IUserService;

/**
 * @author EdwardVan
 * @since 2020-04-05
 */
@Api(value = "用户模块", tags = "用户模块接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController implements UserApi {

    @Autowired
    IUserService userService;

    @Override
    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "int")
    @SentinelResource(value = "getUser", blockHandlerClass = UserBlockHandler.class, blockHandler = "getUser",
            fallbackClass = UserBlockHandler.class, fallback = "getUser")
    public ResponseResult<User> getUser(@PathVariable(value = "userId") Integer userId) {
        //测试降级
        if (userId == 0) {
            throw new RuntimeException("测试降级");
        }
        return ResponseResult.SUCCESS(userService.getById(userId));
    }

    @Override
    @PostMapping
    @ApiOperation(value = "新增用户信息")
    public ResponseResult addUser(@RequestBody @Validated UserSaveVo userSaveVo) {
        User user = new User();
        BeanUtils.copyProperties(userSaveVo, user);
        if (userService.save(user)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.ERROR();
    }

    @Override
    @PutMapping
    @ApiOperation(value = "更新用户信息")
    public ResponseResult updateUser(@RequestBody @Validated UserUpdateVo userUpdateVo) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateVo, user);
        if (userService.updateById(user)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.ERROR();
    }

    @Override
    @DeleteMapping(value = "/{userId}")
    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "int")
    public ResponseResult deleteUser(@PathVariable(value = "userId") Integer userId) {
        if (userService.removeById(userId)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.ERROR();
    }
}

