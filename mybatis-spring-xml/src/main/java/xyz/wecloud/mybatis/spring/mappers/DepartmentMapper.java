package xyz.wecloud.mybatis.spring.mappers;

import xyz.wecloud.mybatis.spring.models.Department;

public interface DepartmentMapper {
    Department selectDepartmentById(Integer id);
    void insertDepartment(Department department);
}
