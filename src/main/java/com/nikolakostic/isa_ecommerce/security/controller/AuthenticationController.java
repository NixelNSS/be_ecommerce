package com.nikolakostic.isa_ecommerce.security.controller;

import com.nikolakostic.isa_ecommerce.security.dto.ChangePasswordDTO;
import com.nikolakostic.isa_ecommerce.security.dto.LoginDTO;
import com.nikolakostic.isa_ecommerce.security.dto.RegisterDTO;
import com.nikolakostic.isa_ecommerce.security.service.AuthenticationService;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import com.nikolakostic.isa_ecommerce.user.exception.InvalidCredentialsException;
import com.nikolakostic.isa_ecommerce.user.exception.UserExistsException;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("auth")
@Validated
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDTO dto) {
        try {
            return ResponseEntity.ok().body(this.userService.create(dto));
        } catch (InvalidCredentialsException | UserExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("authenticate")
    public ResponseEntity<User> authenticate(@Valid @RequestBody LoginDTO dto) {
        try {
            return ResponseEntity.ok().body(authenticationService.authenticate(dto));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
        try {
            this.authenticationService.changePassword(dto);
            return ResponseEntity.ok().build();
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
