package tech.edwardvan.mallmono.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import tech.edwardvan.mallmono.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * 程序响应对象
 *
 * @author EdwardVan
 */
@ApiModel(value="程序统一响应对象", description="程序统一响应对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private Object data;

    public ServerResponse(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> success() {
        return success(ResponseCode.SUCCESS.getDesc());
    }

    public static <T> ServerResponse<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> ServerResponse<T> success(T data) {
        return success(ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> error(int status) {
        return error(status, null, null);
    }

    public static <T> ServerResponse<T> error(String msg) {
        return error(ResponseCode.ERROR.getCode(), msg, null);
    }

    public static <T> ServerResponse<T> error(int status, String msg) {
        return error(status, msg, null);
    }

    public static <T> ServerResponse<T> error(int status, String msg, T data) {
        return new ServerResponse<>(status, msg, data);
    }


}
