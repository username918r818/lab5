package com.username918r818.lab5.commandUtils.abstractCommands;

import com.username918r818.lab5.commandUtils.Client;
import com.username918r818.lab5.commandUtils.Command;

/**
 * Abstract command class for client command.
 * 
 * @author Kirill Zakusov
 */
public abstract class ClientCommand implements Command {

    private Client client;
    private String[] args;

    public ClientCommand(Client client, String... args) {
        this.client = client;
        this.args = args;
    }

    public Client getClient() {
        return this.client;
    }

    public String[] getArgs() {
        return this.args;
    }

    public void setArgs(String... args) {
        // this.args = Arrays.copyOf(args, args.length);
        this.args = args;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
