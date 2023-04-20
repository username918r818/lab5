package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.ConcreteCommand;
import com.username918r818.lab5.commandUtils.Receiver;

public class GroupCountingByName extends ConcreteCommand {

	public GroupCountingByName (Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().group_counting_by_name(getArgs());
	}

}
