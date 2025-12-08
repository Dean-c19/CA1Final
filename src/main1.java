import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Emission;
import parsers.MyJSONParser;
import parsers.XMLParser;

public class main1 {

    private static String buildKey(String category, String unit) {
        if (category == null) category = "";
        if (unit == null) unit = "";
        return category.trim() + "||" + unit.trim();
    }

    public static void main(String[] args) {

        try {
            XMLParser xmlParser = new XMLParser();

            File xmlFile = new File("src/main/resources/emissions.xml");

            List<Emission> xmlEmissions = xmlParser.parse(xmlFile);

            System.out.println("Predicted emissions (from XML) count: " + xmlEmissions.size());

            Map<String, Emission> emissionMap = new HashMap<>();
            for (Emission e : xmlEmissions) {
                String key = buildKey(e.getCategory(), e.getUnit());
                emissionMap.put(key, e);
            }


            MyJSONParser jsonParser = new MyJSONParser();
            int jsonCount = jsonParser.parseAndMerge(emissionMap);

            System.out.println("Actual emissions (from JSON) records processed: " + jsonCount);

            System.out.println(" All Emissions After Merge (Predicted + Actual)");

            for (Emission e : emissionMap.values()) {
                System.out.println("Category: " + e.getCategory());
                System.out.println("Unit: " + e.getUnit());
                System.out.println("Predicted: " + e.getPredictedValue());
                System.out.println("Actual: " + e.getActualValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
