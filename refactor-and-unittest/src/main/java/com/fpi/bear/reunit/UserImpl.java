package com.fpi.bear.reunit;

public class UserImpl implements User {

	private String name;
	private String password;
	private UserChecker userChecker;

	public UserImpl() {
		super();
	}

	public UserImpl(String name) {
		super();
		this.name = name;
	}

	public UserImpl(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fpi.bear.reunit.User#isLeader()
	 */
	public boolean isLeader() {
		return userChecker.isLeader(name, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fpi.bear.reunit.User#isAdministrator()
	 */
	public boolean isAdministrator() {
		return userChecker.isAdministrator(name, password);
	}

	public void setUserChecker(UserChecker userChecker) {
		this.userChecker = userChecker;
	}
}
