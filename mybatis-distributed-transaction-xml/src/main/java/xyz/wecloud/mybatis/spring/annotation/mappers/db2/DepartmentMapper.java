package xyz.wecloud.mybatis.spring.annotation.mappers.db2;

import xyz.wecloud.mybatis.spring.annotation.models.Department;

public interface DepartmentMapper {
    Department selectDepartmentById(Integer id);
    void insertDepartment(Department department);
}
