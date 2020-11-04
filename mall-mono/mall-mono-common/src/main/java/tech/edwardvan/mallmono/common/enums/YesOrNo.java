package tech.edwardvan.mallmono.common.enums;

/**
 * 是否 枚举
 *
 * @author EdwardVan
 */
public enum YesOrNo {
    /**
     * 否
     */
    NO(0, "否"),
    /**
     * 是
     */
    YES(1, "是");

    public final Integer value;

    public final String desc;

    YesOrNo(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
