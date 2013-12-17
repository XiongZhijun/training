package com.fpi.bear.reunit.handler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import com.fpi.bear.reunit.Menu;
import com.fpi.bear.reunit.ReunitBaseTestCase;
import com.fpi.bear.reunit.User;

public class SystemSettingsMenuHandlerTest extends ReunitBaseTestCase {
	private SystemSettingsMenuHandler handler = new SystemSettingsMenuHandler();
	private IMocksControl control;

	@Before
	public void setUp() throws FileNotFoundException {
		super.setUp();
		control = EasyMock.createControl();
	}

	@Test
	public void testHandle() {
		User user = control.createMock(User.class);
		user.isAdministrator();
		EasyMock.expectLastCall().andReturn(true).once();

		control.replay();
		handler.handle(user);
		out.check("system settings menu is selected.");
		control.verify();
	}

	@Test(expected = NullPointerException.class)
	public void testHandleWithNull() {
		handler.handle(null);
	}

	@Test
	public void testMatch() {
		assertFalse(handler.match(new Menu()));
		assertFalse(handler.match(new Menu(-1, null)));
		assertTrue(handler.match(new Menu(Menu.SYSTEM_SETTING_MENU, null)));
		assertTrue(handler.match(new Menu(Menu.SYSTEM_SETTING_MENU, "System")));
	}
}
