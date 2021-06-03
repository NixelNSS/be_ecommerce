package com.nikolakostic.isa_ecommerce.shoppingcart.controller;

import com.nikolakostic.isa_ecommerce.shoppingcart.dto.AddRemoveProductDTO;
import com.nikolakostic.isa_ecommerce.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("product/add")
    public ResponseEntity<?> addProduct(@RequestBody AddRemoveProductDTO dto) {
        try {
            return ResponseEntity.ok().body(this.shoppingCartService.addProduct(dto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("product/remove")
    public ResponseEntity<?> removeProduct(@RequestBody AddRemoveProductDTO dto) {
        try {
            return ResponseEntity.ok().body(this.shoppingCartService.removeProduct(dto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("buy")
    public ResponseEntity<?> buy() {
        try {
            return ResponseEntity.ok().body(this.shoppingCartService.buy());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getByUser() {
        try {
            return ResponseEntity.ok().body( this.shoppingCartService.getByUser());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
