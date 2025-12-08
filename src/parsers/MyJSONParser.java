package parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.Emission;

public class MyJSONParser {

    private String jsonString = "";
    private List<Emission> emissions = new ArrayList<>();

    public MyJSONParser() throws IOException, ParseException {

        InputStream is = getClass().getClassLoader().getResourceAsStream("emissions.json");

        if (is == null) {
            throw new IOException("Could not find emissions.json in resources");
        }

        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            jsonString += scanner.nextLine();
        }

        scanner.close();

        System.out.println("JSON from file as a String");
        System.out.println(jsonString);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonString);
        System.out.println("JSON as a Java Object");
        System.out.println(obj);


        JSONObject jobj = (JSONObject) parser.parse(jsonString);

        JSONArray jsonArray = (JSONArray) jobj.get("Emissions");
        System.out.println("JSON as a JSONArray = Emissions collection");
        System.out.println(jsonArray);

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

            System.out.println("JSON Objects under Emissions array parsed");
            System.out.println("Category: " + category);
            System.out.println("Gas Units: " + unit);
            System.out.println("Actual Value: " + actualValue);
            System.out.println("Predicted Value: 0");

            Emission emission = new Emission();
            emission.setCategory(category.trim());
            emission.setUnit(unit.trim());
            emission.setActualValue(actualValue);
            emission.setPredictedValue(0.0);

            emissions.add(emission);
        }
    }

    public List<Emission> getEmissions() {
        return emissions;
    }

}