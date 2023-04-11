package com.username918r818.lab5.commands;

public class Receiver {
	
	public void help() {
		System.out.println("help : вывести справку по доступным командам");
		System.out.println("info : вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
		System.out.println("show : вывести все элементы коллекции в строковом представлении");
		System.out.println("add : добавить новый элемент в коллекцию");
		System.out.println("update id : обновить значение элемента коллекции, id которого равен заданному");
		System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
		System.out.println("clear : очистить коллекцию");
		System.out.println("save : сохранить коллекцию в файл");
		System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла.");
		System.out.println("exit : завершить программу (без сохранения в файл)");
		System.out.println("remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)");
		System.out.println("remove_lower  : удалить из коллекции все элементы, меньшие, чем заданный");
		System.out.println("history : вывести последние 5 команд (без их аргументов)");
		System.out.println("group_counting_by_name: сгруппировать элементы коллекции по значению поля name,");
		System.out.println("count_by_number_of_participants numberOfParticipants : вывести количество элементов, значение поля numberOfParticipants которых равно заданному");
		System.out.println("print_unique_genre : вывести уникальные значения поля genre всех элементов в коллекции");	
	} 
    public void info() {} 
    public void show() {}
    public void add(Object element) {}
    public void update(Object id) {}
    public void remove_by_id(Object id) {}
    public void clear() {}
    public void save() {}
    public void execute_script(Object file_name ) {}
    public void exit() {}
    public void remove_at(int index) {}
    public void remove_lower(Object element) {}
    public void history() {}
    public void group_counting_by_name() {}
    public void count_by_number_of_participants(int numberOfParticipants) {}
    public void print_unique_genre() {}
}
