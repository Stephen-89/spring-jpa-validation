package com.example.test.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.test.constants.ExceptionConstants;
import com.example.test.dto.UserDetailsDto;
import com.example.test.exceptions.ResourceAlreadyExistsException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.filter.UserFilter;
import com.example.test.model.User;
import com.example.test.repository.UserAnnotationRepository;
import com.example.test.repository.UserMethodRepository;
import com.example.test.repository.UserSpecificationRepository;
import com.example.test.repository.specifications.UserSpecification;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserMethodRepository userMethodRepository;
	private UserAnnotationRepository userAnnotationRepository;
	private UserSpecificationRepository userSpecificationRepository;
	
	private RoleService roleService;

	public List<User> filterUsersMethod(UserFilter userFilter) {
		return userMethodRepository.findByFirstNameAndLastName(userFilter.getFirstName(), userFilter.getLastName());
	}

	public List<User> filterUsersAnnotation(UserFilter userFilter) {
		return userAnnotationRepository.findByFirstNameAndLastName(userFilter.getFirstName(), userFilter.getLastName());
	}

	public List<User> filterUsersAnnotationNative(UserFilter userFilter) {
		return userAnnotationRepository.findByFirstNameAndLastNameNative(userFilter.getFirstName(), userFilter.getLastName());
	}

	public List<User> filterUsersSpecification(UserFilter userFilter) {
		return userSpecificationRepository.findAll(Specification.where(UserSpecification.hasFirstName(userFilter.getFirstName()))
				.or(UserSpecification.hasLastName(userFilter.getLastName())));
	}

	public List<User> filterUsersSpecificationWithRole(UserFilter userFilter) {
		return userSpecificationRepository.findAll(Specification.where(UserSpecification.findByFirstNameAndLastNameAndRole(userFilter)));
	}

	public void createUser(UserDetailsDto userDto) {
		userExistsByUsername(userDto);
		User newUser = new User();
		newUser.setUsername(userDto.getUsername());
		newUser.setPassword(userDto.getPassword());
		newUser.setFirstName(userDto.getFirstName());
		newUser.setLastName(userDto.getLastName());
		roleService.setupUserRoles(userDto, newUser);
		userMethodRepository.save(newUser);
	}

	public User findUserById(long userId) {
		Optional<User> user = userMethodRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		} else {
			LOG.info("User not found for id {}", userId);
			throw new ResourceNotFoundException(String.format(ExceptionConstants.USER_USERID_NOT_FOUND, userId));
		}
	}
	
	private User findByUsername(UserDetailsDto userDto) {
		Optional<User> user = userMethodRepository.findByUsername(userDto.getUsername());
		if(user.isPresent()) {
			return user.get();
		} else {
			LOG.info("User not found for username {}", userDto.getUsername());
			throw new ResourceNotFoundException(String.format(ExceptionConstants.USER_USERNAME_NOT_FOUND, userDto.getUsername()));
		}
	}

	public void updateUser(UserDetailsDto userDto) {
		User userUpdate = findByUsername(userDto);
		userUpdate.setUsername(userDto.getUsername());
		userUpdate.setPassword(userDto.getPassword());
		userUpdate.setFirstName(userDto.getFirstName());
		userUpdate.setLastName(userDto.getLastName());
		userMethodRepository.save(userUpdate);
	}


	public void deleteUser(long userId) {
		User user = findUserById(userId);
		userMethodRepository.delete(user);
	}

	private void userExistsByUsername(UserDetailsDto userDto) {
		if (Boolean.TRUE.equals(userMethodRepository.existsByUsername(userDto.getUsername()))) {
			LOG.info("A user is already registered with username: {}", userDto.getUsername());
			throw new ResourceAlreadyExistsException(String.format(ExceptionConstants.USER_USERNAME_EXISTS, userDto.getUsername()));
		}
	}

}
