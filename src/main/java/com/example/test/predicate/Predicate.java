package com.example.test.predicate;

import com.example.test.model.User;

public class Predicate {

	private Predicate() {}

	public static java.util.function.Predicate<User> idPredicate(long id) {
		return u -> u.getId() == id;
	}

	public static java.util.function.Predicate<User> userNamePredicate(String username) {
		return u -> u.getUsername().equalsIgnoreCase(username);
	}
	
	public static java.util.function.Predicate<User> passwordPredicate(String password) {
		return u -> u.getPassword().equals(password);
	}
	
	public static java.util.function.Predicate<User> firstNamePredicate(String firstName) {
		return u -> u.getFirstName().equalsIgnoreCase(firstName);
	}

	public static java.util.function.Predicate<User> lastNamePredicate(String lastName) {
		return u -> u.getLastName().equalsIgnoreCase(lastName);
	}

	public static java.util.function.Predicate<User> userAgeGraterThan(int age) {
		return u -> u.getAge() >= age;
	}

}
