package xyz.wecloud.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import xyz.wecloud.mybatis.model.Employee;
import xyz.wecloud.mybatis.model.EmployeeCustom;
import xyz.wecloud.mybatis.provider.EmployeeMapperSqlProvider;

import java.util.List;

public interface EmployeeMapperAnnotation {
    @Results(id = "employeeResultMap", value = {
        @Result( property="empId", column="emp_id", id = true),
        @Result(property="empName", column="emp_name"),
        @Result(property="empSalary", column="emp_salary"),
        @Result(property="empAge", column="emp_age")
    })
    @Select("SELECT * FROM TAB_EMP WHERE emp_id=#{id}")
    Employee selectEmployeeById(Integer id);

    @ResultMap("employeeResultMap")
    @Select("<script>" +
            "   SELECT * FROM TAB_EMP " +
            "   WHERE emp_id IN " +
            "   <foreach collection=\"list\" item=\"item\" open=\"(\" close=\")\" separator=\",\" index=\"index\">" +
            "       #{item}" +
            "   </foreach>" +
            "</script>")
    List<Employee> selectEmployeesByIds(List<Integer> ids);

    @ResultMap("employeeResultMap")
    @SelectProvider(type = EmployeeMapperSqlProvider.class, method = "selectEmployeeByExample")
    List<Employee> selectEmployeeByExample(Employee example);

    @Results({
            @Result( property="empId", column="emp_id", id = true),
            @Result(property="empName", column="emp_name"),
            @Result(property="empSalary", column="emp_salary"),
            @Result(property="empAge", column="emp_age"),
            @Result(property="department", column="dep_id", one = @One(select = "xyz.wecloud.mybatis.mapper.DepartmentMapper.selectDepartmentById", fetchType = FetchType.LAZY)),
            @Result(property="empCards", column="emp_id", many = @Many(select = "xyz.wecloud.mybatis.mapper.CardMapper.selectCardsByEmployeeId", fetchType = FetchType.LAZY))
    })
    @Select("select * from TAB_EMP where emp_id=#{id}")
    EmployeeCustom selectEmployeeCustomById(Integer id);
}