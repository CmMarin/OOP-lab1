import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class jsonParser {

    public void parseJson(String filePath) {

        // initialize paraser
        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader(filePath);

            // typecasting to json obj
            JSONObject jsonData = (JSONObject) parser.parse(fileReader);

            // data array
            JSONArray data = (JSONArray) jsonData.get("data");

            // for loop to get all data
            for (Object obj : data) {
                JSONObject alienData = (JSONObject) obj;


                Long id = (Long) alienData.getOrDefault("id", -1L);
                System.out.println("ID: " + id);


                Boolean isHumanoid = (Boolean) alienData.getOrDefault("isHumanoid", false);
                System.out.println("isHumanoid: " + isHumanoid);

                String planet = (String) alienData.getOrDefault("planet", "Unknown Planet");
                System.out.println("Planet: " + planet);


                Long age = (Long) alienData.getOrDefault("age", 0L);
                System.out.println("Age: " + age);


                JSONArray traits = (JSONArray) alienData.get("traits");
                if (traits != null) {
                    System.out.println("Traits: " + traits);
                } else {
                    System.out.println("Traits: None");
                }

                System.out.println("---------------------");
            }

        } catch (IOException e) {
            System.out.println("Error reading the JSON file.");
        } catch (ParseException e) {
            System.out.println("Error parsing the JSON file.");
        }
    }
}
