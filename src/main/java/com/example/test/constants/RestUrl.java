package com.example.test.constants;

public class RestUrl {
	
	private RestUrl() {}

	public static final String USERS = "/users";
	public static final String USER = USERS + "/{userId}";
	public static final String USERS_SPECIFICATION = "/users-specification";
	public static final String USERS_SPECIFICATION_ROLE = USERS_SPECIFICATION + "-role";
	
}
