package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.daos.EmployeeDao;
import xyz.wecloud.mybatis.daos.EmployeeDaoImpl;
import xyz.wecloud.mybatis.models.Employee;

import java.io.InputStream;

public class Ch3_MybatisDao {
    private static Logger logger = LoggerFactory.getLogger(Ch3_MybatisDao.class);
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
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
    public void getDepartmentByEmployeeId() {
        EmployeeDao employeeDao = new EmployeeDaoImpl(sqlSessionFactory);
        Employee employee = employeeDao.selectEmployeeById(1);
        logger.info("输出映射对象[{}]",employee);
    }
}
