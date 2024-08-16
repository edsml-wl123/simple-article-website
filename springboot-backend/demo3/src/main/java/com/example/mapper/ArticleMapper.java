package com.example.mapper;

import com.example.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("insert into article (title,content,cover_img,state,category_id,create_user) values " +
            "(#{article.title},#{article.content},#{article.coverImg}, #{article.state}," +
            "#{article.categoryId}, #{userId})")
    void add(@Param("article") Article article, @Param("userId") int userId);


    List<Article> items(@Param("categoryId") Integer categoryId, @Param("state")String state,
                        @Param("userId")Integer userId);

    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg}," +
            "state=#{state},category_id=#{categoryId}")
    void modify(@Param("article") Article article);

    @Delete("delete from article where id=#{id}")
    void delete(@Param("id") int id);
}
