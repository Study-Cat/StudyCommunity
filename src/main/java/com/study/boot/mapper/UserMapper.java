package com.study.boot.mapper;

import com.study.boot.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where account_id = #{accountId}")
    @Results(id="userMap",value = {
            @Result(column = "avatar_url",property = "avatarUrl"),
            @Result(column = "account_id",property = "accountId")
    })
    User findById(@Param("accountId") String accountId);

    @Select("select * from user where token=#{token}")
    @ResultMap(value = {"userMap"})
    User findByToken(@Param("token") String token);

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);



    @Update("update user set token = #{user.getToken} where account_id = user.getAccountId")
    void update(User user);
}
