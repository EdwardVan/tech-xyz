package tech.edwardvan.sourcecodeanalysis;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandler;

/**
 * MyBatis源码解析集
 *
 * @author EdwardVan
 */
public interface MyBatis {

    /**
     * MyBatis主要部件
     * <p>
     * {@link SqlSession}:作为MyBatis工作的主要顶层APl,表示和数据库交互的会话,完成必要数据库增删改查功能
     * {@link Executor}:MyBatis执行器,是MyBatis 调度的核心,负责SQL语句的生成和查询缓存的维护
     * {@link StatementHandler}:封装了JDBC Statement操作,负责对JDBC statement的操作,如设置参数、将Statement结果集转换成List集合
     * {@link ParameterHandler}:负责对用户传递的参数转换成JDBC Statement 所需要的参数
     * {@link ResultSetHandler}:负责将JDBC返回的ResultSet结果集对象转换成List类型的集合
     * {@link TypeHandler}:负责java数据类型和jdbc数据类型之间的映射和转换
     * {@link MappedStatement}:维护了一条<select update delete insert>节点的封装
     * {@link SqlSource}:负责根据用户传递的parameterObject,动态地生成SQL语句,将信息封装到BoundSql对象中,并返回
     * {@link BoundSql}:表示动态生成的SQL语句以及相应的参数信息
     * {@link Configuration}:MyBatis所有的配置信息都维持在此对象中
     */
    void MyBatis主要部件();

}
