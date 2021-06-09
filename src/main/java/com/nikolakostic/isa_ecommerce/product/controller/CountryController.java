package com.nikolakostic.isa_ecommerce.product.controller;

import com.nikolakostic.isa_ecommerce.product.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(this.countryService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
