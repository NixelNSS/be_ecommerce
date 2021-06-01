package com.nikolakostic.isa_ecommerce.security.dto;

import com.nikolakostic.isa_ecommerce.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @Getter
    @Setter
    @Email
    private String email;

    @Getter
    @Setter
    @Size(min = 8)
    @NotBlank
    private String password;

    @Getter
    @Setter
    private String confirmPassword;

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

    @Getter
    @Setter
    private List<Category> favoriteCategories;

}
