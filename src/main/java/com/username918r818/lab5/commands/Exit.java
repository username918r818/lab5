package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.Client;
import com.username918r818.lab5.commandUtils.abstractCommands.ClientCommand;

public class Exit extends ClientCommand {
	/**
	 * @param client
	 * @param args
	 */
	public Exit(Client client, String... args) {
		super(client, args);
	}

	public void execute() {
		super.getClient().exit(getArgs());
	}

}
