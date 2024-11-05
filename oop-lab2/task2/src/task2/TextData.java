package task2;

import java.nio.file.Paths;

public class TextData {
    private String fileName;
    private String text;
    private int numberOfVowels;
    private int numberOfConsonants;
    private int numberOfLetters;
    private int numberOfSentences;
    private String longestWord;

    public TextData(String fileName, String text) {
        this.fileName = fileName;
        this.text = text;
        this.numberOfVowels = getNumberOfVowels();
        this.numberOfConsonants = getNumberOfConsonants();
        this.numberOfLetters = getNumberOfLetter();
        this.numberOfSentences = getNumberOfSentences();
        this.longestWord = getLongestWord();
    }

    private int getNumberOfVowels() {
        return (int) text.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count();
    }

    private int getNumberOfConsonants() {
        return (int) text.chars().filter(c -> Character.isLetter(c) && "AEIOUaeiou".indexOf(c) == -1).count();
    }

    private int getNumberOfLetter() {
        return (int) text.chars().filter(Character::isLetter).count();
    }

    private int getNumberOfSentences() {
        return text.split("[.!?]").length;
    }

    private String getLongestWord() {
        String[] words = text.split("\\W+");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public String getFilename() {
        return Paths.get(fileName).getFileName().toString();
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Content:" + text + "\n" +
                "File: " + getFilename() + "\n" +
                "Vowels: " + numberOfVowels + "\n" +
                "Consonants: " + numberOfConsonants + "\n" +
                "Letters: " + numberOfLetters + "\n" +
                "Sentences: " + numberOfSentences + "\n" +
                "Longest Word: " + longestWord;
    }
}
