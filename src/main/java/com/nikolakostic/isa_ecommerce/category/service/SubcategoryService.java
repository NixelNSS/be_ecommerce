package com.nikolakostic.isa_ecommerce.category.service;

import com.nikolakostic.isa_ecommerce.category.entity.Subcategory;
import com.nikolakostic.isa_ecommerce.category.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService {

    @Autowired
    SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getAll() {
        return this.subcategoryRepository.findAll();
    }

}
