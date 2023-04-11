package com.username918r818.lab5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.username918r818.lab5.commandUtils.*;

public class App {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker();

        var cTypes = new HashMap<String, CommandType>();
        cTypes.put("help", CommandType.HELP);

        Scanner scanner = new Scanner(System.in).useDelimiter("");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.trim().split("\\s+"); // Этот метод использует регулярное выражение \\s+ для
                                                        // разделения строки на массив слов. Метод trim() удаляет
                                                        // начальные и конечные пробелы из строки. Вы можете
                                                        // использовать этот метод таким образом
            if (line.equals("")) {
                System.out.println("Уберите лапку вашего котика от клавиши \"Enter\", в потоке ввода пустая строка.");
                continue;
            }

            Command command = CommandFabric.get(cTypes.get(words[0]), receiver,
                    Arrays.copyOfRange(words, 1, words.length));
            if (command == null) {
                System.out.println("Уберите вашего котика с клавиатуры.");
                continue;
            }

            invoker.execute(command);
        }
        scanner.close();
    }
}
