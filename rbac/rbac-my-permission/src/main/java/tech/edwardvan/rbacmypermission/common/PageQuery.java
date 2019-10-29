package tech.edwardvan.rbacmypermission.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

public class PageQuery {

    @Getter
    @Setter
    @Min(value = 1, message = "当前页码不合法")
    private int pageNumber = 1;

    @Getter
    @Setter
    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;

    @Setter
    private int offset;

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }
}
