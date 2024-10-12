import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class jsonParser {

    public Map<String, List<JSONObject>> classifyAliens(String filePath, AliensClassification alienClassifier, Universes universeSelector) {

        // store universe with aliens
        Map<String, List<JSONObject>> universes = new HashMap<>();

        //init parser
        JSONParser parser = new JSONParser();

        try {

            FileReader fileReader = new FileReader(filePath);

            JSONObject jsonData = (JSONObject) parser.parse(fileReader);
            JSONArray data = (JSONArray) jsonData.get("data");

            // loop through all aliesn
            for (Object obj : data) {
                JSONObject alienData = (JSONObject) obj;

                // get data
                Long id = (Long) alienData.getOrDefault("id", -1L);
                Boolean isHumanoid = (Boolean) alienData.getOrDefault("isHumanoid", false);
                String planet = (String) alienData.getOrDefault("planet", "Unknown Planet");
                JSONArray traits = (JSONArray) alienData.get("traits");
                Long age = (Long) alienData.getOrDefault("age", -1L);

                String speciesClassification = alienClassifier.classifyAlien(id, planet, age, traits, isHumanoid);

                String universe = universeSelector.getUniverse(speciesClassification);

                universes.putIfAbsent(universe, new ArrayList<>());
                universes.get(universe).add(alienData);
            }

        } catch (IOException e) {
            System.out.println("Error reading the JSON file.");
        } catch (ParseException e) {
            System.out.println("Error parsing the JSON file.");
        }

        return universes;
    }
}
