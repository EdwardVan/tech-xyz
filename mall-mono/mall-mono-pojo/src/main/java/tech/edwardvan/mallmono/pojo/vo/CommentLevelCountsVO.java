package tech.edwardvan.mallmono.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品评价数量VO
 *
 * @author EdwardVan
 */
@Data
@AllArgsConstructor
public class CommentLevelCountsVO {
    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;
}
