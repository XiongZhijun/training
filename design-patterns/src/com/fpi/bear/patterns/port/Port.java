package com.fpi.bear.patterns.port;

public interface Port {

	void sendMessage(Object message);

	void receiveMessage(Object message);
}
