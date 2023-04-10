package com.username918r818.lab5.commands;

import com.username918r818.lab5.Command;
import com.username918r818.lab5.Executor;

public class Help extends ConcreteCommand{


	public Command setCommand(Executor executor, String[] args){
		Help command = new Help();
		command.setArgs(args);
		command.setExecutor(executor);
		return command;
	}


	public void execute(){
		super.getExecutor().help();
	}

}


