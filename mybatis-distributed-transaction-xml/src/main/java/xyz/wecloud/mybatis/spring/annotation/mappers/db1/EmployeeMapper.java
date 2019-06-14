package xyz.wecloud.mybatis.spring.annotation.mappers.db1;

import xyz.wecloud.mybatis.spring.annotation.models.Employee;

public interface EmployeeMapper {
    Employee selectEmployeeById(Integer id);
    void insertEmployee(Employee employee);
}
