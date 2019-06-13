package xyz.wecloud.mybatis.daos;

import xyz.wecloud.mybatis.models.Employee;

public interface EmployeeDao {
    Employee selectEmployeeById(Integer userId);
}
