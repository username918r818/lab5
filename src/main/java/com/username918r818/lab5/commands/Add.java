package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.Receiver;
import com.username918r818.lab5.commandUtils.abstractCommands.ReceiverCommand;

public class Add extends ReceiverCommand {
	/**
	 * @param receiver
	 * @param args
	 */
	public Add(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().add(getArgs());
	}

}
