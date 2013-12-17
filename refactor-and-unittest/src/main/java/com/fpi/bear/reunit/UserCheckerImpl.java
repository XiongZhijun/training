package com.fpi.bear.reunit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserCheckerImpl implements UserChecker {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fpi.bear.reunit.UserChecker#isAdministrator(java.lang.String,
	 * java.lang.String)
	 */
	public boolean isAdministrator(String name, String password) {
		String role = getRoleByUser(name, password);
		return "administrator".equals(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fpi.bear.reunit.UserChecker#isLeader(java.lang.String,
	 * java.lang.String)
	 */
	public boolean isLeader(String name, String password) {
		return "leader".equals(getRoleByUser(name, password));
	}

	private String getRoleByUser(String name, String password) {
		List<Map<String, Object>> users = jdbcTemplate.queryForList(
				"select * from User where name = ? and password = ?", name,
				password);
		if (users == null || users.size() == 0) {
			return "";
		}
		Map<String, Object> user = users.get(0);
		String role = (String) user.get("role");
		return role;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
