import java.util.HashMap;
import java.util.Map;

public class Universes {

    private final Map<String, String> universeMap = new HashMap<>();

    public Universes() {
        // populating the universes so we know what to choose
        universeMap.put("Wookie", "StarWars Universe");
        universeMap.put("Ewok", "StarWars Universe");
        universeMap.put("Asgardian", "Marvel Universe");
        universeMap.put("Betelgeusian", "Hitchhiker's Universe");
        universeMap.put("Vogons", "Hitchhiker's Universe");
        universeMap.put("Elf", "LOTR Universe");
        universeMap.put("Dwarf", "LOTR Universe");
        universeMap.put("Unknown Species", "No Known Universe, sent to manual inspection");
    }

    // get the universe that matched
    public String getUniverse(String speciesClassification) {
        return universeMap.getOrDefault(speciesClassification, "No Known Universe, sent to manual inspection");
    }
}
