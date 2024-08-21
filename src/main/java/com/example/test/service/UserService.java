package com.example.test.service;

import java.util.List;

import com.example.test.dto.UserDetailsDto;
import com.example.test.filter.UserFilter;
import com.example.test.model.User;

public interface UserService {
	
	public void createUser(UserDetailsDto userDto);
	public void updateUser(UserDetailsDto userDto);
	public User findUserById(long userId);
	public void deleteUser(long userId);
	
	public List<User> filterUsersMethod(UserFilter userFilter);
	public List<User> filterUsersAnnotation(UserFilter userFilter);
	public List<User> filterUsersAnnotationNative(UserFilter userFilter);
	public List<User> filterUsersSpecification(UserFilter userFilter);
	public List<User> filterUsersSpecificationWithRole(UserFilter userFilter);
	
}
