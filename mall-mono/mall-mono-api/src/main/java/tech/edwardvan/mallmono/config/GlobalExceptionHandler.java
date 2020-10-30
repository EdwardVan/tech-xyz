package tech.edwardvan.mallmono.config;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.edwardvan.mallmono.common.enums.ResponseCode;
import tech.edwardvan.mallmono.common.exception.MallIllegalArgumentException;
import tech.edwardvan.mallmono.common.utils.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理类
 *
 * @author EdwardVan
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 参数错误处理-单个参数校验
     * 注意:一定要在方法所在的controller类上加入@Validated注解,不然没有任何效果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ServerResponse constraintViolationExceptionHandler(ConstraintViolationException e) {
        e.printStackTrace();
        return ServerResponse.error(ResponseCode.ILLEGAL_ARGUMENT.getCode(), e.getMessage());
    }

    /**
     * 参数错误处理-实体类参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return ServerResponse.error(ResponseCode.ILLEGAL_ARGUMENT.getCode(), e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 参数错误处理-程序内部通过计算得出参数错误
     */
    @ExceptionHandler(MallIllegalArgumentException.class)
    public ServerResponse mallIllegalArgumentExceptionHandler(MallIllegalArgumentException e) {
        e.printStackTrace();
        return ServerResponse.error(ResponseCode.ILLEGAL_ARGUMENT.getCode(), e.getDesc());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ServerResponse exceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        return ServerResponse.error(ResponseCode.ERROR.getCode());
    }
}
