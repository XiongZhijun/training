package com.fpi.bear.reunit.handler;

import com.fpi.bear.reunit.Menu;
import com.fpi.bear.reunit.User;

public interface MenuHandler {

	void handle(User user);

	boolean match(Menu menu);
}