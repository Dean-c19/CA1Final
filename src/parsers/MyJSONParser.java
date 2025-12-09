package parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileInputStream;


import entities.Emission;

public class MyJSONParser {

  // holds json file to be parsed
    private File jsonFile;
    public MyJSONParser(File jsonFile) {
        this.jsonFile = jsonFile;
    }

// holds the entire of whats in the json file to a string
    private String jsonString = "";
// this then parses the json file and merges the data into a emissions map that already exists
    public int parseAndMerge(Map<String, Emission> emissionMap) throws IOException, ParseException {
        // this should use this.jsonFile and convert to json string
        InputStream is = new FileInputStream(this.jsonFile);
// error handling
        if (is == null) {
            throw new IOException("Could not find GreenhouseGasEmissions2025.json in resources");
        }
        // the scanner reads the file
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            jsonString += scanner.nextLine(); // for converting each line to jsonstring
        }
        scanner.close();
        // prints the raw JSON string to the console for debugging
        System.out.println("JSON from file as a String");
        System.out.println(jsonString);
    // converts the json string into objs
        JSONParser parser = new JSONParser();
        JSONObject jobj = (JSONObject) parser.parse(jsonString);// parse string into a Json obj

        // this then gets the emissions array from the root obj
        JSONArray jsonArray = (JSONArray) jobj.get("Emissions");
        // print for debugging
        System.out.println("JSON as a JSONArray = Emissions collection");
        System.out.println(jsonArray);
        // tracks how many valid jsons were processed
        int jsonRecordsCount = 0;

        //loop through each emission
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jo1 = (JSONObject) jsonArray.get(i);

            String category = (String) jo1.get("Category");
            String unit = (String) jo1.get("Gas Units");
            Object valueObj = jo1.get("Value");

            if (category == null || unit == null || valueObj == null) {
                continue;
            }

            double actualValue;
            // if the value is not a valid number then skip it
            try {
                actualValue = Double.parseDouble(valueObj.toString());
            } catch (NumberFormatException e) {
                continue;
            }

            if (actualValue <= 0) {
                continue;
            }

            jsonRecordsCount++;

            // builds the key used in the map so it helps find the same emission from the xml
            String key = category.trim() + "||" + unit.trim();

            // check if emission exists and if not cerate a new one
            Emission emission = emissionMap.get(key);

            if (emission == null) {
                emission = new Emission();
                emission.setCategory(category.trim());
                emission.setUnit(unit.trim());
                emission.setPredictedValue(0.0);
                emissionMap.put(key, emission);
            }

            emission.setActualValue(actualValue);

        }

        return jsonRecordsCount;
    }
}