import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main {
    public static void main(String[] args) {
        System.out.println("Papers, please!");
//new instance for parser
        jsonParser parser = new jsonParser();

        parser.parseJson("resources/test_input.json");//locagtion of the file i need to read

    }


}


//commented aliens.java and universe.java for the moment, since i want to re-build those in the next commit