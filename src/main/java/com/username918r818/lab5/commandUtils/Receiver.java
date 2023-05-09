package com.username918r818.lab5.commandUtils;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import com.username918r818.lab5.models.Album;
import com.username918r818.lab5.models.Coordinates;
import com.username918r818.lab5.models.MusicBand;
import com.username918r818.lab5.models.MusicGenre;
import com.username918r818.lab5.utils.CollectionHandler;
import com.username918r818.lab5.utils.FileHandler;
import com.username918r818.lab5.utils.JsonHandler;
import com.username918r818.lab5.utils.ModelHandler;

public class Receiver {
	private ArrayList<Command> history = new ArrayList<>();
	CollectionHandler collection;
	File file;
	Scanner scanner;
	boolean isExit = false;

	public Receiver(File file, Scanner scanner) {
		String fileString;
		try {
			fileString = FileHandler.getFileString(file);
		} catch (Exception e) {
			System.out.println("Ошибка при чтении файла");
			this.exit();
			return;
		}

		this.file = file;
		this.collection = new CollectionHandler();

		Map rawMap;
		try {
			rawMap = JsonHandler.JSONToMap(fileString);
			this.collection = new CollectionHandler(ZonedDateTime.parse(rawMap.get("initDate").toString()));
			var models = (HashMap) rawMap.get("models");
			for (var key : models.keySet()) {
				this.collection.add(ModelHandler.mapToModel((HashMap) models.get(key)));
			}
		} catch (Exception e) {
			System.out.println("Неправильный JSON файл");
			System.out.println("Сгенерирована новая коллекция");
			this.collection = new CollectionHandler();
		}
	}

	public void addHistory(Command command) {
		this.history.add(command);
	}
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
		sb.append(
				"count_by_number_of_participants numberOfParticipants : вывести количество элементов, значение поля numberOfParticipants которых равно заданному\n");
		sb.append("print_unique_genre : вывести уникальные значения поля genre всех элементов в коллекции\n");
		System.out.println(sb.toString());
	}

	public void info(String... args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Type: Musicband\n");
		sb.append("Init Date: ").append(this.collection.getInitDate()).append('\n');
		sb.append("Count: ").append(this.collection.getCount()).append('\n');
		System.out.println(sb.toString());
	}

	public void show(String... args) {
		// TODO: приккрутить норм вывод
		System.out.println(this.collection.show());
	}

	public void add(String... args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter group name: ");
		String name = scanner.nextLine();

		System.out.print("Enter X coordinate: ");
		int x = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter Y coordinate: ");
		double y = Double.parseDouble(scanner.nextLine());

		System.out.print("Enter number of participants: ");
		int numberOfParticipants = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter music genre: ");
		MusicGenre genre = MusicGenre.valueOf(scanner.nextLine());

		System.out.print("Enter album name: ");
		String albumName = scanner.nextLine();

		System.out.print("Enter number of tracks: ");
		int albumTracks = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter number of sales: ");
		int albumSales = Integer.parseInt(scanner.nextLine());

		Album album = new Album(albumName, albumTracks, albumSales);
		Coordinates coordinates = new Coordinates(x, y);
		MusicBand musicBand = new MusicBand(name, coordinates, numberOfParticipants, genre, album);
		this.collection.add(musicBand);
	}

	public void update(String... args) {
		Scanner scanner = new Scanner(System.in);
		String name;
		while(true) {
			System.out.print("Enter group name: ");
			name = scanner.nextLine();
			if (name != null && !name.isEmpty()) {
				break;
			}
		}

		System.out.print("Enter X coordinate: ");
		int x = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter Y coordinate: ");
		double y = Double.parseDouble(scanner.nextLine());

		System.out.print("Enter number of participants: ");
		int numberOfParticipants = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter music genre: ");
		MusicGenre genre = MusicGenre.valueOf(scanner.nextLine());

		System.out.print("Enter album name: ");
		String albumName = scanner.nextLine();

		System.out.print("Enter number of tracks: ");
		int albumTracks = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter number of sales: ");
		int albumSales = Integer.parseInt(scanner.nextLine());

		Album album = new Album(albumName, albumTracks, albumSales);
		Coordinates coordinates = new Coordinates(x, y);
		MusicBand musicBand = new MusicBand(name, coordinates, numberOfParticipants, genre, album);
		this.collection.update(musicBand);
	}

	public void remove_by_id(String... args) {
		this.collection.removeByUUID(UUID.fromString(args[0]));
	}

	public void clear(String... args) {
		this.collection.clear();
	}

	public void save(String... args) {
		var json = this.collection.save();
		try {
			FileHandler.writeFile(this.file, json);
		} catch (IOException e) {
			System.out.println("Отказано в доступе(");
		}
	}

	public void execute_script(String... args) {
	}

	public void exit(String... args) {
		this.isExit = true;
	}

	public void remove_at(String... args) {
		this.collection.removeAtIndex(Integer.parseInt(args[0]));
	}

	public void remove_lower(String... args) {
		this.collection.removeLower(Integer.parseInt(args[0]));
	}

	public void history(String... args) {
		
		for (int i = history.size()-2; i > history.size()-7 && i >= 0; i--) {
			System.out.println(history.get(i).getClass().getName());
		}
	}

	public void group_counting_by_name(String... args) {
	}

	public void count_by_number_of_participants(String... args) {
	}

	public void print_unique_genre(String... args) {
	}

	public boolean isExit() {
		return this.isExit;
	}
}
