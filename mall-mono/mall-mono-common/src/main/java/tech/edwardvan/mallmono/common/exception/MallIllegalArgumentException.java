package tech.edwardvan.mallmono.common.exception;


import lombok.Getter;
import tech.edwardvan.mallmono.common.enums.ResponseCode;

/**
 * 商城非法参数错误
 *
 * @author EdwardVan
 */
@Getter
public class MallIllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private int code;
    /**
     * 异常描述
     */
    private String desc;

    public MallIllegalArgumentException() {
        super();
    }

    public MallIllegalArgumentException(String desc) {
        super(desc);
        this.code = ResponseCode.ILLEGAL_ARGUMENT.getCode();
        this.desc = desc;
    }

    public MallIllegalArgumentException(int code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public MallIllegalArgumentException(int code, String desc, Throwable throwable) {
        super(desc, throwable);
        this.code = code;
        this.desc = desc;
    }

    public MallIllegalArgumentException(ResponseCode responseCode) {
        super(responseCode.getDesc());
        this.code = responseCode.getCode();
        this.desc = responseCode.getDesc();
    }

    public MallIllegalArgumentException(ResponseCode responseCode, Throwable throwable) {
        super(responseCode.getDesc(), throwable);
        this.code = responseCode.getCode();
        this.desc = responseCode.getDesc();
    }
}