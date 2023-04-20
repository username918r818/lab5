package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.ConcreteCommand;
import com.username918r818.lab5.commandUtils.Receiver;

public class RemoveLower extends ConcreteCommand {

	public RemoveLower(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().remove_lower(getArgs());
	}

}
