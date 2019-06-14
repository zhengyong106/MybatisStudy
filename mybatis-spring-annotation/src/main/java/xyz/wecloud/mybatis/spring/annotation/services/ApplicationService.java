package xyz.wecloud.mybatis.spring.annotation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wecloud.mybatis.spring.annotation.mappers.DepartmentMapper;
import xyz.wecloud.mybatis.spring.annotation.mappers.EmployeeMapper;
import xyz.wecloud.mybatis.spring.annotation.models.Department;
import xyz.wecloud.mybatis.spring.annotation.models.Employee;

@Service
public class ApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 当调用 SQL 方法时（包括由 getMapper() 方法返回的映射器中的方法），SqlSessionTemplate 将会保证使用的 SqlSession 与当前 Spring 的事务相关
     * （Spring 事务内调用任何映射器中中的方法时，映射器只会创建一次 SqlSession，此外，它管理 该SqlSession 的生命周期，包含必要的关闭、提交或回滚操作
     **/
    @Transactional
    public Department getDepartmentByEmployeeId(Integer id){
        Employee employee1 = employeeMapper.selectEmployeeById(id);
        Employee employee2 = employeeMapper.selectEmployeeById(id);
        // 通过观察两次数据库访问是否获取为同一对象，从而判断 Mybatis 一级缓存是否生效
        logger.info("cache hit: {}", employee1 == employee2);
        return departmentMapper.selectDepartmentById(employee1.getDepId());
    }

    @Transactional
    public void saveEmployeeAndDepartment(Employee employee, Department department){
        employeeMapper.insertEmployee(employee);
        departmentMapper.insertDepartment(department);
        int i = 1 / 0;
    }
}
