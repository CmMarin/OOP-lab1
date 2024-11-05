import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class View {

    // Mapping universes to file names
    private final Map<String, String> universeFileMap = Map.of(
            "Hitchhiker's Universe", "hitchhiker.json",
            "LOTR Universe", "rings.json",
            "Marvel Universe", "marvel.json",
            "StarWars Universe", "starwars.json"
    );

    // method to write to files
    public void writeToFiles(Map<String, List<Map<String, Object>>> universes) {
        for (String universe : universes.keySet()) {
            JSONObject universeObject = new JSONObject();
            universeObject.put("name", universe);

            JSONArray individualsArray = new JSONArray();
            List<Map<String, Object>> aliens = universes.get(universe);

            for (Map<String, Object> alien : aliens) {
                JSONObject individual = new JSONObject();
                individual.put("id", alien.get("id"));
                individual.put("isHumanoid", alien.get("isHumanoid"));
                individual.put("age", alien.get("age"));
                individual.put("traits", alien.get("traits"));

                individualsArray.add(individual);
            }

            universeObject.put("individuals", individualsArray);

            // write data per universe or default to unknown (error handling)
            String filename = universeFileMap.getOrDefault(universe, "unknown_universe.json");
            writeToFile(filename, universeObject);
        }
    }

    // writing to the file, given the location of the directory + pretty printing
    private void writeToFile(String filename, JSONObject universeData) {
        try (FileWriter file = new FileWriter("output/" + filename)) {
            file.write(prettyPrintJson(universeData.toJSONString(), 4));
            file.flush();
            System.out.println("Written data for " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // pretty printing of jsons, method may have been inspired from the internet
    private String prettyPrintJson(String jsonString, int indentSize) {
        StringBuilder prettyJson = new StringBuilder();
        String indent = " ".repeat(indentSize);
        boolean inQuotes = false;
        int level = 0;

        for (char charFromJson : jsonString.toCharArray()) {
            switch (charFromJson) {
                case '"':
                    inQuotes = !inQuotes;
                    prettyJson.append(charFromJson);
                    break;
                case '{':
                case '[':
                    prettyJson.append(charFromJson);
                    if (!inQuotes) {
                        prettyJson.append("\n").append(indent.repeat(++level));
                    }
                    break;
                case '}':
                case ']':
                    if (!inQuotes) {
                        prettyJson.append("\n").append(indent.repeat(--level));
                    }
                    prettyJson.append(charFromJson);
                    break;
                case ',':
                    prettyJson.append(charFromJson);
                    if (!inQuotes) {
                        prettyJson.append("\n").append(indent.repeat(level));
                    }
                    break;
                case ':':
                    prettyJson.append(charFromJson);
                    if (!inQuotes) {
                        prettyJson.append(" ");
                    }
                    break;
                default:
                    prettyJson.append(charFromJson);
            }
        }

        return prettyJson.toString();
    }
}
