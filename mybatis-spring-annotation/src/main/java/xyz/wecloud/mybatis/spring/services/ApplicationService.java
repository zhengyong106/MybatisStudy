package xyz.wecloud.mybatis.spring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wecloud.mybatis.spring.mappers.DepartmentMapper;
import xyz.wecloud.mybatis.spring.mappers.EmployeeMapper;
import xyz.wecloud.mybatis.spring.models.Department;
import xyz.wecloud.mybatis.spring.models.Employee;

@Service
public class ApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 由 Spring 管理的 Mapper 映射器实际上都是由 SqlSessionTemplate 所创建（SqlSessionTemplate 是一个特殊的 SqlSession，他继承了 SqlSession 接口），
     * 而 Mapper 映射器访问 SqlSession 数据方法实际上都不是通过 SqlSessionTemplate 而是新创建一个 SqlSession 去调用
     * 但在 Spring 事务内调用任何映射器中的方法时，映射器只会创建一个 SqlSession，这是因为 Mybatis 事务管理是底层通过 SqlSession 的 Commit，Rollback 和 Close 来完成的
     * 因此不在 Spring 事务内 Mybatis 一级缓存将会失效
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
