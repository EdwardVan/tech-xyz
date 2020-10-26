package tech.edwardvan.mallmono.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 *
 * @author EdwardVan
 */
@Configuration
@MapperScan("tech.edwardvan.mallmono.mapper")
public class MybatisPlusConfig {

    /**
     * 自动填充处理器
     */
    @Bean
    MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {

            @Override
            public void insertFill(MetaObject metaObject) {
                //this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                //this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }

}
