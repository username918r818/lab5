package com.username918r818.lab5.utils.readers;

import java.io.InputStream;
import java.util.Scanner;

import com.username918r818.lab5.utils.InputHandler;

public class StreamReader implements InputHandler {
    Scanner scanner;

    public StreamReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream).useDelimiter("");
    }

    /**
     * Reads a line from the input stream
     * 
     * @return Returns String object if the input stream contains. Otherwise,
     *         returns null
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
