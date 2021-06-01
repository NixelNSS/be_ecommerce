package com.nikolakostic.isa_ecommerce.user.controller;

import com.nikolakostic.isa_ecommerce.user.dto.UpdateProfileDTO;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping()
    public ResponseEntity<User> update(@Valid @RequestBody UpdateProfileDTO dto) {
        try {
            return ResponseEntity.ok().body(this.userService.update(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
