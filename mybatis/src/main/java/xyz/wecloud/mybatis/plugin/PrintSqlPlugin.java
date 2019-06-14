package xyz.wecloud.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(
        type= StatementHandler.class,
        method = "query",
        args = {Statement.class, ResultHandler.class}
        )})
public class PrintSqlPlugin implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(PrintSqlPlugin.class);

    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        StatementHandler statementHandler = (StatementHandler)target;
        BoundSql boundSql = statementHandler.getBoundSql();

        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("\n==>  Preparing: {}\n==>  UseTime: {}ms", boundSql.getSql(), endTime - startTime);
        }
    }


    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}