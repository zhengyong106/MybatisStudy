package xyz.wecloud.mybatis.mapper;

import xyz.wecloud.mybatis.models.Employee;
import xyz.wecloud.mybatis.models.EmployeeCustom;

public interface EmployeeMapper {
    Employee selectEmployeeById(int id);
    EmployeeCustom selectEmployeeCustomById(int id);
}
