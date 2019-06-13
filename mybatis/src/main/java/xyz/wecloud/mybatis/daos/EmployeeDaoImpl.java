package xyz.wecloud.mybatis.daos;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import xyz.wecloud.mybatis.models.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
    private SqlSessionFactory sqlSessionFactory;

    public EmployeeDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Employee selectEmployeeById(Integer userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Employee user = sqlSession.selectOne("selectEmployeeById", userId);
        sqlSession.close();
        return user;
    }
}
