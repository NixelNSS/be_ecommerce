package com.nikolakostic.isa_ecommerce.product.controller;

import com.nikolakostic.isa_ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(this.productService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(this.productService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("criteria/{nameOrDesc}")
    public ResponseEntity<?> getAllByNameOrDescriptionContainCriteria(@PathVariable("nameOrDesc") String criteria) {
        try {
            return ResponseEntity.ok().body(this.productService.getAllByNameOrDescriptionContainCriteria(criteria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("category")
    public ResponseEntity<?> getAllByCategoryIdsAndPriceBetween(@RequestParam("categoryIds") List<Long> categoryIds,
                                                                @RequestParam(name = "countryIds", required = false) Optional<List<Long>> countryIds,
                                                                @RequestParam(name = "price", required = false) Optional<Double> price,
                                                                @RequestParam(name = "averageReview", required = false) Optional<Integer> averageReview) {
        try {
            return ResponseEntity.ok().body(this.productService.getAllByCategoryIdsAndOtherFilters(categoryIds, countryIds, price, averageReview));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("subcategory")
    public ResponseEntity<?> getAllBySubcategoryIdsAndPriceBetween(@RequestParam("subcategoryIds") List<Long> subcategoryIds,
                                                                   @RequestParam(name = "countryIds", required = false) Optional<List<Long>> countryIds,
                                                                   @RequestParam(name = "price", required = false) Optional<Double> price,
                                                                   @RequestParam(name = "averageReview", required = false) Optional<Integer> averageReview) {
        try {
            return ResponseEntity.ok().body(this.productService.getAllBySubcategoryIdsAndOtherFilters(subcategoryIds, countryIds, price, averageReview));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
