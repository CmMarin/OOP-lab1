import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class jsonParser {

    public Map<String, List<Map<String, Object>>> classifyAliens(List<String> filePaths, AliensClassification alienClassifier, Universes universeSelector) {


        Map<String, List<Map<String, Object>>> universes = new HashMap<>();

        // loop through all files
        for (String filePath : filePaths) {
            // parse and classify per file
            Map<String, List<Map<String, Object>>> currentFileUniverses = classifyAliensFromSingleFile(filePath, alienClassifier, universeSelector);

            // merge in the universe map
            for (String universe : currentFileUniverses.keySet()) {
                universes.putIfAbsent(universe, new ArrayList<>());
                universes.get(universe).addAll(currentFileUniverses.get(universe));  //merging
            }
        }

        return universes;
    }

    // classify per file
    private Map<String, List<Map<String, Object>>> classifyAliensFromSingleFile(String filePath, AliensClassification alienClassifier, Universes universeSelector) {

        Map<String, List<Map<String, Object>>> universes = new HashMap<>();

        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader(filePath);
            JSONObject jsonData = (JSONObject) parser.parse(fileReader);
            JSONArray data = (JSONArray) jsonData.get("data");

            for (Object obj : data) {
                JSONObject alienData = (JSONObject) obj;

                // alien details
                Long id = (Long) alienData.getOrDefault("id", -1L);
                Boolean isHumanoid = (Boolean) alienData.getOrDefault("isHumanoid", false);
                String planet = (String) alienData.getOrDefault("planet", "Unknown Planet");
                JSONArray traits = (JSONArray) alienData.get("traits");
                Long age = (Long) alienData.getOrDefault("age", -1L);

                String speciesName = alienClassifier.classifyAlien(id, planet, age, traits, isHumanoid);

                String universe = universeSelector.getUniverse(speciesName);

                Map<String, Object> alienDetails = new HashMap<>();
                alienDetails.put("id", id);
                alienDetails.put("species", speciesName);
                alienDetails.put("planet", planet);
                alienDetails.put("isHumanoid", isHumanoid);
                alienDetails.put("age", age);
                alienDetails.put("traits", traits);

                universes.putIfAbsent(universe, new ArrayList<>());
                universes.get(universe).add(alienDetails);
            }

        } catch (IOException e) {
            System.out.println("Error reading the JSON file.");
        } catch (ParseException e) {
            System.out.println("Error parsing the JSON file.");
        }

        return universes;
    }
}
