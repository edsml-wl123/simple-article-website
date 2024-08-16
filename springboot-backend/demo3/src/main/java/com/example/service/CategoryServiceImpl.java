package com.example.service;


import com.example.mapper.CategoryMapper;
import com.example.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category, Integer id) {
        categoryMapper.add(category,id);
    }

    @Override
    public List<Category> get(Integer id) {
        return  categoryMapper.get(id);
    }

    @Override
    public Category findById(int id) {
        Category category=categoryMapper.findById(id);
        return category;

    }

    @Override
    public void modify(Category category) {
        categoryMapper.modify(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }
}
