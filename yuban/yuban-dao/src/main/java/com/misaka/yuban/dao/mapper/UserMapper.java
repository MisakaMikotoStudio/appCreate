package com.misaka.yuban.dao.mapper;

import com.misaka.yuban.common.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface  UserMapper {

    @Select("select * from user where name = #{name} limit 1")
    User selectByName(@Param("name") String name);

    @Update("update user set " +
            "token = #{token}, " +
            "token_expire = #{tokenExpire} " +
            "where id = #{id} " +
            "limit 1")
    int updateToken(@Param("id") Integer id, @Param("token") String token, @Param("tokenExpire") Long tokenExpire);

    @Insert("insert into user (name, password, token, token_expire) " +
            "values (#{name}, #{password}, #{token}, #{tokenExpire})")
    int register(@Param("name") String name, @Param("password") String password,
                 @Param("token") String token, @Param("tokenExpire") Long tokenExpire);

    @Select("select * from user where token = #{token} limit 1")
    User selectTokenRecord(@Param("token") String token);

    @Select("select * from user where id = #{id} limit 1")
    User selectById(@Param("id") Integer id);

    @Update("update user set icon = #{icon} where id = #{id}")
    int saveUserIcon(@Param("id") Integer id, @Param("icon") String icon);
}
