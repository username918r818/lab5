package com.username918r818.lab5.commands;

public class Help extends ConcreteCommand {

	public Help(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().help();
	}

}
