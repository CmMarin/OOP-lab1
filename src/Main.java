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

            for (JSONObject alien : aliens) {
                System.out.println("Alien: " + alien);
            }

            System.out.println("---------------------");
        }
    }
}
