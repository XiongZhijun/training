package com.fpi.bear.reunit;

public class Menu {

	private long id;
	private String name;
	private MenuCategory category;
	public static final long LANGUAGE_SETTING_MENU = 2;
	public static final long USER_SETTING_MENU = 3;
	public static final long SYSTEM_SETTING_MENU = 1;

	public Menu() {
		super();
	}

	public Menu(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Menu(long id, String name, MenuCategory category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuCategory getCategory() {
		return category;
	}

	public void setCategory(MenuCategory category) {
		this.category = category;
	}

}
