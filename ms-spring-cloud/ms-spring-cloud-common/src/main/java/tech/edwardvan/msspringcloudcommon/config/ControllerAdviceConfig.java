package tech.edwardvan.msspringcloudcommon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.msspringcloudcommon.entity.ResponseCode;
import tech.edwardvan.msspringcloudcommon.entity.ResponseResult;
import tech.edwardvan.msspringcloudcommon.exception.CustomException;

/**
 * 全局异常处理器
 *
 * @author EdwardVan
 */
@RestControllerAdvice
@Slf4j
public class ControllerAdviceConfig {

    /**
     * 处理CustomException
     */
    @ExceptionHandler(CustomException.class)
    public ResponseResult handleException(CustomException e) {
        return ResponseResult.ERROR();
    }


    /**
     * 处理IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult handleException(IllegalArgumentException e) {
        return ResponseResult.ERROR(ResponseCode.ILLEGAL_ARGUMENT.getCode(), e.getMessage());
    }

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseResult.ERROR(ResponseCode.ILLEGAL_ARGUMENT.getCode(), e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
