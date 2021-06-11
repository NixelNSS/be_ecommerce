package com.nikolakostic.isa_ecommerce.shoppingcart.controller;

import com.nikolakostic.isa_ecommerce.shoppingcart.dto.AddRemoveProductDTO;
import com.nikolakostic.isa_ecommerce.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("product/decrease")
    public ResponseEntity<?> decreaseProduct(@RequestBody AddRemoveProductDTO dto) {
        try {
            return ResponseEntity.ok().body(this.shoppingCartService.decreaseProduct(dto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("buy")
    public ResponseEntity<?> buy(@RequestParam(name = "address", required = false) Optional<String> address) {
        try {
            return ResponseEntity.ok().body(this.shoppingCartService.buy(address));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getByUser() {
        try {
            return ResponseEntity.ok().body(this.shoppingCartService.getByUser());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
