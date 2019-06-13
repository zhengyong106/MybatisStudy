package xyz.wecloud.mybatis.spring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wecloud.mybatis.spring.mappers.db1.EmployeeMapper;
import xyz.wecloud.mybatis.spring.mappers.db2.DepartmentMapper;
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
     * 由 Spring 管理的 Mapper 映射器实际上都是由 SqlSessionTemplate 所创建（即来自同一个 SqlSession ），而 Mapper 映射器访问 SqlSession 数据方法实际上都不是通过SqlSessionTemplate 而是新创建一个 SqlSession 去调用
     * 因此在 Spring 事务以内调用任何在映射器中方法，Mapper 映射器创建得到的都是同一个 SqlSession，这是因为 Spring 对 Mybatis 事务管理是底层通过 SqlSession 的 Commit 和 Rollback 来完成的
     * 反之在 Spring 事务之外调用任何在映射器中方法，事务都将会自动被提交，而每次调用 Mapper 映射器中的方法所创建的 SqlSession 都是不一样的
     **/
    @Transactional
    public Department getDepartmentByEmployeeId(Integer id){
        Employee employee1 = employeeMapper.selectEmployeeById(id);
        Employee employee2 = employeeMapper.selectEmployeeById(id);
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
