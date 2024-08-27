package com.example.test.comparator;

import java.util.Comparator;

import com.example.test.model.User;

public class UserComparator {
	
	private UserComparator() {}

	public static Comparator<User> compareUsername() {
		return (s1, s2) -> s1.getUsername().compareTo(s2.getUsername());
	}

	public static Comparator<User> compareId() {
		return (s1, s2) -> s2.getId().compareTo(s1.getId());
	}

}
