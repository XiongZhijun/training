package com.fpi.bear.patterns.port;

public class EncryptPort extends AbstractPort {

	public EncryptPort() {
		super();
	}

	public EncryptPort(AbstractPort nextPort) {
		super(nextPort);
	}

	@Override
	protected Object handleSendMessage(Object message) {
		// TODO encrypt
		return message;
	}

	@Override
	protected Object handleReceivedMessage(Object message) {
		// TODO decrypt
		return message;
	}

}
