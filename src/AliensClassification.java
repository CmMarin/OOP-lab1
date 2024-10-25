import org.json.simple.JSONArray;
import java.util.*;

public class AliensClassification {

    private Map<String, Map<String, Map<String, Object>>> classificationData;

    public AliensClassification() {

        classificationData = new HashMap<>();

        // StarWars Universe
        Map<String, Map<String, Object>> starWarsSpecies = new HashMap<>();
        Map<String, Object> wookieCriteria = new HashMap<>();
        wookieCriteria.put("isHumanoid", false);
        wookieCriteria.put("planet", "Kashyyk");
        wookieCriteria.put("age", Arrays.asList(0, 400));
        wookieCriteria.put("traits", Arrays.asList("HAIRY", "TALL"));
        starWarsSpecies.put("Wookie", wookieCriteria);

        Map<String, Object> ewokCriteria = new HashMap<>();
        ewokCriteria.put("isHumanoid", false);
        ewokCriteria.put("planet", "Endor");
        ewokCriteria.put("age", Arrays.asList(0, 60));
        ewokCriteria.put("traits", Arrays.asList("SHORT", "HAIRY"));
        starWarsSpecies.put("Ewok", ewokCriteria);

        classificationData.put("StarWars Universe", starWarsSpecies);

        // Marvel Universe
        Map<String, Map<String, Object>> marvelSpecies = new HashMap<>();
        Map<String, Object> asgardianCriteria = new HashMap<>();
        asgardianCriteria.put("isHumanoid", true);
        asgardianCriteria.put("planet", "Asgard");
        asgardianCriteria.put("age", Arrays.asList(0, 5000));
        asgardianCriteria.put("traits", Arrays.asList("BLONDE", "TALL"));
        marvelSpecies.put("Asgardian", asgardianCriteria);

        classificationData.put("Marvel Universe", marvelSpecies);

        // Hitchhiker's Universe
        Map<String, Map<String, Object>> hitchhikerSpecies = new HashMap<>();
        Map<String, Object> betelgeusianCriteria = new HashMap<>();
        betelgeusianCriteria.put("isHumanoid", true);
        betelgeusianCriteria.put("planet", "BETELGEUSE");
        betelgeusianCriteria.put("age", Arrays.asList(0, 100));
        betelgeusianCriteria.put("traits", Arrays.asList("EXTRA_ARMS", "EXTRA_HEAD"));
        hitchhikerSpecies.put("Betelgeusian", betelgeusianCriteria);

        Map<String, Object> vogonCriteria = new HashMap<>();
        vogonCriteria.put("isHumanoid", false);
        vogonCriteria.put("planet", "Vogsphere");
        vogonCriteria.put("age", Arrays.asList(0, 200));
        vogonCriteria.put("traits", Arrays.asList("GREEN", "BULKY"));
        hitchhikerSpecies.put("Vogons", vogonCriteria);

        classificationData.put("Hitchhiker's Universe", hitchhikerSpecies);

        // LOTR Universe
        Map<String, Map<String, Object>> lotrSpecies = new HashMap<>();
        Map<String, Object> elfCriteria = new HashMap<>();
        elfCriteria.put("isHumanoid", true);
        elfCriteria.put("planet", "Earth");
        elfCriteria.put("age", null);
        elfCriteria.put("traits", Arrays.asList("BLONDE", "POINTY_EARS"));
        lotrSpecies.put("Elf", elfCriteria);

        Map<String, Object> dwarfCriteria = new HashMap<>();
        dwarfCriteria.put("isHumanoid", true);
        dwarfCriteria.put("planet", "Earth");
        dwarfCriteria.put("age", Arrays.asList(0, 200));
        dwarfCriteria.put("traits", Arrays.asList("SHORT", "BULKY"));
        lotrSpecies.put("Dwarf", dwarfCriteria);

        classificationData.put("LOTR Universe", lotrSpecies);
    }

    public String classifyAlien(Long id, String planet, Long age, JSONArray traits, Boolean isHumanoid) {
        Map<String, Integer> speciesScoreMap = new HashMap<>();
        List<String> matchingSpecies = new ArrayList<>();

        // loop through all universes
        for (Map.Entry<String, Map<String, Map<String, Object>>> universeEntry : classificationData.entrySet()) {
            Map<String, Map<String, Object>> speciesData = universeEntry.getValue();

            for (Map.Entry<String, Map<String, Object>> speciesEntry : speciesData.entrySet()) {
                String species = speciesEntry.getKey();
                Map<String, Object> criteria = speciesEntry.getValue();

                // checking for possible match
                int score = evaluateSpeciesMatch(planet, age, traits, isHumanoid, criteria);


                if (score > 0) {
                    speciesScoreMap.put(species, score);
                    matchingSpecies.add(species);
                }
            }
        }

        return selectBestMatch(speciesScoreMap, matchingSpecies, traits);
    }

    //  method to evaluate the match for each species based on planet, age, traits, and isHumanoid
    private int evaluateSpeciesMatch(String planet, Long age, JSONArray traits, Boolean isHumanoid, Map<String, Object> criteria) {
        int score = 0;

        // planet match
        if (criteria.containsKey("planet") && criteria.get("planet").equals(planet)) {
            score += 3;
        }

        // age match
        if (criteria.containsKey("age")) {
            List<Integer> ageRange = (List<Integer>) criteria.get("age");
            if (ageRange != null && (age >= ageRange.get(0) && age <= ageRange.get(1))) {
                score += 1;
            }
        }

        // humanoid or not
        if (criteria.containsKey("isHumanoid") && criteria.get("isHumanoid").equals(isHumanoid)) {
            score += 2;
        }

        // traits
        if (criteria.containsKey("traits")) {
            List<String> requiredTraits = (List<String>) criteria.get("traits");
            if (traits != null) {
               // if all traits from the classification file are met, we'll give a higher score, if only some then a lower score
                if (traits.containsAll(requiredTraits)) {
                    score += 4;
                } else if (!Collections.disjoint(traits, requiredTraits)) {
                    score += 2;
                }
            }
        }

        return score;
    }

    private String selectBestMatch(Map<String, Integer> speciesScoreMap, List<String> matchingSpecies, JSONArray traits) {
       //if one species matches
        if (matchingSpecies.size() == 1) {
            return matchingSpecies.get(0);
        }

        // If multiple species match, get the highest score
        return speciesScoreMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown Species");
    }
}