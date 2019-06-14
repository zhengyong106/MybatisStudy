package xyz.wecloud.mybatis.mapper;

import xyz.wecloud.mybatis.model.Employee;
import xyz.wecloud.mybatis.model.EmployeeCustom;

public interface EmployeeMapper {
    Employee selectEmployeeById(Integer id);
    EmployeeCustom selectEmployeeCustomById(Integer id);
}
