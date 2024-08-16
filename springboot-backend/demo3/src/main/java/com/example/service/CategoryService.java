package com.example.service;

import com.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category, Integer id);

    List<Category> get(Integer id);

    Category findById(int id);

    void modify(Category id);

    void delete(int id);
}
