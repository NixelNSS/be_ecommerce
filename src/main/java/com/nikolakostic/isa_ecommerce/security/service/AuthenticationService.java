package com.nikolakostic.isa_ecommerce.security.service;

import com.nikolakostic.isa_ecommerce.security.dto.ChangePasswordDTO;
import com.nikolakostic.isa_ecommerce.security.dto.LoginDTO;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import com.nikolakostic.isa_ecommerce.user.exception.InvalidCredentialsException;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public User authenticate(LoginDTO dto) throws InvalidCredentialsException {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            return userService.getAuthenticatedUser();
        } catch (Exception e) {
            throw new InvalidCredentialsException();
        }
    }

    public void changePassword(ChangePasswordDTO dto) {
        if (dto.getNewPassword().equals(dto.getConfirmPassword())) {
            this.authenticate(new LoginDTO(dto.getEmail(), dto.getOldPassword()));
            userService.changePassword(dto.getNewPassword());
        }
    }

}
