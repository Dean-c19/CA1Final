import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.EmissionDAO;
import entities.Emission;
import parsers.MyJSONParser;
import parsers.XMLParser;

// main class that reads the files and merges both of them together then saves the esults into the database
public class main1 {

    // builds a unique key using the category and unit so each emission is unique during the merge process
    private static String buildKey(String category, String unit) {
        if (category == null) category = "";
        if (unit == null) unit = "";
        // format for the key
        return category.trim() + "||" + unit.trim();
    }

    public static void main(String[] args) {

        try {
            EmissionDAO emissionDAO = new EmissionDAO();
            XMLParser xmlParser = new XMLParser();

            File xmlFile = new File("resources/emissions.xml");
            File jsonFile = new File("resources/GreenhouseGasEmissions2025.json");

            // parse the cml file into a list of emission objs
            List<Emission> xmlEmissions = xmlParser.parse(xmlFile);

            //print the no of predicted emissions
            System.out.println("Predicted emissions (from XML) count: " + xmlEmissions.size());

            // create map so the key is category || unit
            Map<String, Emission> emissionMap = new HashMap<>();
            // loop to go through all predicted emmisions
            for (Emission e : xmlEmissions) {
                String key = buildKey(e.getCategory(), e.getUnit());
                emissionMap.put(key, e);
            }

            // parse and merge the actual emissions from the json file
            MyJSONParser jsonParser = new MyJSONParser(jsonFile);
            int jsonCount = jsonParser.parseAndMerge(emissionMap);

            System.out.println("Actual emissions (from JSON) records processed: " + jsonCount);

            // then save and merge emissions into the database

            System.out.println(" All Emissions After Merge (Predicted + Actual)");

            //loop through all merged emissions and only save emissions that contain a predicted and actual value
            for (Emission e : emissionMap.values()) {
                if(e.getPredictedValue() != 0.00 && e.getActualValue() != 0.00) {
                    emissionDAO.persist(e);
                }
            }

            // get all the emissions from the database and print them
            for(Emission e: emissionDAO.getAllEmissions()) {
                System.out.println(e.toString());
            }
            // incase there is errors during parsing, merging or database storing
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
