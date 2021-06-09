package com.nikolakostic.isa_ecommerce.product.service;

import com.nikolakostic.isa_ecommerce.product.entity.Country;
import com.nikolakostic.isa_ecommerce.product.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAll() {
        return this.countryRepository.findAll();
    }

}
