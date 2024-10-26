package task2;

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
        this.numberOfVowels = countVowels();
        this.numberOfConsonants = countConsonants();
        this.numberOfLetters = countLetters();
        this.numberOfSentences = countSentences();
        this.longestWord = findLongestWord();
    }

    private int countVowels() {
        return (int) text.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count();
    }

    private int countConsonants() {
        return (int) text.chars().filter(c -> Character.isLetter(c) && "AEIOUaeiou".indexOf(c) == -1).count();
    }

    private int countLetters() {
        return (int) text.chars().filter(Character::isLetter).count();
    }

    private int countSentences() {
        return text.split("[.!?]").length;
    }

    private String findLongestWord() {
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
        return fileName;
    }

    public String getText() {
        return text;
    }

    public int getNumberOfVowels() {
        return numberOfVowels;
    }

    public int getNumberOfConsonants() {
        return numberOfConsonants;
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public String getLongestWord() {
        return longestWord;
    }

    @Override
    public String toString() {
        return "File: " + fileName + "\n" +
                "Vowels: " + numberOfVowels + "\n" +
                "Consonants: " + numberOfConsonants + "\n" +
                "Letters: " + numberOfLetters + "\n" +
                "Sentences: " + numberOfSentences + "\n" +
                "Longest Word: " + longestWord;
    }
}
