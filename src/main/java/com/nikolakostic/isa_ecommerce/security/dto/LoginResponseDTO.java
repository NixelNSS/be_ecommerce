package com.nikolakostic.isa_ecommerce.security.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
	private final Long id;
	private final String email;
	private final String firstName;
	private final String lastName;
	private final String phone;
	private final String address;
	private final String token;
}
