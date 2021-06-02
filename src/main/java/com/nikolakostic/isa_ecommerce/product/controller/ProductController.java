package com.nikolakostic.isa_ecommerce.product.controller;

import com.nikolakostic.isa_ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<?> getAllByCategoryIdsAndPriceBetween(@RequestParam(name = "startPrice", required = false) Double startPrice,
                                                                @RequestParam(name = "endPrice", required = false) Double endPrice) {
        try {
            return ResponseEntity.ok().body(this.productService.getAllByPriceBetweenStartAndEnd(startPrice, endPrice));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("category/{categoryIds}")
    public ResponseEntity<?> getAllByCategoryIdsAndPriceBetween(@PathVariable("categoryIds") List<Long> categoryIds,
                                                                @RequestParam(name = "startPrice", required = false) Double startPrice,
                                                                @RequestParam(name = "endPrice", required = false) Double endPrice) {
        try {
            return ResponseEntity.ok().body(this.productService.getAllByCategoryIdsAndPriceBetweenStartAndEnd(categoryIds, startPrice, endPrice));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("subcategory/{subcategoryIds}")
    public ResponseEntity<?> getAllBySubcategoryIds(@PathVariable("subcategoryIds") List<Long> subcategoryIds,
                                                    @RequestParam(name = "startPrice", required = false) Double startPrice,
                                                    @RequestParam(name = "endPrice", required = false) Double endPrice) {
        try {
            return ResponseEntity.ok().body(this.productService.getAllBySubcategoryIdsAndPriceBetweenStartAndEnd(subcategoryIds, startPrice, endPrice));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
