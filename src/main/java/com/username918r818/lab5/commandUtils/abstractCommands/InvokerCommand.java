package com.username918r818.lab5.commandUtils.abstractCommands;

import com.username918r818.lab5.commandUtils.Command;
import com.username918r818.lab5.commandUtils.Invoker;

/**
 * Abstract command class for invoker command.
 * 
 * @author Kirill Zakusov
 */
public abstract class InvokerCommand implements Command {

    private Invoker invoker;
    private String[] args;

    public InvokerCommand(Invoker invoker, String... args) {
        this.invoker = invoker;
        this.args = args;
    }

    public Invoker getInvoker() {
        return this.invoker;
    }

    public String[] getArgs() {
        return this.args;
    }

    public void setArgs(String... args) {
        // this.args = Arrays.copyOf(args, args.length);
        this.args = args;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

}
