package com.username918r818.lab5.commandUtils;

import com.username918r818.lab5.commands.Help;

public class CommandFabric {

	public static Command get(CommandType type, Receiver receiver, String... args) {
		Command command = null;
		if (type == null) {
			return null;
		}
		switch (type) {
			case HELP:
				command = new Help(receiver, args);
				break;
			default:
				break;
		}
		return command;
	}

	public static Command get(String type, Receiver receiver, String... args) {
		try {
			return get(CommandType.valueOf(type), receiver, args);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
