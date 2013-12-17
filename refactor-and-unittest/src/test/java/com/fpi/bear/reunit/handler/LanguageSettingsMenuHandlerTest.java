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

public class LanguageSettingsMenuHandlerTest extends ReunitBaseTestCase {

	private LanguageSettingsMenuHandler handler = new LanguageSettingsMenuHandler();
	private IMocksControl control;

	@Before
	public void setUp() throws FileNotFoundException {
		super.setUp();
		control = EasyMock.createControl();
	}

	@Test
	public void testHandleWithAdmin() {
		User user = control.createMock(User.class);
		user.isAdministrator();
		EasyMock.expectLastCall().andReturn(true).once();

		control.replay();
		handler.handle(user);
		out.check("language settings menu is selected.");
		control.verify();
	}

	@Test(expected = NullPointerException.class)
	public void testHandleWithNull() {
		handler.handle(null);
	}

	@Test
	public void testHandleWithNullUser() {
		handler.handle(User.NULL_USER);
		out.check("");
	}

	@Test
	public void testHandleWithNotAdmin() {
		User user = control.createMock(User.class);
		user.isAdministrator();
		EasyMock.expectLastCall().andReturn(false).once();

		control.replay();
		handler.handle(user);
		out.check("");
		control.verify();
	}

	@Test
	public void testMatch() {
		assertFalse(handler.match(new Menu()));
		assertTrue(handler.match(new LanguageMenu()));
		assertFalse(handler
				.match(new Menu(Menu.SYSTEM_SETTING_MENU, "Language")));
	}

	class LanguageMenu extends Menu {

		@Override
		public long getId() {
			return Menu.LANGUAGE_SETTING_MENU;
		}

	}

}
