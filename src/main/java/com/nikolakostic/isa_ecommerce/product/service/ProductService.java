package com.nikolakostic.isa_ecommerce.product.service;

import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.product.repository.ProductRepository;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return productRepository.findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(criteria, criteria);
    }


    public List<Product> getAllByCategoryIdsAndOtherFilters(Optional<List<Long>> categoryIds, Optional<List<Long>> countryIds,
                                                            Optional<Double> price, Optional<Integer> avgReview) {
        List<Product> products = this.getAll();
        if (categoryIds.isPresent()) {
            products = products.stream()
                    .filter(product -> categoryIds.get().contains(product.getSubcategory().getCategory().getId()))
                    .collect(Collectors.toList());
        }
        return this.filter(products, countryIds, price, avgReview);
    }

    public List<Product> getAllBySubcategoryIdsAndOtherFilters(Optional<List<Long>> subcategoryIds, Optional<List<Long>> countryIds,
                                                               Optional<Double> price, Optional<Integer> avgReview) {
        List<Product> products = this.getAll();
        if (subcategoryIds.isPresent()) {
            products = products.stream()
                    .filter(product -> subcategoryIds.get().contains(product.getSubcategory().getId()))
                    .collect(Collectors.toList());
        }
        return this.filter(products, countryIds, price, avgReview);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private List<Product> filter(List<Product> products, Optional<List<Long>> countryIds, Optional<Double> price, Optional<Integer> avgReview) {
        if(countryIds.isPresent()) {
            products = products.stream()
                    .filter(product -> countryIds.get().contains(product.getCountryOfOrigin().getId()))
                    .collect(Collectors.toList());
        }
        if(price.isPresent()) {
            products = products.stream()
                    .filter(product -> price.get() >= product.getPrice())
                    .collect(Collectors.toList());
        }
        if(avgReview.isPresent()) {
            products = products.stream()
                    .filter(product -> avgReview.get() <= product.getAverageReviewValue() || product.getAverageReviewValue().equals(0))
                    .collect(Collectors.toList());
        }
        return products;
    }
}
