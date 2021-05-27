package com.nikolakostic.isa_ecommerce.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class PasswordEncoder {

    //TODO: Change to the BCrypt at the end!
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
