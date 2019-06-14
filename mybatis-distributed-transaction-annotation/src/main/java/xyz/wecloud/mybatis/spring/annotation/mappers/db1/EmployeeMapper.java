package xyz.wecloud.mybatis.spring.annotation.mappers.db1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import xyz.wecloud.mybatis.spring.annotation.models.Employee;

public interface EmployeeMapper {
    @Results(value = {
            @Result(property = "empId", column = "emp_id", id = true),
            @Result(property = "empName", column = "emp_name"),
            @Result(property = "empSalary", column = "emp_salary"),
            @Result(property = "empAge", column = "emp_age"),
            @Result(property = "depId", column = "dep_id")
    })
    @Select("SELECT * FROM TAB_EMP WHERE emp_id = #{id}")
    Employee selectEmployeeById(Integer id);

    @Insert("INSERT INTO TAB_EMP (emp_name, emp_salary, emp_age) VALUES(#{empName}, #{empSalary}, #{empAge})")
    void insertEmployee(Employee employee);
}
