package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper()
public interface UserMapper {

    @Select("select id,username,password,nickname,email,user_pic as userPic,create_time as createTime," +
            "update_time as updateTime from user where username=#{username}")
    User findByName(@Param("username") String username);


    @Insert("insert into user (username,password) values " +
            "(#{username},#{password})")
    void add(@Param("username") String username,@Param("password") String password);

    @Update("update user set nickname=#{user.nickname}, email=#{user.email} where id=#{user.id}")
    void update(@Param("user") User user);

    @Update("update user set user_pic=#{url} where id=#{id}")
    void updateAvatar(@Param("url") String url, @Param("id") Integer id);

    @Update("update user set password=#{md5} where id=#{id}")
    void updatePassword(@Param("md5") String md5, @Param("id") int id);
}
