package com.lindada.community.mapper;

import com.lindada.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)\n" +
            "VALUES (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag});")
    void create(Question question);

    @Select("select * from question limit #{size} offset #{offset}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} order by id limit #{size} OFFSET #{offset}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "size") Integer size,@Param(value = "offset") Integer offset);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}