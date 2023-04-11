package com.username918r818.lab5.commands;

import java.util.HashMap;

public class CommandPanel {
	private Receiver receiver;
	private Invoker invoker;
	HashMap<String, Command> commandMap = new HashMap<>();

	{	
		// commandMap.put("help", new Help());
	}

	public CommandPanel(Receiver receiver, Invoker invoker) {
		this.receiver = receiver;
		this.invoker = invoker;
	}

	public void execute(String commandName) {
		this.execute(commandName, new String[0]);
	}

	public void execute(String commandName, String[] args) {
		Command command = commandMap.get(commandName);
		if (command == null) {
			System.out.println("Неверная команда. Воспользуйтесь командой help");
			return;
		}
		// command.setCommand(receiver, args);
		this.invoker.execute(command);
	}

	


}
