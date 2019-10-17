package tech.edwardvan.rbacmypermission.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库相关配置
 */
@Configuration
@MapperScan("tech.edwardvan.rbacmypermission.dao")
@PropertySource("classpath:jdbc.properties")
public class DataAccessConfig {
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    //数据库连接池配置
    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        druid.setUrl(url);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setDriverClassName(driverClassName);
        //配置初始化大小、最小、最大
        druid.setInitialSize(1);
        druid.setMinIdle(1);
        druid.setMaxActive(20);
        //配置获取连接等待超时的时间
        druid.setMaxWait(60000);
        //配置间隔多久才进行一次检测,检测需要关闭的空闲连接,单位是毫秒
        druid.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间,单位是毫秒 -->
        druid.setMinEvictableIdleTimeMillis(300000);

        druid.setValidationQuery("SELECT 'x'");
        druid.setTestWhileIdle(true);
        druid.setTestOnBorrow(false);
        druid.setTestOnReturn(false);
        //打开PSCache,并且指定每个连接上PSCache的大小
        druid.setPoolPreparedStatements(false);
        druid.setMaxPoolPreparedStatementPerConnectionSize(20);
        //配置监控统计拦截的filters
        druid.setFilters("stat");
        return druid;
    }

    /**
     * 事务核心管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * MyBatis配置
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionBean = new SqlSessionFactoryBean();
        sessionBean.setDataSource(dataSource);
        sessionBean.setTypeAliasesPackage("tech.edwardvan.rbacmypermission.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionBean.setMapperLocations(resolver.getResources("classpath*:mappers/*.xml"));
        SqlSessionFactory sessionFactory = sessionBean.getObject();
        return sessionFactory;
    }
}
