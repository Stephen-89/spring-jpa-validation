package com.example.test.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.test.dto.RoleDto;
import com.example.test.dto.UserDetailsDto;
import com.example.test.enums.RolesEnum;
import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	public void setupUserRoles(UserDetailsDto userDto, User newUser) {
		if(!CollectionUtils.isEmpty(userDto.getRoles())) {
			List<Long> roleIds = userDto.getRoles().stream()
	                .map(RoleDto::getId).collect(Collectors.toList());
			newUser.setRoles(roleRepository.findAllById(roleIds));
		} else {
			newUser.setRoles(Arrays.asList(new Role(2l, RolesEnum.USER.toString())));
		}
	}

}
