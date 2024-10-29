package task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide paths to the .txt files as arguments.");
            return;
        }

        for (String filePath : args) { //updated for task4
            try {
                String content = FileReader.readFileIntoString(filePath);
                TextData textData = new TextData(filePath, content);
                System.out.println(textData);
                System.out.println("----");
            } catch (IOException e) {
                System.err.println("Error reading file: " + filePath);
                e.printStackTrace();
            }
        }
    }
}
