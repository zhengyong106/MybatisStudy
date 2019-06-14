package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.mapper.EmployeeMapper;
import xyz.wecloud.mybatis.model.EmployeeCustom;

import java.io.IOException;
import java.io.InputStream;

public class Ch6_MybatisLazyLoading {
    private static Logger logger = LoggerFactory.getLogger(Ch6_MybatisLazyLoading.class);
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // 通过读取 xml 配置文件构建 SessionFactory 对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 释放资源
        inputStream.close();
    }

    @Test
    public void testSelect() throws InterruptedException {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setLazyLoadingEnabled(true);

        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过 sqlSession
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 传入参数执行sq查询
        EmployeeCustom employeeCustom = employeeMapper.selectEmployeeCustomById(1);
        // 已获取查询结果集，但是没有立马执行子查询语句
        logger.info("请等待3秒后激活延迟加载");
        Thread.sleep(3000);
        logger.info("输出部门信息：{}", employeeCustom.getDepartment());
        // 释放资源
        sqlSession.close();
    }
}
