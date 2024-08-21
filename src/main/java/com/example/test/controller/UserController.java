package com.example.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.constants.RestUrl;
import com.example.test.dto.UserDetailsDto;
import com.example.test.dto.UserInfoDto;
import com.example.test.filter.UserFilter;
import com.example.test.service.UserService;
import com.example.test.util.EntityToDtoMapper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService userService;

	@PostMapping(RestUrl.USERS)
	public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody UserDetailsDto userDto) {
		userService.createUser(userDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(RestUrl.USER)
	public ResponseEntity<UserInfoDto> findUser(@PathVariable Long userId) {
		return new ResponseEntity<>(EntityToDtoMapper.userToUserInfoDto(userService.findUserById(userId)), HttpStatus.OK);
	}

	@PutMapping(RestUrl.USERS)
	public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody UserDetailsDto userDto) {
		userService.updateUser(userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(RestUrl.USERS)
	public ResponseEntity<HttpStatus> deleteUser(@RequestParam long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(RestUrl.USERS)
	public ResponseEntity<List<UserInfoDto>> filterUsersMethod(@RequestBody UserFilter userFilter) {
		return new ResponseEntity<>(EntityToDtoMapper.userToUserInfoDtos(userService.filterUsersMethod(userFilter)), HttpStatus.OK);
	}

	@GetMapping(RestUrl.USERS_SPECIFICATION)
	public ResponseEntity<List<UserInfoDto>> filterUsersSpecification(@RequestBody UserFilter userFilter) {
		return new ResponseEntity<>(EntityToDtoMapper.userToUserInfoDtos(userService.filterUsersSpecification(userFilter)), HttpStatus.OK);
	}

	@GetMapping(RestUrl.USERS_SPECIFICATION_ROLE)
	public ResponseEntity<List<UserInfoDto>> filterUsersSpecificationWithRole(@RequestBody UserFilter userFilter) {
		return new ResponseEntity<>(EntityToDtoMapper.userToUserInfoDtos(userService.filterUsersSpecificationWithRole(userFilter)), HttpStatus.OK);
	}
	
}
