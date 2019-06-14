package xyz.wecloud.mybatis.spring.annotation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Department getDepartmentByEmployeeId(Integer id){
        Employee employee1 = employeeMapper.selectEmployeeById(id);
        Employee employee2 = employeeMapper.selectEmployeeById(id);
        // 通过观察两次数据库访问是否获取为同一对象，从而判断 Mybatis 一级缓存是否生效
        logger.info("cache hit: {}", employee1 == employee2);
        return departmentMapper.selectDepartmentById(employee1.getDepId());
    }

    public void saveEmployeeAndDepartment(Employee employee, Department department){
        employeeMapper.insertEmployee(employee);
        departmentMapper.insertDepartment(department);
        int i = 1 / 0;
    }
}
