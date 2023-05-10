package com.username918r818.lab5;

import java.io.File;
import com.username918r818.lab5.commandUtils.*;
import com.username918r818.lab5.utils.InputHandler;
import com.username918r818.lab5.utils.readers.StreamReader;

public class App {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Введите имя файла");
            return;
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (var arg : args) {
            if (!first) {
                sb.append('\s');
            }
            first = false;
            sb.append(arg);
        }
        File file = new File(sb.toString());
        Invoker invoker = new Invoker();
        CommandFabric commandFabric = new CommandFabric(null, invoker, null);
        InputHandler inputHandler = new StreamReader(System.in);
        Receiver receiver = new Receiver(file, inputHandler, invoker, commandFabric);
        Client client = new Client(inputHandler, invoker, receiver, commandFabric);
        commandFabric.setClient(client);
        commandFabric.setReceiver(receiver);
        
        client.run();
        inputHandler.close();
    }
}
