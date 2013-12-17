package com.fpi.bear.patterns.port;

public class CrcPort extends AbstractPort {

	public CrcPort() {
		super();
	}

	public CrcPort(AbstractPort nextPort) {
		super(nextPort);
	}

	@Override
	protected Object handleSendMessage(Object message) {
		// TODO add crc
		return message;
	}

	@Override
	protected Object handleReceivedMessage(Object message) {
		// TODO remove crc
		return message;
	}

}
