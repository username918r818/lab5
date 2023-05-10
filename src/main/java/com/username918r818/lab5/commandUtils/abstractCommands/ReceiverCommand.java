package com.username918r818.lab5.commandUtils.abstractCommands;

import com.username918r818.lab5.commandUtils.Command;
import com.username918r818.lab5.commandUtils.Receiver;

/**
 * Abstract command class for receiver command.
 * 
 * @author Kirill Zakusov
 */
public abstract class ReceiverCommand implements Command {

	private Receiver receiver;
	private String[] args;

	public ReceiverCommand(Receiver receiver, String... args) {
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
		// this.args = Arrays.copyOf(args, args.length);
		this.args = args;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

}
