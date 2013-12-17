package com.fpi.bear.reunit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;

public class UserImplTest {

	@Test
	public void testIsLeader() {

		IMocksControl control = EasyMock.createControl();
		UserChecker userChecker = control.createMock(UserChecker.class);

		userChecker.isLeader("zhangsan", "111");
		EasyMock.expectLastCall().andReturn(true).once();
		userChecker.isLeader("zhangsan", null);
		EasyMock.expectLastCall().andReturn(false).once();
		control.replay();
		UserImpl user1 = new UserImpl();
		user1.setName("zhangsan");
		user1.setPassword("111");
		user1.setUserChecker(userChecker);
		assertTrue(user1.isLeader());

		UserImpl user2 = new UserImpl();
		user2.setName("zhangsan");
		user2.setUserChecker(userChecker);
		assertFalse(user2.isLeader());

		control.verify();
	}

	@Test
	public void testIsAdministrator() {
		fail();
	}

}
