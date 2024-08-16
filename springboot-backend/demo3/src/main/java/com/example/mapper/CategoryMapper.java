package com.example.mapper;


import com.example.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper()
public interface CategoryMapper {

    @Insert("Insert into category (category_name, category_alias,create_user) values " +
            "(#{category.categoryName},#{category.categoryAlias},#{id})")
    void add(@Param("category") Category category, @Param("id") Integer id);

    @Select("select id, category_name as categoryName, category_alias as categoryAlias," +
            "create_user as createUser,create_time as createTime, update_time as updateTime" +
            " from category where create_user=#{userId}")
    List<Category> get(@Param("userId") Integer userId);

    @Select("select id, category_name as categoryName, category_alias as categoryAlias," +
            "create_user as createUser,create_time as createTime, update_time as updateTime" +
            " from category where id=#{id}")
    Category findById(@Param("id") int id);


    @Update("update category set category_name=#{category.categoryName}," +
            "category_alias=#{category.categoryAlias} where id=#{category.id}")
    void modify(@Param("category") Category category);

    @Delete("delete from category where id=#{id}")
    void delete(@Param("id") int id);
}
