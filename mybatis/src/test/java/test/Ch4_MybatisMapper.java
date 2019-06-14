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
import xyz.wecloud.mybatis.model.Employee;

import java.io.IOException;
import java.io.InputStream;

public class Ch4_MybatisMapper {
    private static Logger logger = LoggerFactory.getLogger(Ch4_MybatisMapper.class);
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
    public void testSelect() {
        // 获取 SqlSession
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 通过将 Mapper 接口与 Mybatis 映射文件进行绑定，Mybatis 会自动为 Mapper 接口创建代理类
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.selectEmployeeById(1);
            logger.info("输出映射对象[{}]", employee);
        } finally {
            // 释放资源
            sqlSession.close();
        }
    }
}
