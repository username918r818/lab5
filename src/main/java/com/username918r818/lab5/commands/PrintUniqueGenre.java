package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.ConcreteCommand;
import com.username918r818.lab5.commandUtils.Receiver;

public class PrintUniqueGenre extends ConcreteCommand {

	public PrintUniqueGenre(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().print_unique_genre(getArgs());
	}

}