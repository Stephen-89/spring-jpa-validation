package com.example.test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.example.test.comparator.UserComparator;
import com.example.test.model.User;
import com.example.test.predicate.Predicate;
import com.example.test.util.EntityToDtoMapper;

@SpringBootApplication
public class TestApplication {

	private static final String USER = "User: {}";

	private static final Logger LOG = LoggerFactory.getLogger(TestApplication.class);

	private static final String ROBERT = "robert";

	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);

		Set<User> users = setupUsers();

		List<User> filteredUsers = users.stream()
				.filter(user -> user.getFirstName().equalsIgnoreCase(ROBERT))
				.collect(Collectors.toList());
		
		Optional<String> userRobert = Optional.ofNullable(users.stream()
				.filter(user -> user.getFirstName().equalsIgnoreCase(ROBERT))
				.map(User::getFirstName)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element found")));
		userRobert.ifPresent(name -> LOG.info(USER, name));
		
		Optional<Integer> maxAge = users.stream().map(User::getAge)
                .max(Integer::compareTo);

		Optional<Integer> minAge = users.stream().map(User::getAge)
                .min(Integer::compareTo);
		
		LOG.info("Max and Min Age {} {}", maxAge.get(), minAge.get());
		
		for (User user : filteredUsers) {
			LOG.info(USER, EntityToDtoMapper.userToUserInfoDto(user));
		}

		List<User> filteredUsersPredicate = users.stream()
				.filter(Predicate.firstNamePredicate(ROBERT).and(Predicate.userAgeGraterThan(30)))
				.collect(Collectors.toList());
		users.stream().filter(Predicate.firstNamePredicate(ROBERT).and(Predicate.userAgeGraterThan(30))).forEachOrdered(name -> LOG.info(USER, name));
		for (User user : filteredUsersPredicate) {
			LOG.info(USER, EntityToDtoMapper.userToUserInfoDto(user));
		}

		List<String> firstNames = users.stream()
				.filter(Predicate.userAgeGraterThan(30))
				.sorted(UserComparator.compareUsername())
				.sorted(UserComparator.compareId())
				.map(User::getFirstName)
				.collect(Collectors.toList());
		for (String name : firstNames) {
			LOG.info("User First Name: {}", name);
		}

		Map<String, List<User>> userByRole = users.stream()
				.filter(u -> u.getAge() > 20 && CollectionUtils.isEmpty(u.getRoles()))
				.sorted(UserComparator.compareId())
				.collect(Collectors.groupingBy(User::getUsername));
		userByRole.forEach((username, user) -> LOG
				.info(String.format("%s: %s", username, EntityToDtoMapper.userToUserInfoDtos(user))));

	}

	private static Set<User> setupUsers() {
		Set<User> users = new HashSet<>();
		User user1 = new User(1l, "james.radclif@gmail.com", "james", "radclif", 34);
		User user2 = new User(2l, "paul.bannan@gmail.com", "paul", "bannan", 56);
		User user3 = new User(3l, "robert.roy@gmail.com", ROBERT, "roy", 25);
		User user4 = new User(4l, "frank.hoey@gmail.com", "frank", "hoey", 78);
		User user5 = new User(5l, "pat.hume@gmail.com", "pat", "hume", 25);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		return users;
	}

}
