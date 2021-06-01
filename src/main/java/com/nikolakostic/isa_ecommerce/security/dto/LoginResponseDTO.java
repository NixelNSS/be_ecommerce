package com.nikolakostic.isa_ecommerce.security.dto;

import com.nikolakostic.isa_ecommerce.category.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDTO {
	private final Long id;
	private final String email;
	private final String firstName;
	private final String lastName;
	private final String phone;
	private final String address;
	private final List<Category> favoriteCategories;
	private final String token;
}
