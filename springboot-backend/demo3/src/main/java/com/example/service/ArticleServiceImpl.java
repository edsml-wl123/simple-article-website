package com.example.service;

import com.example.mapper.ArticleMapper;
import com.example.pojo.Article;
import com.example.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article, int userId) {
        articleMapper.add(article,userId);
    }

    @Override
    public PageBean<Article> items(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb=new PageBean<>();

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");

        // PageHelper 能够追踪分页的原因是它通过使用 MyBatis的拦截器机制来实现的.
        // MyBatis 提供了一种机制，允许在执行 SQL 语句之前或之后对语句进行拦截和修改,
        // PageHelper 利用了这一机制，在 MyBatis 执行查询语句之前拦截并进行处理
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.items(categoryId, state, userId);

        Page<Article> pages = (Page<Article>) list;
        pb.setTotal(pages.getTotal());  //把数据填充到page bean中
        pb.setItems(pages.getResult());

        return pb;

    }

    @Override
    public void modify(Article article) {
        articleMapper.modify(article);
    }

    @Override
    public void delete(int id) {
        articleMapper.delete(id);

    }
}
