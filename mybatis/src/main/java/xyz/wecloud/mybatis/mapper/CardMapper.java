package xyz.wecloud.mybatis.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import xyz.wecloud.mybatis.model.Card;

import java.util.List;

public interface CardMapper {
    @Results({
            @Result(property="cardId", column="card_id", id = true),
            @Result(property="cardName", column="card_name"),
            @Result(property="cardType", column="card_type"),
            @Result( property="empId", column="emp_id"),
    })
    @Select("SELECT * FROM TAB_CARD WHERE emp_id = #{empId}")
    List<Card> selectCardsByEmployeeId(Integer employeeId);
}
