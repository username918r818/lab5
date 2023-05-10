package com.username918r818.lab5.commandUtils;

import java.util.ArrayList;

/**
 * Invoker class allows to execute commands.
 * @author Kirill Zakusov
 */
public class Invoker {
	private ArrayList<Command> history = new ArrayList<>();

	/**
	 * Simply executes the command and saving the object in history.
	 * @param command
	 */
	public void execute(Command command) {
		history.add(command);
		command.execute();
	}

	/**
	 * Prints the history of the last 5 commands.
	 */
	public void history(String... args) {
		StringBuilder sb = new StringBuilder();
		for (int i = history.size() - 6 < 0 ? 0 : history.size() - 6; i < history.size()-1; i++) {

			var className = history.get(i).getClass().getName().split("\\.");
			sb.append(className[className.length - 1]).append('\n');

		}
		if (sb.isEmpty()) {
			sb.append("empty!!\n");
		}
		System.out.print(sb.toString());

	}
}
