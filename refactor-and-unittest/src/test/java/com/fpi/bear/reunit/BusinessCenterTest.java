package com.fpi.bear.reunit;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration
public class BusinessCenterTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private BusinessCenter center;
	protected TestOutputStream out;

	@Before
	public void setUp() throws FileNotFoundException {
		out = new TestOutputStream();
		System.setOut(out);
	}

	@Test
	public void testOnSystemSettingsMenuSelected() {
		Menu systemSettingsMenu = new Menu(Menu.SYSTEM_SETTING_MENU,
				"systemSetting");
		center.onMenuSelected(systemSettingsMenu, new UserImpl("admin",
				"123456"));
		out.check("system settings menu is selected.");
	}

	@Test
	public void testOnSystemSettingsMenuSelectedWithNullUser() {
		Menu systemSettingsMenu = new Menu(Menu.SYSTEM_SETTING_MENU,
				"systemSetting");
		center.onMenuSelected(systemSettingsMenu, null);
		out.check("");
	}

	@Test
	public void testOnSystemSettingsMenuSelectedWithNotAdmin() {
		Menu systemSettingsMenu = new Menu(Menu.SYSTEM_SETTING_MENU,
				"systemSetting");
		center.onMenuSelected(systemSettingsMenu, new UserImpl("root"));
		out.check("");
	}

	@Test
	public void testOnLanguageSettingsMenuSelected() {
		Menu languageSettingsMenu = new Menu(Menu.LANGUAGE_SETTING_MENU,
				"languageSetting");
		center.onMenuSelected(languageSettingsMenu, new UserImpl("admin",
				"123456"));
		out.check("language settings menu is selected.");
	}

	@Test
	public void testOnLanguageSettingsMenuSelectedWithNotAdmin() {
		Menu languageSettingsMenu = new Menu(Menu.LANGUAGE_SETTING_MENU,
				"languageSetting");
		center.onMenuSelected(languageSettingsMenu, new UserImpl("abc"));
		out.check("");
	}

	@Test
	public void testOnUserSettingsMenuSelected() {
		Menu userSettingsMenu = new Menu(Menu.USER_SETTING_MENU, "userSetting");
		center.onMenuSelected(userSettingsMenu, new UserImpl("leader", "111"));
		out.check("user settings menu is selected.");
	}

	@Test
	public void testOnUserSettingsMenuSelectedWithNotLeader() {
		Menu userSettingsMenu = new Menu(Menu.USER_SETTING_MENU, "userSetting");
		center.onMenuSelected(userSettingsMenu, new UserImpl());
		out.check("");
	}

}
