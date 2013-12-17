package com.fpi.bear.reunit;

public interface UserChecker {

	boolean isAdministrator(String name, String password);

	boolean isLeader(String name, String password);

}