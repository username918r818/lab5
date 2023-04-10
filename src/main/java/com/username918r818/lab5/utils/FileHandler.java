package com.username918r818.lab5.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileHandler {

	static Scanner getFileScanner(File file) throws FileNotFoundException, IOException {
		return new Scanner(file);
	}

	static void fillFile(File file, String content) throws FileNotFoundException, IOException{
		//TODO: CHECK PERMISSIONS
		FileOutputStream fileStream = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
		writer.write(content);
		writer.close();

	}

	static String getFileString(File file) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(file);
		//TODO: CHECK PERMISSIONS
		StringBuilder content = new StringBuilder();
		while (scanner.hasNextLine()) {
			content.append(scanner.nextLine()).append("\n");
		}
		scanner.close();
		String result = content.toString();
		return result;
	}

}
