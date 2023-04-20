package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.ConcreteCommand;
import com.username918r818.lab5.commandUtils.Receiver;

public class Info extends ConcreteCommand {

	public Info(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().info(getArgs());
	}

}
