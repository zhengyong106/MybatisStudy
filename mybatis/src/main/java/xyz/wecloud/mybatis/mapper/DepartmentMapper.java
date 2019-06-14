package xyz.wecloud.mybatis.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import xyz.wecloud.mybatis.model.Department;

public interface DepartmentMapper {
    @Results({
            @Result(property="depId", column="dep_id", id = true),
            @Result(property="depName", column="dep_name"),
            @Result(property="depCTime", column="dep_ctime")
    })
    @Select("SELECT * FROM TAB_DEP WHERE dep_id = #{depId}")
    Department selectDepartmentById(Integer id);
}
