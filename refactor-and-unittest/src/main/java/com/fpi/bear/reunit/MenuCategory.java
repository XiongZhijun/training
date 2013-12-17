package com.fpi.bear.reunit;

public class MenuCategory {

	private long id;
	private String name;

	public MenuCategory() {
		super();
	}

	public MenuCategory(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
