package com.example.controller;

import com.example.pojo.Category;
import com.example.pojo.Result;
import com.example.service.CategoryService;
import com.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping()
    public Result add(@RequestBody @Validated Category category){
        System.out.println(category);
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        categoryService.add(category,id);
        return Result.success();
    }

    @GetMapping()
    public Result<List<Category>> get(){
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        List<Category> list=categoryService.get(id);
       return Result.success(list);
    }

    @GetMapping("/detail")
    public Result<Category> getDetail(int id){
        Category category=categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping("/modify")
    public Result modify(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.modify(category);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(int id){
        categoryService.delete(id);
        return Result.success();
    }
}
