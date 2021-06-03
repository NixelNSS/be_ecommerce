package com.nikolakostic.isa_ecommerce.order.controller;

import com.nikolakostic.isa_ecommerce.order.exception.InvalidOrderException;
import com.nikolakostic.isa_ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getAllByUser() {
        try {
            return ResponseEntity.ok().body(this.orderService.getAllByUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(this.orderService.deleteById(id));
        } catch (InvalidOrderException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
