package tech.edwardvan.msspringclouduserserver.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;

/**
 * SentinelResource降级
 *
 * @author EdwardVan
 */
@Slf4j
public class UserBlockHandler {
    /**
     * 处理Sentinel对控制资源的限流处理
     */
    public static ResponseResult getUser(Integer userId, BlockException exception) {
        return ResponseResult.ERROR(exception.getMessage());
    }

    /**
     * 处理目标对象抛出异常后的降级处理
     */
    public static ResponseResult getUser(Integer userId, Throwable throwable) {
        return ResponseResult.ERROR(throwable.getMessage());
    }
}
