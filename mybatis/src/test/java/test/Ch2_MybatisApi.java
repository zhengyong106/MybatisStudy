package test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.models.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Ch2_MybatisApi {
    private static Logger logger = LoggerFactory.getLogger(Ch2_MybatisApi.class);

    @Test
    public void findUserById() throws IOException {
        // 创建Properties对象，然后加载配置文件，最后读取配置文件中的配置值
        InputStream inputStream = Ch2_MybatisApi.class.getClassLoader().getResourceAsStream("datasource.properties");
        Properties properties = new Properties();
        properties.load(inputStream);


        Configuration configuration = new Configuration();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));

        // spring 默认事务工厂
        // new SpringManagedTransactionFactory();
        Environment environment = new Environment("database1", new JdbcTransactionFactory(), dataSource);
        configuration.setEnvironment(environment);
        configuration.setLazyLoadingEnabled(true);
        configuration.addMappers("xyz.wecloud.mybatis.mapper");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 通过 sqlSessionFactory 得到 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过 sqlSession 执行 sql 语句，将返回结果集映射成 Employee 模型对象，并将查询的结果放入到 sqlSession 一级缓存中
        Employee employee = sqlSession.selectOne("selectEmployeeById", 1);
        logger.info("输出结果集[{}]", employee);

        // 释放资源
        inputStream.close();
        sqlSession.close();
    }
}
