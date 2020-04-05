package tech.edwardvan.osmybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MybatisPlus配置类
 *
 * @author EdwardVan
 */
@Configuration
@MapperScan("tech.edwardvan.osmybatisplus.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作,true调回到首页,false 继续请求(默认false)
        paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量,默认 500 条,-1 不受限制
        paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

        List<ISqlParser> sqlParserList = new ArrayList<>();
        //多租户sql解析器配置
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            /**
             * 通过一定的方法获取租户id
             */
            @Override
            public Expression getTenantId(boolean where) {
                return new StringValue("ombp");
            }
            /**
             * 多租户对应的字段名称
             * @return
             */
            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            /**
             * 过滤表
             * @param tableName 表名
             * @return false为加租户, true为不加租户
             */
            @Override
            public boolean doTableFilter(String tableName) {
                /*
                if ("user".equals(tableName)) {
                    return true;
                }*/
                return false;
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            // 自定义查询此时不添加租户信息的方法
            /*if ("xxxx".equals(ms.getId())) {
                return true;
            }*/
            return false;
        });

        return paginationInterceptor;
    }

    /**
     * 自动填充处理器
     */
    @Bean
    MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {

            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }

}
