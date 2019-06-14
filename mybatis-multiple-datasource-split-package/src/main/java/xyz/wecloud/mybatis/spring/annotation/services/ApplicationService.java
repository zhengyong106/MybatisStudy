package xyz.wecloud.mybatis.spring.annotation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wecloud.mybatis.spring.annotation.mappers.db1.EmployeeMapper;
import xyz.wecloud.mybatis.spring.annotation.mappers.db2.DepartmentMapper;
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
     * 可以针对不同事务指定不同的事务管理器管理多数据源
     **/
    @Transactional(transactionManager="transactionManager1")
    public void saveEmployee(Employee employee){
        employeeMapper.insertEmployee(employee);
        int i = 1 / 0;
    }

    @Transactional(transactionManager="transactionManager2")
    public void saveDepartment(Department department){
        departmentMapper.insertDepartment(department);
        int i = 1 / 0;
    }
}
