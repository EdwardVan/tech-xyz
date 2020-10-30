package tech.edwardvan.mallmono.common.enums;

/**
 * 程序响应码
 *
 * @author EdwardVan
 */
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, "请求成功"),
    /**
     *
     */
    ERROR(500, "服务器内部错误"),
    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT(501, "非法参数");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
