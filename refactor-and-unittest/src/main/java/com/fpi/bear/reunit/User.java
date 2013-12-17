package com.fpi.bear.reunit;

public interface User {

	public static final User NULL_USER = new User() {
		public boolean isLeader() {
			return false;
		}

		public boolean isAdministrator() {
			return false;
		}
	};

	public abstract boolean isLeader();

	public abstract boolean isAdministrator();

}