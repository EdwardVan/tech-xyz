package tech.edwardvan.msspringcloudcommon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.edwardvan.msspringcloudcommon.exception.CustomException;
import tech.edwardvan.msspringcloudcommon.exception.IllegalArgumentException;

import java.io.Serializable;

/**
 * 程序响应对象
 *
 * @author EdwardVan
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    public ResponseResult(int status) {
        this.status = status;
    }

    public ResponseResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //使之不在json序列化结果当中
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 处理调用远程接口的返回
     */
    public T handler() {
        if (ResponseCode.SUCCESS.getCode() != status) {
            exceptionHandler(status, msg);
        }
        return data;
    }

    /**
     * 处理异常
     */
    private void exceptionHandler(int status, String msg) {
        if (status == ResponseCode.ILLEGAL_ARGUMENT.getCode()) {
            throw new IllegalArgumentException(status, msg);
        } else {
            throw new CustomException(status, msg);
        }
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    public static ResponseResult SUCCESS(String msg, Object data) {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static ResponseResult SUCCESS(Object data) {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static ResponseResult ERROR() {
        return new ResponseResult(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static ResponseResult ERROR(String msg) {
        return new ResponseResult(ResponseCode.ERROR.getCode(), msg);
    }

    public static ResponseResult ERROR(int status, String msg) {
        return new ResponseResult(status, msg);
    }

    public static ResponseResult ERROR(String msg, Object data) {
        return new ResponseResult(ResponseCode.ERROR.getCode(), msg, data);
    }

    public static ResponseResult ERROR(Object data) {
        return new ResponseResult(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc(), data);
    }

    public static ResponseResult ERROR(int status, String msg, Object data) {
        return new ResponseResult(status, msg, data);
    }

}
