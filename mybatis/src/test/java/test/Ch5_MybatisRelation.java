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
import xyz.wecloud.mybatis.model.EmployeeCustom;

import java.io.IOException;
import java.io.InputStream;

public class Ch5_MybatisRelation {
    private static Logger logger = LoggerFactory.getLogger(Ch5_MybatisRelation.class);
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        // 定义mybatis配置文件路径
        String resource = "mybatis.xml";
        // 获取mybatis配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 通过读取配置文件获取sessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 释放资源
        inputStream.close();
    }

    @Test
    public void getUserById() {
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过mapper代理获取DepartmentMapper接口实现对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 传入参数执行sq查询
        EmployeeCustom employeeCustom = employeeMapper.selectEmployeeCustomById(1);
        logger.info("输出映射对象[{}]", employeeCustom);
        // 释放资源
        sqlSession.close();
    }
}
