package tech.edwardvan.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.springcloud.fallback.UserClientFallback;
import tech.edwardvan.springcloudusercommon.entity.User;

@FeignClient(value = "user-server", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping(value = "/user/{userId}")
    User getUser(@PathVariable(value = "userId") Integer id);

    @PostMapping(value = "/user")
    Integer insertUser(@RequestBody User user);

    @PutMapping(value = "/user")
    void updateUser(@RequestBody User user);

    @DeleteMapping(value = "/user/{userId}")
    Integer deleteUser(@PathVariable(value = "userId") Integer id);
}
