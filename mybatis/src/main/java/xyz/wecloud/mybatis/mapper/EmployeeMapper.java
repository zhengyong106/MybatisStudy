package xyz.wecloud.mybatis.mapper;

import xyz.wecloud.mybatis.models.Employee;
import xyz.wecloud.mybatis.models.EmployeeCustom;

public interface EmployeeMapper {
    Employee selectEmployeeById(Integer id);
    EmployeeCustom selectEmployeeCustomById(Integer id);
}
