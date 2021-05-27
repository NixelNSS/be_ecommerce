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
public class ChangePasswordDTO {

    @Getter
    @Setter
    @Email
    private String email;

    @Getter
    @Setter
    private String oldPassword;

    @Getter
    @Setter
    @Size(min = 8)
    @NotBlank
    private String newPassword;

    @Getter
    @Setter
    private String confirmPassword;
}
