package com.study.boot.mapper;

import com.study.boot.dto.QuestionDTO;
import com.study.boot.model.Question;
import com.study.boot.model.QuestionUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface QuestionMapper{
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    @Results(id = "questionMap", value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "comment_count",property = "commentCount"),
            @Result(column = "view_count",property = "viewCount"),
            @Result(column = "like_count",property = "likeCount"),
            @Result(column = "tag",property = "tag"),
            @Result(column = "creator",property = "user",one=@One(select = "com.study.boot.mapper.UserMapper.findById",fetchType = FetchType.EAGER))
    })
    List<QuestionUser> list();

    @Select("select * from question where creator =#{id}")
    @ResultMap(value = {"questionMap"})
    List<QuestionUser> listByUserId(@Param("id") int id);

    @Select("select * from question where id = #{id}")
    @ResultMap("questionMap")
    QuestionUser getById(@Param("id") Integer id);

    @Select("select * from question where id =#{id}")
    Question getByIdUpdate(@Param("id") Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified},creator=#{creator},tag=#{tag} where id =#{id}")
    void setByIdUpdate(Question question);
}