package test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.models.Employee;

import java.io.IOException;
import java.io.InputStream;

public class Ch1_Mybatis {
    private static Logger logger = LoggerFactory.getLogger(Ch1_Mybatis.class);

    @Test
    public void findUserById() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Ch1_Mybatis.class.getClassLoader().getResourceAsStream(resource);
        // 通过读取配置文件获取 sessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过 sqlSessionFactory 得到 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过 sqlSession 执行 sql 语句，将返回结果集映射成 Employee 模型对象，并将查询的结果放入到 sqlSession 一级缓存中
        Employee employee1 = sqlSession.selectOne("selectEmployeeById", 1);
        Employee employee2 = sqlSession.selectOne("selectEmployeeById", 1);
        // 第二次执行相同的查询会直接从缓存中获取查询结果，此时查询返回结果与第一次查询返回的是同一个对象
        logger.info("employee2 == employee1: {}",employee2 == employee1);

        // 一直缓存作用域为 sqlSession ，因此不同的 sqlSession 即使执行相同的查询也不会返回同一个对象
        sqlSession = sqlSessionFactory.openSession();
        Employee employee3 = sqlSession.selectOne("selectEmployeeById", 1);
        logger.info("employee3 == employee1: {}",employee3 == employee1);

        // 即使同一个 sqlSession 执行的查询也相同 ，但是如果两次查询中间清空了缓存（手动或者查询前后执行了增删改并执行了提交操作都会清空缓存（清空全部缓存包含二级））。此时返回对象也不会相同
        sqlSession.clearCache();
        Employee employee4 = sqlSession.selectOne("selectEmployeeById", 1);
        logger.info("employee4 == employee3: {}",employee4 == employee3);

        // 释放资源
        inputStream.close();
        sqlSession.close();
    }
}
