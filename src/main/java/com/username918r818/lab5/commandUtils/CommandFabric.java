package com.username918r818.lab5.commandUtils;

import java.util.HashMap;

import com.username918r818.lab5.commands.*;

/**
 * CommandFabric class creates Command objects.
 * 
 * @author Kirill Zakusov
 */
public class CommandFabric {
	private Client client;
	private Invoker invoker;
	private Receiver receiver;

	/**
	 * CommandFabric constructor builds command for its own client, invoker or receiver.
	 * @param client
	 * @param invoker
	 * @param receiver
	 */
	public CommandFabric(Client client, Invoker invoker, Receiver receiver) {
		this.client = client;
		this.invoker = invoker;
		this.receiver = receiver;
	}

	/** 
	 * Gives a new command object by enum type of the command and args for it.
	 * 
	 * @param type
	 * @param args
	*/
	public Command getCommand(CommandType type, String... args) {
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
				command = new Exit(client, args);
				break;
			case REMOVE_AT:
				command = new RemoveAt(receiver, args);
				break;
			case REMOVE_LOWER:
				command = new RemoveLower(receiver, args);
				break;
			case HISTORY:
				command = new History(invoker, args);
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
				command = null;
				break;
		}
		return command;
	}

	/** 
	 * Gets type of the command by the string.
	 * 
	 * @param string
	*/
	public CommandType getType(String string) {
		var cTypes = new HashMap<String, CommandType>();
        cTypes.put("help", CommandType.HELP);
        cTypes.put("help", CommandType.HELP);
        cTypes.put("info", CommandType.INFO);
        cTypes.put("show", CommandType.SHOW);
        cTypes.put("add", CommandType.ADD);
        cTypes.put("update", CommandType.UPDATE);
        cTypes.put("remove_by_id", CommandType.REMOVE_BY_ID);
        cTypes.put("clear", CommandType.CLEAR);
        cTypes.put("save", CommandType.SAVE);
        cTypes.put("execute_script", CommandType.EXECUTE_SCRIPT);
        cTypes.put("exit", CommandType.EXIT);
        cTypes.put("remove_at", CommandType.REMOVE_AT);
        cTypes.put("remove_lower", CommandType.REMOVE_LOWER);
        cTypes.put("history", CommandType.HISTORY);
        cTypes.put("group_counting_by_name", CommandType.GROUP_COUNTING_BY_NAME);
        cTypes.put("count_by_number_of_participants", CommandType.COUNT_BY_NUMBER_OF_PARTICIPANTS);
        cTypes.put("print_unique_genre", CommandType.PRINT_UNIQUE_GENRE);
		return cTypes.get(string);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setInvoker(Invoker invoker) {
		this.invoker = invoker;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

    public Client getClient() {
        return client;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public Receiver getReceiver() {
        return receiver;
    }
}
