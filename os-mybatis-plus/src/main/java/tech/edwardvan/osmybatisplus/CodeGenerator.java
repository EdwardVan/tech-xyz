package tech.edwardvan.osmybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器
 * <p>
 * 配置属性参考
 * https://mybatis.plus/config/generator-config.html
 *
 * @author EdwardVan
 */
public class CodeGenerator {

    /**
     * 数据库基本信息
     */
    private static String DATA_SOURCE_URL = "jdbc:mysql://127.0.0.1:3306/demo_mall?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static String DATA_SOURCE_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String DATA_SOURCE_USERNAME = "root";
    private static String DATA_SOURCE_PASSWORD = "123456";
    private static DbType DB_TYPE = DbType.MYSQL;

    /**
     * 生成代码所在的项目绝对地址
     */
    private static String PROJECT_PATH = "D:\\Project\\tech-xyz\\os-mybatis-plus";

    /**
     * 包名称
     */
    private static String PARENT_PACKAGE = "tech.edwardvan.osmybatisplus";

    /**
     * 表名前缀
     */
    private static String TABLE_PREFIX = "demo_";
    /**
     * 数据库表名
     */
    private static String[] TABLES = {"demo_user"};

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        /**
         * 数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATA_SOURCE_URL);
        dsc.setDbType(DB_TYPE);
        dsc.setDriverName(DATA_SOURCE_DRIVER_NAME);
        dsc.setUsername(DATA_SOURCE_USERNAME);
        dsc.setPassword(DATA_SOURCE_PASSWORD);
        mpg.setDataSource(dsc);

        /**
         * 数据库表配置
         */
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //表名前缀
        strategy.setTablePrefix(TABLE_PREFIX);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLES);
        mpg.setStrategy(strategy);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE);
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        /**
         * 全局策略
         */
        GlobalConfig gc = new GlobalConfig();
        //生成代码路径
        gc.setOutputDir(PROJECT_PATH + "\\src\\main\\java");
        //多次生成,文件是否覆盖
        gc.setFileOverride(true);
        //是否开启ActiveRecord模式
        gc.setActiveRecord(true);
        //作者
        gc.setAuthor("EdwardVan");
        //主键策略
        gc.setIdType(IdType.AUTO);
        //是否开启swagger2模式
//        gc.setSwagger2(true);
        //是否生成基本的ResultMap
        gc.setBaseResultMap(true);
        //是否生成基本的字段sql
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        mpg.execute();
    }
}
