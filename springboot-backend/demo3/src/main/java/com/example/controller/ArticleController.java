package com.example.controller;

import com.example.pojo.Article;
import com.example.pojo.Category;
import com.example.pojo.Result;
import com.example.service.ArticleService;
import com.example.service.PageBean;
import com.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public Result getAll(@RequestHeader("Authorization") String token){
        return Result.success("all articles");
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article){
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        articleService.add(article,userId);
        return Result.success();
    }

    @GetMapping()
    public Result<PageBean<Article>> items(Integer pageNum, Integer pageSize,
                                           @RequestParam(required = false)Integer categoryId,
                                           @RequestParam(required = false) String state){
        PageBean<Article> pb=articleService.items(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @PutMapping("/modify")
    public Result modify(@RequestBody @Validated(Article.Update.class) Article article){
        articleService.modify(article);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(int id){
        articleService.delete(id);
        return Result.success();
    }
}
