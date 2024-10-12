import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class jsonParser {

    public Map<String, List<JSONObject>> classifyAliens(String filePath) {
        //storing here for hte moment
        Map<String, List<JSONObject>> universes = new HashMap<>();


        JSONParser parser = new JSONParser();

        try {
            // Read json
            FileReader fileReader = new FileReader(filePath);


            JSONObject jsonData = (JSONObject) parser.parse(fileReader);
            JSONArray data = (JSONArray) jsonData.get("data");

            for (Object obj : data) {
                JSONObject alienData = (JSONObject) obj;

                //data
                Long id = (Long) alienData.getOrDefault("id", -1L);
                Boolean isHumanoid = (Boolean) alienData.getOrDefault("isHumanoid", false);
                String planet = (String) alienData.getOrDefault("planet", "Unknown Planet");
                JSONArray traits = (JSONArray) alienData.get("traits");
                Long age = (Long) alienData.getOrDefault("age", -1L);


                String universe = classifyAliens(id, planet, age, traits, isHumanoid);


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

    public String classifyAliens(Long id, String planet, Long age, JSONArray traits, boolean isHumanoid) {
        //classification rules

        if (!isHumanoid && "Endor".equals(planet) ||
                "Kashyyk".equals(planet) && age > 0 && age < 400 &&
                        traits != null && traits.contains("HAIRY")) {
            return "StarWars Universe";
        }

        if (isHumanoid && "Asgard".equals(planet) && age > 0 && age < 5000 &&
                traits != null && traits.contains("BLONDE") && traits.contains("TALL")) {
            return "Marvel Universe";
        }

        if ("BETELGEUSE".equals(planet) && age > 0 && age < 100 &&
                traits != null && (traits.contains("EXTRA_ARMS") && traits.contains("EXTRA_HEAD") ||
                traits.contains("GREEN") && traits.contains("BULKY"))) {
            return "Hitchhiker's Universe";
        }

        if (isHumanoid && "EARTH".equals(planet) && age > 0 &&
                traits != null && (traits.contains("BLONDE") && traits.contains("POINTY_EARS") ||
                traits.contains("SHORT") && traits.contains("BULKY"))) {
            return "LOTR Universe";
        }


        //if the system doesnt know, send to manual check (which idk when it will come)
        return "No Known Universe, sent to manual inspection";
    }
}
