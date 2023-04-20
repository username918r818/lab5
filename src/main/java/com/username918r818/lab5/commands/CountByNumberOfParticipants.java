package com.username918r818.lab5.commands;

import com.username918r818.lab5.commandUtils.ConcreteCommand;
import com.username918r818.lab5.commandUtils.Receiver;

public class CountByNumberOfParticipants extends ConcreteCommand {

	public CountByNumberOfParticipants(Receiver receiver, String... args) {
		super(receiver, args);
	}

	public void execute() {
		super.getReceiver().count_by_number_of_participants(getArgs());
	}

}
