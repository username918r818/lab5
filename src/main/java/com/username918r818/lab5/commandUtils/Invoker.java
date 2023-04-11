package com.username918r818.lab5.commandUtils;

import java.util.ArrayList;
// import java.util.Stack;

public class Invoker {
	private ArrayList<Command> history = new ArrayList<>();
	// private Stack<Command> cancelledСommands = new Stack<>();

	public void execute(Command command) {
		history.add(command);
		command.execute();
	}

	/*
	 * public void undo() {
	 * if (history.size() > 0){
	 * cancelledСommands.add(history.get(history.size()-1));
	 * history.remove(history.size()-1);
	 * }
	 * }
	 * 
	 * public void redo() {
	 * if (history.size() > 0){
	 * cancelledСommands.add(history.get(history.size()-1));
	 * history.remove(history.size()-1);
	 * }
	 * }
	 */

}
