import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main {
    public static void main(String[] args) {
        System.out.println("Papers, please!");
//new instance for parser
        jsonParser parser = new jsonParser();

        Map<String, List<JSONObject>> universes = parser.classifyAliens("resources/test_input.json");

        // Print out the universes and the aliens in them
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


//commented aliens.java and universe.java for the moment, since i want to re-build those in the next commit