package com.nikolakostic.isa_ecommerce.security.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
	private String token;
	private String type;
	private Long id;
	private String email;

	public LoginResponseDTO(String token, Long id, String email) {
		this.token = token;
		this.type = "Bearer";
		this.id = id;
		this.email = email;
	}
}
