package xyz.wecloud.mybatis.spring.xml.mappers;

import xyz.wecloud.mybatis.spring.xml.models.Employee;

public interface EmployeeMapper {
    Employee selectEmployeeById(Integer id);
    void insertEmployee(Employee employee);
}
