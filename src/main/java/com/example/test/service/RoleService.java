package com.example.test.service;

import com.example.test.dto.UserDetailsDto;
import com.example.test.model.User;

public interface RoleService {
	
	public void setupUserRoles(UserDetailsDto userDto, User newUser);
	
}
