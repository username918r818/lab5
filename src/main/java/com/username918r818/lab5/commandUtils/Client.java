package com.username918r818.lab5.commandUtils;

import java.util.Arrays;

import com.username918r818.lab5.utils.InputHandler;

/**
 * Class for the client-side part of the program. Gets inputHandler, invoker,
 * receiver and commandFabric.
 * 
 * @author Kirill Zakusov
 */
public class Client {

    private InputHandler inputHandler;
    private Invoker invoker;
    private Receiver receiver;
    private CommandFabric commandFabric;

    private boolean isRunning = true;

    public Client(InputHandler inputHandler, Invoker invoker, Receiver receiver, CommandFabric commandFabric) {
        this.inputHandler = inputHandler;
        this.invoker = invoker;
        this.receiver = receiver;
        this.commandFabric = commandFabric;
    }

    /**
     * Ends shell session for the client.
     * 
     * @param args
     */
    public void exit(String... args) {
        isRunning = false;
    }

    /**
     * Runs the client. It waits for input.
     */
    public void run() {
        while (isRunning && receiver.isRunning()) {
            {
                String line = inputHandler.getNextLine();
                if (line == null) {
                    break;
                }
                String[] words = line.trim().split("\s"); // Этот метод использует регулярное выражение \\s+ для
                                                          // разделения строки на массив слов. Метод trim() удаляет
                                                          // начальные и конечные пробелы из строки.

                if (line.equals("")) {
                    System.out
                            .println("Уберите лапку вашего котика от клавиши \"Enter\", в потоке ввода пустая строка.");
                    continue;
                }

                Command command = commandFabric.getCommand(commandFabric.getType(words[0]),
                        Arrays.copyOfRange(words, 1, words.length));

                if (command == null) {
                    System.out.println("Уберите вашего котика с клавиатуры.");
                    continue;
                }

                invoker.execute(command);
            }
        }
    }
}
