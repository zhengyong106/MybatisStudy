package xyz.wecloud.mybatis.spring.xml.mappers;

import xyz.wecloud.mybatis.spring.xml.models.Department;

public interface DepartmentMapper {
    Department selectDepartmentById(Integer id);
    void insertDepartment(Department department);
}
