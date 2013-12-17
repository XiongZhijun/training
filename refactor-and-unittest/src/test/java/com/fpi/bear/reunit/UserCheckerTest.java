package com.fpi.bear.reunit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.fpi.bear.test.AbstractDataSourceTests;

@ContextConfiguration
public class UserCheckerTest extends AbstractDataSourceTests {

	@Autowired
	private UserChecker userChecker;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setUp() {
		jdbcTemplate.execute("DROP TABLE IF EXISTS user ");
		jdbcTemplate
				.execute("create table User (id bigint, name varchar(25), password varchar(25), role varchar(25))");
		insertDataSetToDatabase("test/com/fpi/bear/reunit/user.init.dataset.xml");

	}

	@Test
	public void testCheckNotAdministrator() {
		assertFalse(userChecker.isAdministrator(null, null));
		assertFalse(userChecker.isAdministrator("admin", null));
		assertFalse(userChecker.isAdministrator("zhangsan", "111"));
	}

	@Test
	public void testCheckAdministrator() {
		assertTrue(userChecker.isAdministrator("admin", "123456"));
	}

	@Test
	public void testCheckNotLeader() {
		assertFalse(userChecker.isLeader("admin", "123456"));
		assertFalse(userChecker.isLeader("zhangsan", null));
		assertFalse(userChecker.isLeader(null, null));
	}

	@Test
	public void testCheckLeader() {
		assertTrue(userChecker.isLeader("zhangsan", "111"));
	}
}
