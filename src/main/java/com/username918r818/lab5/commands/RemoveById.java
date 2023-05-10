package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.Receiver;
import com.username918r818.lab5.commandUtils.abstractCommands.ReceiverCommand;

public class RemoveById extends ReceiverCommand {
	/**
	 * @param receiver
	 * @param args
	 */
	public RemoveById(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().remove_by_id(getArgs());
	}

}
