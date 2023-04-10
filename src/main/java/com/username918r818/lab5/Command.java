package com.username918r818.lab5;



public interface Command {
	public Command setCommand(Executor executor, String[] args);
	public void execute();
}
