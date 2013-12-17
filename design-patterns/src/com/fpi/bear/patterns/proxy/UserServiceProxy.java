package com.fpi.bear.patterns.proxy;

import java.util.List;

public class UserServiceProxy implements UserService {
	private UserService userService;

	public UserServiceProxy(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public List<?> getAllUser() {
		// TODO 做一些权限方面的判断，判断该用户是否拥有获取所有用户的权限。
		return userService.getAllUser();
	}

}
