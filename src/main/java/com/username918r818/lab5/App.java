package com.username918r818.lab5;

import com.username918r818.lab5.commands.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import com.username918r818.lab5.Test;

public class App {
    public static void main(String[] args) {
        Executor executor = new Executor();
        Invoker invoker = new Invoker();
        CommandPanel commandPanel = new CommandPanel(executor, invoker);
        

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.trim().split("\\s+"); // Этот метод использует регулярное выражение \\s+ для
                                                        // разделения строки на массив слов. Метод trim() удаляет
                                                        // начальные и конечные пробелы из строки. Вы можете
                                                        // использовать этот метод таким образом
            // Command command = commandMap.get(words[0]);
            // if (command == null) {
            //     System.out.println("Неверная команда. Воспользуйтесь командой help");
            //     break;
            // }
            // commandPanel.mm
            // String[] vArgs = Arrays.copyOfRange(words, 1, words.length);
            // invoker.execute(command.invokeCommand(vArgs));
        }
        scanner.close();
    }
}
