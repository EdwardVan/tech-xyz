package tech.edwardvan.msspringcloudcommon.entity;

/**
 * 程序响应码
 *
 * @author EdwardVan
 */
public enum ResponseCode {
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    ILLEGAL_ARGUMENT(2, "参数异常");

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
