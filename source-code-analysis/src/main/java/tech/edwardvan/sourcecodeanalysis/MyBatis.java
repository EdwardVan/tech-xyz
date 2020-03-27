package tech.edwardvan.sourcecodeanalysis;


import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.InterceptorChain;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionHolder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
     * Mapper代理类方法执行:{@link MapperProxy#invoke(Object, Method, Object[])}
     * 执行简单的方法执行器:{@link MapperProxy.PlainMethodInvoker#invoke(Object, Method, Object[], SqlSession)}
     * 封装方法参数:{@link ParamNameResolver#getNamedParams(Object[])}
     * SqlSession代理类方法执行:{@link SqlSessionTemplate.SqlSessionInterceptor#invoke(Object, Method, Object[])}
     * <p>
     * [获取SqlSession]-入口:{@link SqlSessionUtils#getSqlSession(SqlSessionFactory, ExecutorType, PersistenceExceptionTranslator)}
     * 尝试从Holder中获取SqlSession:{@link SqlSessionUtils#sessionHolder(ExecutorType, SqlSessionHolder)}
     * [创建SqlSession]-入口:{@link DefaultSqlSessionFactory#openSessionFromDataSource(ExecutorType, TransactionIsolationLevel, boolean)}
     * 获取事务工厂:{@link DefaultSqlSessionFactory#getTransactionFactoryFromEnvironment(Environment)}
     * 创建事务:{@link TransactionFactory#newTransaction(DataSource, TransactionIsolationLevel, boolean)}
     * [创建Executor]-入口:{@link Configuration#newExecutor(Transaction, ExecutorType)}
     * 真正创建Executor对象:{@link SimpleExecutor#SimpleExecutor(Configuration, Transaction)}
     * 使用二级缓存包装Executor:{@link CachingExecutor#CachingExecutor(Executor)}
     * 使用拦截器封装Executor:{@link InterceptorChain#pluginAll(Object)}
     * [创建Executor]-出口
     * 真正创建SqlSession对象:{@link DefaultSqlSession#DefaultSqlSession(Configuration, Executor, boolean)}
     * [创建SqlSession]-出口
     * 注册到SessionHolder:{@link SqlSessionUtils#registerSessionHolder(SqlSessionFactory, ExecutorType, PersistenceExceptionTranslator, SqlSession)}
     * [获取SqlSession]-出口
     * <p>
     * [执行查询]-入口:{@link DefaultSqlSession#selectList(String, Object, RowBounds)}
     * 获取MappedStatement:{@link Configuration#getMappedStatement(String)}
     * 包装集合参数:{@link DefaultSqlSession#wrapCollection(Object)}
     * [二级缓存包装的Executor执行]-入口:{@link CachingExecutor#query(MappedStatement, Object, RowBounds, ResultHandler)}
     * 获取BoundSql:{@link MappedStatement#getBoundSql(Object)}
     * 获取缓存key:{@link CachingExecutor#createCacheKey(MappedStatement, Object, RowBounds, BoundSql)}
     * 尝试从二级缓存中获取结果:{@link TransactionalCacheManager#getObject(Cache, CacheKey)}
     * [真正的Executor执行]-入口:{@link BaseExecutor#query(MappedStatement, Object, RowBounds, ResultHandler, CacheKey, BoundSql)}
     * 尝试从一级缓存中获取结果:{@link PerpetualCache#getObject(Object)}
     * [开始查询]-入口:{@link SimpleExecutor#doQuery(MappedStatement, Object, RowBounds, ResultHandler, BoundSql)}
     * 创建StatementHandler:{@link Configuration#newStatementHandler(Executor, MappedStatement, Object, RowBounds, ResultHandler, BoundSql)}
     * 创建ParameterHandler:{@link Configuration#newParameterHandler(MappedStatement, Object, BoundSql)}
     * 创建ResultSetHandler:{@link Configuration#newResultSetHandler(Executor, MappedStatement, RowBounds, ParameterHandler, ResultHandler, BoundSql)}
     * 上面三个对象都被拦截器封装:{@link InterceptorChain#pluginAll(Object)}
     * 获取Connection:{@link BaseExecutor#getConnection(Log)}
     * 获取Statement:{@link RoutingStatementHandler#prepare(Connection, Integer)}
     * 设置Statement执行时需要的参数:{@link DefaultParameterHandler#setParameters(PreparedStatement)}
     * TypeHandler执行:{@link BaseTypeHandler#setParameter(PreparedStatement, int, Object, JdbcType)}
     * 真正执行数据库查询操作:{@link PreparedStatement#execute()}
     * 处理返回结果:{@link DefaultResultSetHandler#handleResultSets(Statement)}
     * [开始查询]-出口
     * 将结果保存到一级缓存中:{@link PerpetualCache#putObject(Object, Object)}
     * [真正的Executor执行]-出口
     * 将结果保存到二级缓存中:{@link TransactionalCacheManager#putObject(Cache, CacheKey, Object)}
     * [二级缓存包装的Executor执行]-出口
     * [执行查询]-出口
     */
    void 调用Mapper接口执行过程();
}
