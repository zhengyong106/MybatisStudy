package xyz.wecloud.mybatis.spring.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import xyz.wecloud.mybatis.spring.models.Department;

public interface DepartmentMapper {
    @Results(value = {
            @Result(property = "depId", column = "dep_id", id = true),
            @Result(property = "depName", column = "dep_name"),
            @Result(property = "depCTime", column = "dep_ctime")
    })
    @Select("SELECT * FROM TAB_DEP WHERE dep_id = #{id}")
    Department selectDepartmentById(Integer id);

    @Insert("INSERT INTO TAB_DEP (dep_name, dep_ctime) VALUES(#{depName}, #{depCTime})")
    void insertDepartment(Department department);
}
