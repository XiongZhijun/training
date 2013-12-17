package com.fpi.bear.reunit;

import java.io.FileNotFoundException;

import org.junit.Before;

public class ReunitBaseTestCase {

	protected TestOutputStream out;

	@Before
	public void setUp() throws FileNotFoundException {
		out = new TestOutputStream();
		System.setOut(out);
	}

}
