package com.fpi.bear.reunit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

public class TestOutputStream extends PrintStream {

	private StringBuilder sb = new StringBuilder();

	public TestOutputStream() throws FileNotFoundException {
		super(new OutputStreamStub());
	}

	public void println(String s) {
		sb.append(s);
	}

	public void print(String s) {
		sb.append(s);
	}

	public void reset() {
		sb = new StringBuilder();
	}

	public void check(String expected) {
		Assert.assertEquals(expected, sb.toString());
	}

	static class OutputStreamStub extends OutputStream {

		@Override
		public void write(int b) throws IOException {
		}

	}
}
