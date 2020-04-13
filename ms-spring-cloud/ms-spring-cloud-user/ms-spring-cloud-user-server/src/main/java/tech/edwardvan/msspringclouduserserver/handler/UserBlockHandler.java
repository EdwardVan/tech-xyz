package tech.edwardvan.msspringclouduserserver.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;

/**
 * @author EdwardVan
 */
@Slf4j
public class UserBlockHandler {
    public static ResponseResult getUser(Integer userId, BlockException exception) {
        return ResponseResult.ERROR(exception.getMessage());
    }
}
