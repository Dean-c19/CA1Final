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

    // constructor should take in a file type (this is the json file)
    private File jsonFile;
    public MyJSONParser(File jsonFile) {
        this.jsonFile = jsonFile;
    }


    private String jsonString = "";

    public int parseAndMerge(Map<String, Emission> emissionMap) throws IOException, ParseException {
        // this should use this.jsonFile and convert to json string
        InputStream is = new FileInputStream(this.jsonFile);

        if (is == null) {
            throw new IOException("Could not find GreenhouseGasEmissions2025.json in resources");
        }

        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            jsonString += scanner.nextLine();
        }
        scanner.close();

        System.out.println("JSON from file as a String");
        System.out.println(jsonString);

        JSONParser parser = new JSONParser();
        JSONObject jobj = (JSONObject) parser.parse(jsonString);

        JSONArray jsonArray = (JSONArray) jobj.get("Emissions");
        System.out.println("JSON as a JSONArray = Emissions collection");
        System.out.println(jsonArray);

        int jsonRecordsCount = 0;

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jo1 = (JSONObject) jsonArray.get(i);

            String category = (String) jo1.get("Category");
            String unit = (String) jo1.get("Gas Units");
            Object valueObj = jo1.get("Value");

            if (category == null || unit == null || valueObj == null) {
                continue;
            }

            double actualValue;
            try {
                actualValue = Double.parseDouble(valueObj.toString());
            } catch (NumberFormatException e) {
                continue;
            }

            if (actualValue <= 0) {
                continue;
            }

            jsonRecordsCount++;


            String key = category.trim() + "||" + unit.trim();

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