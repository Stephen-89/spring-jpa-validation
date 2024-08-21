package com.example.test.dto;

import java.util.ArrayList;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

	private Long id;
	
	@Email(message = "Enter a valid username")
	@NotNull(message = "Username should not be empty")
	private String username;
	
	@NotNull(message = "Password should not be empty")
	@Size(min = 8, message = "Password should be at least 8 characters")
	private String password;
	
	@NotBlank(message = "First Name should not be empty")
	private String firstName;
	
	@NotBlank(message = "Last Name should not be empty")
	private String lastName;
	
	private ArrayList<RoleDto> roles;
	
	public UserDetailsDto(String firstName, String lastName, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
	
}
