package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.Receiver;
import com.username918r818.lab5.commandUtils.abstractCommands.ReceiverCommand;

public class RemoveLower extends ReceiverCommand {
	/**
	 * @param receiver
	 * @param args
	 */
	public RemoveLower(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().remove_lower(getArgs());
	}

}
