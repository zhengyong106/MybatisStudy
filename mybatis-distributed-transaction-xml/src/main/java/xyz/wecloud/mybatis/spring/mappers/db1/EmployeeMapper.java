package xyz.wecloud.mybatis.spring.mappers.db1;

import xyz.wecloud.mybatis.spring.models.Employee;

public interface EmployeeMapper {
    Employee selectEmployeeById(Integer id);
    void insertEmployee(Employee employee);
}
