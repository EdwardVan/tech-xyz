package tech.edwardvan.mallmono.common.enums;

/**
 * 商品评价等级
 *
 * @author EdwardVan
 */
public enum CommentLevel {
    /**
     * 好评
     */
    GOOD(1, "好评"),
    /**
     * 中评
     */
    NORMAL(2, "中评"),
    /**
     * 差评
     */
    BAD(3, "差评");

    public final Integer value;

    public final String desc;

    CommentLevel(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
