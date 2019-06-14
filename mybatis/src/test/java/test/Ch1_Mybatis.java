package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wecloud.mybatis.model.Employee;

import java.io.IOException;
import java.io.InputStream;

public class Ch1_Mybatis {
    private static Logger logger = LoggerFactory.getLogger(Ch1_Mybatis.class);

    @Test
    public void findUserById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // 通过读取 xml 配置文件构建 SessionFactory 对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;

        try{
            // 创建SqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 通过使用限定性标识查找 Statement，并传入 Statement 参数去执行，获取结果集
            Employee employee1 = sqlSession.selectOne("selectEmployeeById", 1);
            Employee employee2 = sqlSession.selectOne("selectEmployeeById", 1);
            // 由于两次执行查找都使用同一 Statement 和参数因此实际上第二次返回的为 Mybatis 一级缓存数据
            logger.info("cache hit: {}",employee2 == employee1);

            // 关闭并重新创建新的SqlSession
            sqlSession.close();
            sqlSession = sqlSessionFactory.openSession();

            // 再次使用同一 Statement 和参数进行查找，结果执行查询返回并非同一个对象
            Employee employee3 = sqlSession.selectOne("selectEmployeeById", 1);
            // 这是因为 Mybatis 一级缓存作用域为当前 Statement 的 SqlSession
            logger.info("cache hit: {}",employee3 == employee1);

            // 手动清空缓存（包括一级和二级）,不单可以手动清空缓存，当SqlSession被关闭缓存也会被清空（二级缓存不会清空），并且执行完增删改操作后 Statement 映射文件中的全部一二级缓存都会被清除
            sqlSession.clearCache();

            // 结果返回非同一对象
            Employee employee4 = sqlSession.selectOne("selectEmployeeById", 1);
            logger.info("cache hit: {}",employee4 == employee3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            inputStream.close();
            sqlSession.close();
        }
    }
}
