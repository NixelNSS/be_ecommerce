package com.nikolakostic.isa_ecommerce.security.service;

import com.nikolakostic.isa_ecommerce.security.dto.ChangePasswordDTO;
import com.nikolakostic.isa_ecommerce.security.dto.LoginRequestDTO;
import com.nikolakostic.isa_ecommerce.security.dto.LoginResponseDTO;
import com.nikolakostic.isa_ecommerce.security.model.ConcreteUserDetails;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import com.nikolakostic.isa_ecommerce.user.exception.InvalidCredentialsException;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ConcreteUserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtService;

    public LoginResponseDTO authenticate(LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        ConcreteUserDetails userDetails = (ConcreteUserDetails) userDetailsService.loadUserByUsername(dto.getEmail());
        final String jwt = jwtService.generateToken(userDetails);
        User user = userService.getById(userDetails.getId());
        return new LoginResponseDTO(
                user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getPhone(), user.getAddress(), jwt
        );
    }

    public void changePassword(ChangePasswordDTO dto) throws InvalidCredentialsException {
        if (dto.getNewPassword().equals(dto.getConfirmPassword())) {
            this.authenticate(new LoginRequestDTO(dto.getEmail(), dto.getOldPassword()));
            userService.changePassword(dto.getNewPassword());
        } else throw new InvalidCredentialsException();
    }

}
