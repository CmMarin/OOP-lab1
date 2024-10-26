package task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the path to the .txt file as an argument.");
            return;
        }

        String filePath = args[0];
        try {
            String content = FileReader.readFileIntoString(filePath);
            TextData textData = new TextData(filePath, content);
            System.out.println(textData);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
