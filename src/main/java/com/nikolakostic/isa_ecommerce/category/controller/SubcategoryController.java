package com.nikolakostic.isa_ecommerce.category.controller;

import com.nikolakostic.isa_ecommerce.category.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

}
