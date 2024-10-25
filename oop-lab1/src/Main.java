import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Papers please...");


        AliensClassification alienClassifier = new AliensClassification();
        Universes universeSelector = new Universes();
        jsonParser parser = new jsonParser();

        // multiple jsons paths
        List<String> filePaths = Arrays.asList(
                "resources/test_input.json",
                "resources/input1.json",
                "resources/input2.json"
        );

        Map<String, List<Map<String, Object>>> universes = parser.classifyAliens(filePaths, alienClassifier, universeSelector);

        // json output
        View view = new View();
        view.writeToFiles(universes);
    }
}
