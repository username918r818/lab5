package com.username918r818.lab5.commandUtils;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

import com.username918r818.lab5.models.Album;
import com.username918r818.lab5.models.Coordinates;
import com.username918r818.lab5.models.MusicBand;
import com.username918r818.lab5.models.MusicGenre;
import com.username918r818.lab5.utils.CollectionHandler;
import com.username918r818.lab5.utils.FileHandler;
import com.username918r818.lab5.utils.InputHandler;
import com.username918r818.lab5.utils.JsonHandler;
import com.username918r818.lab5.utils.ModelHandler;
import com.username918r818.lab5.utils.readers.FileReader;

/**
 * Command receiver which has methods for commands to manipulate collection.
 * 
 * @author Kirill Zakusov
 */
public class Receiver {
	private CollectionHandler collection;
	private File file;
	private InputHandler inputHandler;
	private Invoker invoker;
	private CommandFabric commandFabric;
	private boolean isRunning = true;

	/**
	 * Receiver constructor builds collection from file. If cannot read, exit with message. 
	 * 
	 * If file has wrong json format, generates a new one.
	 * 
	 * @param file
	 * @param inputHandler
	 * @param invoker
	 * @param commandFabric
	 */
	public Receiver(File file, InputHandler inputHandler, Invoker invoker, CommandFabric commandFabric) {
		String fileString;
		this.inputHandler = inputHandler;
		this.invoker = invoker;
		this.commandFabric = commandFabric;
		try {
			fileString = FileHandler.getFileString(file);
		} catch (Exception e) {
			System.out.println("Ошибка при чтении файла");
			this.exit();
			return;
		}

		this.file = file;
		this.collection = new CollectionHandler();

		HashMap<String, String> rawMap;
		try {
			rawMap = JsonHandler.JSONToMap(fileString);
			this.collection = new CollectionHandler(ZonedDateTime.parse(rawMap.get("initDate").toString()));
			var models = JsonHandler.JSONToMap(rawMap.get("models"));
			for (var key : models.keySet()) {
				this.collection.add(ModelHandler.mapToModel(JsonHandler.JSONToMap(models.get(key))));
			}
		} catch (Exception e) {
			System.out.println("Неправильный JSON файл");
			System.out.println("Сгенерирована новая коллекция");
			this.collection = new CollectionHandler();
		}
	}

	/**
	 * @return true if receiver is running
	*/
	public boolean isRunning() {
		return isRunning;
	}

	/** 
	 * Outputs help message
	 */
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
		sb.append("print_unique_genre : вывести уникальные значения поля genre всех элементов в коллекции");
		System.out.println(sb.toString());
	}

	/**
	 * Outputs info message
	 */
	public void info(String... args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Type: Musicband\n");
		sb.append("Init Date: ").append(this.collection.getInitDate()).append('\n');
		sb.append("Count: ").append(this.collection.getCount());
		System.out.println(sb.toString());
	}

	/**
	 
	*/
	public void show(String... args) {
		System.out.println(this.collection.show());
	}

	public void add(String... args) {
		try {
			this.collection.add(readMusicBand());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void update(String... args) {
		try {
			this.collection.update(readMusicBand());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void remove_by_id(String... args) {
		UUID uuid = this.readUUID();
		this.collection.removeByUUID(uuid);
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
		var oldInputHandler = this.getInputHandler();
		InputHandler scriptInputHandler;
		try {
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
			File scriptFile = new File(sb.toString());
			scriptInputHandler = new FileReader(scriptFile);
		} catch (Exception e) {
			System.out.println("Имя файла некорректно.");
			return;
		}
		this.setInputHandler(scriptInputHandler);
		Client scriptClient = new Client(scriptInputHandler, invoker, this, commandFabric);
		scriptClient.run();
		scriptInputHandler.close();
		this.setInputHandler(oldInputHandler);
	}

	public void exit(String... args) {
		this.isRunning = false;
	}

	public void remove_at(String... args) {
		try {
			this.collection.removeAtIndex(Integer.parseInt(args[0]));
		} catch (Exception e) {
			System.out.println("Некорректный ввод");
		}
	}

	public void remove_lower(String... args) {
		try {
			this.collection.removeLower(Integer.parseInt(args[0]));
		} catch (Exception e) {
			System.out.println("Некорректный ввод");
		}
	}

	public void group_counting_by_name(String... args) {
		System.out.println(collection.group_counting_by_name());
	}

	public void count_by_number_of_participants(String... args) {
		try {
			System.out.println(collection.count_by_number_of_participants(Integer.parseInt(args[0])));
		} catch (Exception e) {
			System.out.println("Некорректный ввод");
		}
	}

	public void print_unique_genre(String... args) {
		System.out.println(collection.print_unique_genre());
	}

	private UUID readUUID() {
		UUID uuid = null;
		int count = 3;
		System.out.println("Введите UUID:");
		while (uuid == null && count > 0) {
			count--;
			try {
				var line = inputHandler.getNextLine();
				if (line == null) {
					exit();
					return null;
				}
				uuid = UUID.fromString(line);
			} catch (Exception e) {
				System.out.println("Некорректный UUID!");
				if (count == 0) {
					return null;
				}
				System.out.println("Осталось попыток: " + String.valueOf(count) + ".");
			}
		}
		return uuid;
	}

	private MusicBand readMusicBand() {
		MusicBand musicBand = new MusicBand("null", new Coordinates(1, 2.0), 1, MusicGenre.PUNK_ROCK,
				new Album("null", 2, null));
		CheckValid UUIDValid = (String string) -> {
			try {
				musicBand.setUUID(UUID.fromString(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в UUID!");
			}
			
		};
		CheckValid nameValid = (String string) -> {
			musicBand.setName(string);
		};
		CheckValid xValid = (String string) -> {
			try {
				musicBand.getCoordinates().setX(Integer.parseInt(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в X!");
			}
		};
		CheckValid yValid = (String string) -> {
			try {
				musicBand.getCoordinates().setY(Double.parseDouble(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в Y!");
			}
		};
		CheckValid numberOfParticipantsValid = (String string) -> {
			try {
				musicBand.setNumberOfParticipants(Integer.parseInt(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в число участников!");
			}
		};
		CheckValid genreValid = (String string) -> {
			try {
				musicBand.setGenre(MusicGenre.valueOf(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в жанр!");
			}

		};
		CheckValid aNameValid = (String string) -> {
			musicBand.getBestAlbum().setName(string);
		};
		CheckValid aTracksValid = (String string) -> {
			try {
				musicBand.getBestAlbum().setTracks(Integer.parseInt(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в число треков!");
			}
		};
		CheckValid aSalesValid = (String string) -> {
			if (string == null) {
				musicBand.getBestAlbum().setSales(null);
			} else {
				try {
					musicBand.getBestAlbum().setSales(Integer.parseInt(string));
				} catch (Exception e) {
					throw new RuntimeException("Нельзя преобразовать ввод в число продаж!");
				}
			}
		};
		CheckValid creationDateValid = (String string) -> {
			try {
				musicBand.setCreationDate(ZonedDateTime.parse(string));
			} catch (Exception e) {
				throw new RuntimeException("Нельзя преобразовать ввод в дату создания!");
			}
		};


		System.out.println("Желаете сгенерировать UUID? (y/n)");
		var answer = inputHandler.getNextLine();
		while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
			System.out.println("Некорректный ввод! (y/n)");
			answer = inputHandler.getNextLine();
		}
		if (answer.equalsIgnoreCase("n")) {
			readValue("UUID", UUIDValid);
		}

		System.out.println("Желаете сгенерировать creationDate? (y/n)");
		answer = inputHandler.getNextLine();
		while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
			System.out.println("Некорректный ввод! (y/n)");
			answer = inputHandler.getNextLine();
		}

		if (answer.equalsIgnoreCase("n")) {
			readValue("creationDate", creationDateValid);
		}

		readValue("name", nameValid);
		readValue("x", xValid);
		readValue("y", yValid);
		readValue("numberOfParticipants", numberOfParticipantsValid);
		readValue("genre", genreValid);

		System.out.println("Желаете ввести лучший альбом? (y/n)");
		answer = inputHandler.getNextLine();
		while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
			System.out.println("Некорректный ввод! (y/n)");
			answer = inputHandler.getNextLine();
		}

		if (answer.equalsIgnoreCase("y")) {
			readValue("aName", aNameValid);
			readValue("aTracks", aTracksValid);
			readValue("aSales", aSalesValid);
		} else {
			musicBand.setBestAlbum(null);
		}
		return musicBand;
	}

	private void readValue(String name, CheckValid checkValid) {
		boolean isSuccess = false;
		while (!isSuccess) {
			isSuccess = true;
			System.out.println("Введите " + name + ":");
			try {
				var line = inputHandler.getNextLine();
				if (line == "") {
					line = null;
				}
				checkValid.check(line);
			} catch (Exception e) {
				System.out.println("Некорректный ввод!");
				System.out.println(e.getMessage());
				isSuccess = false;
			}
		}
	}

	private void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	private InputHandler getInputHandler() {
		return this.inputHandler;
	}
}

@FunctionalInterface
interface CheckValid {
	void check(String string);
}