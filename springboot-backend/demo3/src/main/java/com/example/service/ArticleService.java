package com.example.service;

import com.example.pojo.Article;

public interface ArticleService {
    void add(Article article, int userId);

    PageBean<Article> items(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void modify(Article article);

    void delete(int id);
}
