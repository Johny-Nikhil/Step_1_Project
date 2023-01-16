package com.step1.project.Service;

import com.step1.project.Entity.Category;
import com.step1.project.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategoryByName(String name){
        return categoryRepository.findByCategoryName(name);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
