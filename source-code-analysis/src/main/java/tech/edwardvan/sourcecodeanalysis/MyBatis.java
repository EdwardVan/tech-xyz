package tech.edwardvan.sourcecodeanalysis;


import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.InterceptorChain;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import javax.sql.DataSource;
import java.lang.reflect.Method;

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


    /**
     * 调用Mapper接口执行过程
     * <p>
     * 执行Mapper代理方法-入口:{@link MapperProxy.PlainMethodInvoker#invoke(Object, Method, Object[], SqlSession)}
     * 封装方法参数:{@link ParamNameResolver#getNamedParams(Object[])}
     * [获取SqlSession]-入口:{@link SqlSessionUtils#getSqlSession(SqlSessionFactory, ExecutorType, PersistenceExceptionTranslator)}
     * [创建SqlSession]-入口:{@link DefaultSqlSessionFactory#openSessionFromDataSource(ExecutorType, TransactionIsolationLevel, boolean)}
     * 获取事务工厂:{@link DefaultSqlSessionFactory#getTransactionFactoryFromEnvironment(Environment)}
     * 创建事务:{@link TransactionFactory#newTransaction(DataSource, TransactionIsolationLevel, boolean)}
     * [创建Executor]-入口:{@link Configuration#newExecutor(Transaction, ExecutorType)}
     * 创建Executor对象:{@link SimpleExecutor#SimpleExecutor(Configuration, Transaction)}
     * 使用二级缓存包装Executor:{@link CachingExecutor#CachingExecutor(Executor)}
     * 使用拦截器包装Executor:{@link InterceptorChain#pluginAll(Object)}
     * [创建Executor]-出口
     * 创建SqlSession对象:{@link DefaultSqlSession#DefaultSqlSession(Configuration, Executor, boolean)}
     * [创建SqlSession]-出口
     * 注册到SessionHolder:{@link SqlSessionUtils#registerSessionHolder(SqlSessionFactory, ExecutorType, PersistenceExceptionTranslator, SqlSession)}
     * [获取SqlSession]-出口
     */
    void 调用Mapper接口执行过程();
}
