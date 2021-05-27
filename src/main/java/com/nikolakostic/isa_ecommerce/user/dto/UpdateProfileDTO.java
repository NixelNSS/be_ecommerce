package com.nikolakostic.isa_ecommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDTO {

    @Getter
    @Setter
    @Size(min = 1, max = 50)
    @NotBlank
    private String firstName;

    @Getter
    @Setter
    @Size(min = 1, max = 50)
    @NotBlank
    private String lastName;

    @Getter
    @Setter
    @Size(min = 1, max = 255)
    @NotBlank
    private String phone;

    @Getter
    @Setter
    @Size(min = 1, max = 255)
    @NotBlank
    private String address;

}
