package tech.edwardvan.mallmono.common.enums;

/**
 * 性别 枚举
 *
 * @author EdwardVan
 */
public enum Sex {
    /**
     * 女
     */
    woman(0, "女"),
    /**
     * 男
     */
    man(1, "男"),
    /**
     * 保密
     */
    secret(2, "保密");

    public final Integer value;

    public final String desc;

    Sex(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
