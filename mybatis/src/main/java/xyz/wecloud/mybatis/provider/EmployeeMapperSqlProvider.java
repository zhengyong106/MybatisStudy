package xyz.wecloud.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import xyz.wecloud.mybatis.model.Employee;

public class EmployeeMapperSqlProvider{
    public String selectEmployeeByExample(Employee example){
        return new SQL(){{
            SELECT("*");
            FROM("TAB_EMP");
            if(example.getEmpId() != null){
                WHERE("emp_id=#{empId}");
            }
            if(example.getEmpName() != null){
                WHERE("emp_name=#{empName}");
            }
            if(example.getEmpSalary() != null){
                WHERE("emp_salary=#{empSalary}");
            }
            if(example.getEmpAge() != null) {
                WHERE("emp_age=#{empAge}");
            }
        }}.toString();
    }
}