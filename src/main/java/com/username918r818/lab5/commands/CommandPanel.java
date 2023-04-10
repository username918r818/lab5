package com.username918r818.lab5.commands;

import java.util.HashMap;

import com.username918r818.lab5.Command;
import com.username918r818.lab5.Executor;
import com.username918r818.lab5.Invoker;

public class CommandPanel {
	private Executor executor;
	private Invoker invoker;
	HashMap<String, Command> commandMap = new HashMap<>();

	{	
		commandMap.put("help", new Help());
	}

	public CommandPanel(Executor executor, Invoker invoker) {
		this.executor = executor;
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
		command.setCommand(executor, args);
		this.invoker.execute(command);
	}

	


}
