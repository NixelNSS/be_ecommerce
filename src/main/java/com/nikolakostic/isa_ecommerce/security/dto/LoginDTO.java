package com.nikolakostic.isa_ecommerce.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Getter
    @Setter
    @Email
    private String email;

    @Getter
    @Setter
    @Size(min = 8)
    @NotBlank
    private String password;

}
