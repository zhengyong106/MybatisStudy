package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.dao.EmployeeDao;
import xyz.wecloud.mybatis.dao.EmployeeDaoImpl;
import xyz.wecloud.mybatis.model.Employee;

import java.io.InputStream;

public class Ch3_MybatisDao {
    private static Logger logger = LoggerFactory.getLogger(Ch3_MybatisDao.class);
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // 通过读取 xml 配置文件构建 SessionFactory 对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 释放资源
        inputStream.close();
    }

    @Test
    public void testSelect() {
        // 通过封装一层 DAO 可以简化代码量和提高程序的可移植性
        EmployeeDao employeeDao = new EmployeeDaoImpl(sqlSessionFactory);
        Employee employee = employeeDao.selectEmployeeById(1);
        logger.info("输出映射对象[{}]",employee);
    }
}
