package com.example.test.util;

import java.util.ArrayList;
import java.util.List;

import com.example.test.dto.UserDetailsDto;
import com.example.test.dto.UserInfoDto;
import com.example.test.model.User;

public class EntityToDtoMapper {

	private EntityToDtoMapper() {
	}

	public static UserDetailsDto userToUserDto(User user) {
		return new UserDetailsDto(user.getFirstName(), user.getLastName(), user.getUsername());
	}

	public static List<UserDetailsDto> userToUserDtos(List<User> users) {
		List<UserDetailsDto> userDtos = new ArrayList<>();
		users.forEach(user -> userDtos.add(new UserDetailsDto(user.getFirstName(), user.getLastName(), user.getUsername())));
		return userDtos;
	}

	public static UserInfoDto userToUserInfoDto(User user) {
		return new UserInfoDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername());
	}

	public static List<UserInfoDto> userToUserInfoDtos(List<User> users) {
		List<UserInfoDto> userInfoDto = new ArrayList<>();
		users.forEach(user -> userInfoDto.add(new UserInfoDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername())));
		return userInfoDto;
	}

}
