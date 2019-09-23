package tech.edwardvan.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.springcloudusercommon.entity.User;

@FeignClient(value = "user-server")
@RequestMapping("/user")
public interface UserClient {
    @GetMapping(value = "/{userId}")
    User getUser(@PathVariable(value = "userId") Integer id);

    @PostMapping
    Integer insertUser(@RequestBody User user);

    @PutMapping
    void updateUser(@RequestBody User user);

    @DeleteMapping(value = "/{userId}")
    Integer deleteUser(@PathVariable(value = "userId") Integer id);
}
