package com.username918r818.lab5;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.username918r818.lab5.commandUtils.*;

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
        Scanner scanner = new Scanner(System.in).useDelimiter("");
        
        Invoker invoker = new Invoker();

        Receiver receiver = new Receiver(file, scanner);

        var cTypes = new HashMap<String, CommandType>();
        cTypes.put("help", CommandType.HELP);
        cTypes.put("info", CommandType.INFO);
        cTypes.put("show", CommandType.SHOW);
        cTypes.put("add", CommandType.ADD);
        cTypes.put("update", CommandType.UPDATE);
        cTypes.put("remove_by_id", CommandType.REMOVE_BY_ID);
        cTypes.put("clear", CommandType.CLEAR);
        cTypes.put("save", CommandType.SAVE);
        cTypes.put("execute_script", CommandType.EXECUTE_SCRIPT);
        cTypes.put("exit", CommandType.EXIT);
        cTypes.put("remove_at", CommandType.REMOVE_AT);
        cTypes.put("remove_lower", CommandType.REMOVE_LOWER);
        cTypes.put("history", CommandType.HISTORY);
        cTypes.put("group_counting_by_name", CommandType.GROUP_COUNTING_BY_NAME);
        cTypes.put("count_by_number_of_participants", CommandType.COUNT_BY_NUMBER_OF_PARTICIPANTS);
        cTypes.put("print_unique_genre", CommandType.PRINT_UNIQUE_GENRE);
        

        
        while (scanner.hasNext() && !receiver.isExit()) {
            
            String line = scanner.nextLine();
            String[] words = line.trim().split("\\s+"); // Этот метод использует регулярное выражение \\s+ для
                                                        // разделения строки на массив слов. Метод trim() удаляет
                                                        // начальные и конечные пробелы из строки. Вы можете
                                                        // использовать этот метод таким образом
            if (line.equals("")) {
                System.out.println("Уберите лапку вашего котика от клавиши \"Enter\", в потоке ввода пустая строка.");
                continue;
            }
            //TODO: fix the bug when args have multiple spaces but it doesn't pass


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
