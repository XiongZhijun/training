package com.fpi.bear.patterns.port;

public class Client {

	public static void main(String[] args) {
		Port port = createPort();
		port.sendMessage(new Object());
	}

	private static Port createPort() {
		Port port = new CompressPort(new EncryptPort(new CrcPort()));
		return port;
	}
}
