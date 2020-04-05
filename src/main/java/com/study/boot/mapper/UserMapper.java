package com.study.boot.mapper;

import com.study.boot.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where id = #{id}")
    @Results(value = {
            @Result(column = "avatar_url",property = "avatarUrl"),
            @Result(column = "account_id",property = "accountId")
    })
    User findById(@Param("id") Integer id);
}
