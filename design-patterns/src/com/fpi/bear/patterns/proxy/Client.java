package com.fpi.bear.patterns.proxy;

public class Client {

	public static void main(String[] args) {
		UserService userService = findUserService();
		System.out.println(userService.getAllUser());
	}

	private static UserService findUserService() {
		UserService userService = new UserServiceProxy(new UserServiceImpl());
		return userService;
	}
}
