package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.mapper.EmployeeMapper;
import xyz.wecloud.mybatis.mapper.EmployeeMapperAnnotation;
import xyz.wecloud.mybatis.model.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Ch5_MybatisMapperAnnotation {
    private static Logger logger = LoggerFactory.getLogger(Ch5_MybatisMapperAnnotation.class);
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
    public void testSelect1() {
        // 获取 SqlSession
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 通过将 Mapper 接口与 Mybatis 映射文件进行绑定，Mybatis 会自动为 Mapper 接口创建代理类
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = employeeMapper.selectEmployeeById(1);
            logger.info("输出映射对象[{}]", employee);
        } finally {
            // 释放资源
            sqlSession.close();
        }
    }

    @Test
    public void testSelect2() {
        // 获取 SqlSession
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 通过将 Mapper 接口与 Mybatis 映射文件进行绑定，Mybatis 会自动为 Mapper 接口创建代理类
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            List<Employee> employees = employeeMapper.selectEmployeesByIds(Arrays.asList(1, 2, 3));
            logger.info("输出映射对象[{}]", employees);
        } finally {
            // 释放资源
            sqlSession.close();
        }
    }

    @Test
    public void testSelect3() {
        // 获取 SqlSession
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 通过将 Mapper 接口与 Mybatis 映射文件进行绑定，Mybatis 会自动为 Mapper 接口创建代理类
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee example = new Employee();
            example.setEmpName("老李");
            List<Employee> employees = employeeMapper.selectEmployeeByExample(example);
            logger.info("输出映射对象[{}]", employees);
        } finally {
            // 释放资源
            sqlSession.close();
        }
    }

    @Test
    public void testSelect4() {
        // 获取 SqlSession
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 通过将 Mapper 接口与 Mybatis 映射文件进行绑定，Mybatis 会自动为 Mapper 接口创建代理类
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = employeeMapper.selectEmployeeCustomById(1);
            logger.info("请等待3秒后将自动激活延迟加载");
            Thread.sleep(3000);
            logger.info("输出映射对象[{}]", employee);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            sqlSession.close();
        }
    }
}
