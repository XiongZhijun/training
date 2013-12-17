package com.fpi.bear.reunit;

import java.util.ArrayList;
import java.util.List;

import com.fpi.bear.reunit.handler.MenuHandler;

public class BusinessCenter {

	private List<MenuHandler> menuHandlers = new ArrayList<MenuHandler>();

	public BusinessCenter() {
	}

	public void onMenuSelected(Menu menu, User user) {
		if (user == null) {
			user = User.NULL_USER;
		}
		for (MenuHandler menuHandler : menuHandlers) {
			if (menuHandler.match(menu)) {
				menuHandler.handle(user);
				break;
			}
		}
	}

	public void setMenuHandlers(List<MenuHandler> menuHandlers) {
		this.menuHandlers = menuHandlers;
	}
}
