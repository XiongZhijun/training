package com.fpi.bear.patterns.port;

public abstract class AbstractPort implements Port {

	private Port previousPort;
	private Port nextPort;

	public AbstractPort() {
		super();
	}

	public AbstractPort(AbstractPort nextPort) {
		super();
		this.nextPort = nextPort;
		nextPort.setPreviousPort(this);
	}

	@Override
	public void sendMessage(Object message) {
		Object newMessage = handleSendMessage(message);
		if (nextPort != null) {
			nextPort.sendMessage(newMessage);
		}
	}

	protected abstract Object handleSendMessage(Object message);

	@Override
	public void receiveMessage(Object message) {
		Object newMessage = handleReceivedMessage(message);
		if (previousPort != null) {
			previousPort.receiveMessage(newMessage);
		}
	}

	protected abstract Object handleReceivedMessage(Object message);

	public void setPreviousPort(Port previousPort) {
		this.previousPort = previousPort;
	}

	public void setNextPort(Port nextPort) {
		this.nextPort = nextPort;
	}

}
