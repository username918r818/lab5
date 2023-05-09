package com.username918r818.lab5.commandUtils;

import com.username918r818.lab5.commands.*;

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
			case INFO:
				command = new Info(receiver, args);
				break;
			case SHOW:
				command = new Show(receiver, args);
				break;
			case ADD:
				command = new Add(receiver, args);
				break;
			case UPDATE:
				command = new Update(receiver, args);
				break;
			case REMOVE_BY_ID:
				command = new RemoveById(receiver, args);
				break;
			case CLEAR:
				command = new Clear(receiver, args);
				break;
			case SAVE:
				command = new Save(receiver, args);
				break;
			case EXECUTE_SCRIPT:
				command = new ExecuteScript(receiver, args);
				break;
			case EXIT:
				command = new Exit(receiver, args);
				break;
			case REMOVE_AT:
				command = new RemoveAt(receiver, args);
				break;
			case REMOVE_LOWER:
				command = new RemoveLower(receiver, args);
				break;
			case HISTORY:
				command = new History(receiver, args);
				break;
			case GROUP_COUNTING_BY_NAME:
				command = new GroupCountingByName(receiver, args);
				break;
			case COUNT_BY_NUMBER_OF_PARTICIPANTS:
				command = new CountByNumberOfParticipants(receiver, args);
				break;
			case PRINT_UNIQUE_GENRE:
				command = new PrintUniqueGenre(receiver, args);
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

