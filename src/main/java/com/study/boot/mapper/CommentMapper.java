package com.study.boot.mapper;

import com.study.boot.model.Comment;
import com.study.boot.model.CommentExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,content) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{content})")
    void insertComment(Comment comment);
    @Select("select * from comment where parent_id = #{parentId}")
    Comment selectByPrimaryKey(@Param("parentId") Long parentId);

    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);


    List<Comment> selectByExampleWithRowbounds(CommentExample example, RowBounds rowBounds);


    List<Comment> selectByExample(CommentExample example);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
