package com.nikolakostic.isa_ecommerce.category.service;

import com.nikolakostic.isa_ecommerce.category.entity.Category;
import com.nikolakostic.isa_ecommerce.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public void validateCategories(List<Category> categoryList) throws IllegalArgumentException {
        categoryList.forEach(category -> this.categoryRepository.findById(category.getId()).orElseThrow(IllegalArgumentException::new));
    }

}
