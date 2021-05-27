package com.nikolakostic.isa_ecommerce.security.service;

import com.nikolakostic.isa_ecommerce.security.model.ConcreteUserDetails;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import com.nikolakostic.isa_ecommerce.user.exception.InvalidCredentialsException;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcreteUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            return new ConcreteUserDetails(
                    optionalUser.get().getId(),
                    optionalUser.get().getEmail(),
                    optionalUser.get().getPassword()
            );
        }
        throw new InvalidCredentialsException();
    }
}
