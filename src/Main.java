import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
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

        // printing
        for (String universe : universes.keySet()) {
            System.out.println("Universe: " + universe);
            List<Map<String, Object>> aliens = universes.get(universe);

            for (Map<String, Object> alien : aliens) {
                System.out.println("ID: " + alien.get("id") + "; Species: " + alien.get("species"));
                System.out.println("Alien Data: ");
                System.out.println("  Planet: " + alien.get("planet"));
                System.out.println("  isHumanoid: " + alien.get("isHumanoid"));
                System.out.println("  Age: " + alien.get("age"));
                System.out.println("  Traits: " + alien.get("traits"));
                System.out.println();
            }

            System.out.println("---------------------");
        }
    }
}
