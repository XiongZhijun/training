package com.fpi.bear.reunit.handler;

import com.fpi.bear.reunit.Menu;
import com.fpi.bear.reunit.User;

public class LanguageSettingsMenuHandler implements MenuHandler {

	public void handle(User user) {
		// ...
		// ...
		if (user.isAdministrator()) {
			System.out.println("language settings menu is selected.");
		}
	}

	public boolean match(Menu menu) {
		return menu.getId() == Menu.LANGUAGE_SETTING_MENU;
	}
}
