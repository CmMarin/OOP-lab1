import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("Papers please...\n" );

        AliensClassification alienClassifier = new AliensClassification();
        Universes universeSelector = new Universes();
        jsonParser parser = new jsonParser();

        Map<String, List<JSONObject>> universes = parser.classifyAliens("resources/test_input.json", alienClassifier, universeSelector);

        //printing
        for (String universe : universes.keySet()) {
            System.out.println("Universe: " + universe);
            List<JSONObject> aliens = universes.get(universe);

            for (Map<String, Object> alien : aliens) {
                System.out.println("ID: " + alien.get("id"));
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
