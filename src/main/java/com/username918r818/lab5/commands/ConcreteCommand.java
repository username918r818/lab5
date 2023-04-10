package com.username918r818.lab5.commands;

import java.util.Arrays;

import com.username918r818.lab5.Command;
import com.username918r818.lab5.Executor;

public abstract class ConcreteCommand implements Command{

	private Executor executor;
	private String[] args;

	public Executor getExecutor() {
		return this.executor;
	}

	public String[] getArgs() {
		return this.args;
	}

	public void setArgs(String[] args) {
		this.args = Arrays.copyOf(args, args.length);
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	

}
