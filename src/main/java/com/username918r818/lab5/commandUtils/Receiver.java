package com.username918r818.lab5.commandUtils;

public class Receiver {
	
	public void help(String... args) {
		StringBuilder sb = new StringBuilder();
		sb.append("help : вывести справку по доступным командам\n");
		sb.append("info : вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n");
		sb.append("show : вывести все элементы коллекции в строковом представлении\n");
		sb.append("add : добавить новый элемент в коллекцию\n");
		sb.append("update id : обновить значение элемента коллекции, id которого равен заданному\n");
		sb.append("remove_by_id id : удалить элемент из коллекции по его id\n");
		sb.append("clear : очистить коллекцию\n");
		sb.append("save : сохранить коллекцию в файл\n");
		sb.append("execute_script file_name : считать и исполнить скрипт из указанного файла.\n");
		sb.append("exit : завершить программу (без сохранения в файл)\n");
		sb.append("remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)\n");
		sb.append("remove_lower  : удалить из коллекции все элементы, меньшие, чем заданный\n");
		sb.append("history : вывести последние 5 команд (без их аргументов)\n");
		sb.append("group_counting_by_name: сгруппировать элементы коллекции по значению поля name,\n");
		sb.append("count_by_number_of_participants numberOfParticipants : вывести количество элементов, значение поля numberOfParticipants которых равно заданному\n");
		sb.append("print_unique_genre : вывести уникальные значения поля genre всех элементов в коллекции\n");
		System.out.println(sb.toString());
	}	
    public void info(String... args) {} 
    public void show(String... args) {}
    public void add(String... args) {}
    public void update(String... args) {}
    public void remove_by_id(String... args) {}
    public void clear(String... args) {}
    public void save(String... args) {}
    public void execute_script(String... args) {}
    public void exit(String... args) {}
    public void remove_at(String... args) {}
    public void remove_lower(String... args) {}
    public void history(String... args) {}
    public void group_counting_by_name(String... args) {}
    public void count_by_number_of_participants(String... args) {}
    public void print_unique_genre(String... args) {}
}
