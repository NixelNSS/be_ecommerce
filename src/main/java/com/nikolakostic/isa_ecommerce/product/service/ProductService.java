package com.nikolakostic.isa_ecommerce.product.service;

import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.product.repository.ProductRepository;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllByNameOrDescriptionContainCriteria(String criteria) {
        return productRepository.findAllByNameContainsOrDescriptionContains(criteria, criteria);
    }

    public List<Product> getAllByPriceBetweenStartAndEnd(Double startPrice, Double endPrice) {
        startPrice = fixPrice(startPrice);
        endPrice = fixPrice(endPrice);
        return productRepository.findAllByPriceBetween(startPrice, endPrice);
    }

    public List<Product> getAllByCategoryIdsAndPriceBetweenStartAndEnd(List<Long> categoryIds, Double startPrice, Double endPrice) {
        startPrice = fixPrice(startPrice);
        endPrice = fixPrice(endPrice);
        return productRepository.findAllBySubcategory_Category_IdInAndPriceBetween(categoryIds, startPrice, endPrice);
    }

    public List<Product> getAllBySubcategoryIdsAndPriceBetweenStartAndEnd(List<Long> subcategoryIds, Double startPrice, Double endPrice) {
        startPrice = fixPrice(startPrice);
        endPrice = fixPrice(endPrice);
        return productRepository.findAllBySubcategory_Category_IdInAndPriceBetween(subcategoryIds, startPrice, endPrice);
    }

    private Double fixPrice(Double price) {
        return price == null ? 0D : price;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
