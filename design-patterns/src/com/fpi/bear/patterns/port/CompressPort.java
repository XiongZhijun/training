package com.fpi.bear.patterns.port;

public class CompressPort extends AbstractPort {

	public CompressPort() {
		super();
	}

	public CompressPort(AbstractPort nextPort) {
		super(nextPort);
	}

	@Override
	protected Object handleSendMessage(Object message) {
		// TODO compress
		return message;
	}

	@Override
	protected Object handleReceivedMessage(Object message) {
		// TODO uncompress
		return message;
	}

}
