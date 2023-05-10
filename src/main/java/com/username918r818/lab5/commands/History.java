package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.Invoker;
import com.username918r818.lab5.commandUtils.abstractCommands.InvokerCommand;

public class History extends InvokerCommand {
	/**
	 * @param invoker
	 * @param args
	 */
	public History(Invoker invoker, String... args) {
		super(invoker, args);
	}

	public void execute() {
		super.getInvoker().history(getArgs());
	}

}
