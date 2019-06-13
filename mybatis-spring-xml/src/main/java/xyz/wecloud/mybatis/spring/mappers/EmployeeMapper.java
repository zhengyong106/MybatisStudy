package xyz.wecloud.mybatis.spring.mappers;

import xyz.wecloud.mybatis.spring.models.Employee;

public interface EmployeeMapper {
    Employee selectEmployeeById(Integer id);
    void insertEmployee(Employee employee);
}
