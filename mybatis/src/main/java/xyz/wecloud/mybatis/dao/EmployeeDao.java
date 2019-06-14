package xyz.wecloud.mybatis.dao;

import xyz.wecloud.mybatis.model.Employee;

public interface EmployeeDao {
    Employee selectEmployeeById(Integer userId);
}
