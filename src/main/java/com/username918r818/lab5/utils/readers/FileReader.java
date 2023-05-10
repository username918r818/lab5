package com.username918r818.lab5.utils.readers;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.username918r818.lab5.utils.InputHandler;

public class FileReader implements InputHandler {
    Scanner scanner;

    public FileReader(File file) throws IOException {
        this.scanner = new Scanner(file).useDelimiter("");
    }

    /**
     * Reads a line from the file
     * 
     * @return Returns String object if the file contains. Otherwise, returns null
     */
    public String getNextLine() {
        if (this.scanner.hasNext()) {
            return this.scanner.nextLine();
        }
        return null;
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        this.scanner.close();
    }
}
