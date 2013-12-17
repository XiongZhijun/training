package com.fpi.bear.reunit.handler;

import com.fpi.bear.reunit.Menu;
import com.fpi.bear.reunit.User;

public class UserSettingsMenuHandler implements MenuHandler {

	public void handle(User user) {
		// ...
		// ...
		if (user.isLeader()) {
			System.out.println("user settings menu is selected.");
		}
	}

	public boolean match(Menu menu) {
		return menu.getId() == Menu.USER_SETTING_MENU;
	}

}
