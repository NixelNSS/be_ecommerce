package com.nikolakostic.isa_ecommerce.user.dto;

import com.nikolakostic.isa_ecommerce.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

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

    @Getter
    @Setter
    private List<Category> favoriteCategories;

}
