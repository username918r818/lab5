package com.username918r818.lab5.commandUtils;

public abstract class ConcreteCommand implements Command {

	private Receiver receiver;
	private String[] args;

	public ConcreteCommand(Receiver receiver, String... args) {
		this.receiver = receiver;
		this.args = args;
	}

	public Receiver getReceiver() {
		return this.receiver;
	}

	public String[] getArgs() {
		return this.args;
	}

	public void setArgs(String... args) {
		this.args = args;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
}
