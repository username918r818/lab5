package com.username918r818.lab5.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileHandler {

	/**
	 * Creates a new Scanner object for the given file.
	 *
	 * @param file The file to read.
	 * @return A Scanner object for the given file.
	 * @throws IOException If the file is not found or is not a valid file.
	 */
	public static Scanner getFileScanner(File file) throws IOException {
		if (!file.exists() && !file.isFile()) {
			throw new IOException("File not found.");
		}
		return new Scanner(file);
	}

	/**
	 * Writes the given content to the specified file.
	 *
	 * @param file    The file to write to.
	 * @param content The content to write to the file.
	 * @throws IOException If the file write permission is denied.
	 */
	public static void writeFile(File file, String content) throws IOException {
		if (!file.canWrite()) {
			throw new IOException("File write permission denied.");
		}
		FileOutputStream fileStream = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
		writer.write(content);
		writer.close();
	}

	/**
	 * Reads the contents of a file as a String.
	 * 
	 * @param file The file to read.
	 * @return The contents of the file as a String.
	 * @throws IOException If the file read permission is denied.
	 */
	public static String getFileString(File file) throws IOException {
		if (!file.canRead()) {
			throw new IOException("File read permission denied.");
		}
		Scanner scanner = new Scanner(file);
		StringBuilder contentBuilder = new StringBuilder();
		while (scanner.hasNextLine()) {
			contentBuilder.append(scanner.nextLine()).append(System.lineSeparator());
		}
		scanner.close();
		return contentBuilder.toString();
	}

}
