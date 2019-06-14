package test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.model.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Ch2_MybatisApi {
    private static Logger logger = LoggerFactory.getLogger(Ch2_MybatisApi.class);

    @Test
    public void findUserById() throws IOException {
        // 读取配置文件，并创建 Properties 对象
        InputStream inputStream = Resources.getResourceAsStream("datasource.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        // 创建 DataSource 数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));

        // 创建 Environment 运行环境
        Environment environment = new Environment("defaultEnvironment", new JdbcTransactionFactory(), dataSource);

        // 创建 Configuration 配置
        Configuration configuration = new Configuration();
        configuration.setEnvironment(environment);
        configuration.setLazyLoadingEnabled(true);
        configuration.addMappers("xyz.wecloud.mybatis.mapper");

        // 通过读取 configuration 配置构建 SessionFactory 对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 获取 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行 Statement
        Employee employee = sqlSession.selectOne("selectEmployeeById", 1);
        logger.info("输出结果集[{}]", employee);

        // 释放资源
        inputStream.close();
        sqlSession.close();
    }
}
