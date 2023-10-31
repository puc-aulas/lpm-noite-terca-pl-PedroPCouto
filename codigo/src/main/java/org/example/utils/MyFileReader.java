package org.example.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
    private String fileName;

    public MyFileReader(String fileName) {
        this.fileName = fileName;
    }

    public String read() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
