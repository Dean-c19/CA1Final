import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.EmissionDAO;
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
            EmissionDAO emissionDAO = new EmissionDAO();
            XMLParser xmlParser = new XMLParser();

            File xmlFile = new File("resources/emissions.xml");
            File jsonFile = new File("resources/GreenhouseGasEmissions2025.json");

            List<Emission> xmlEmissions = xmlParser.parse(xmlFile);

            System.out.println("Predicted emissions (from XML) count: " + xmlEmissions.size());

            Map<String, Emission> emissionMap = new HashMap<>();
            for (Emission e : xmlEmissions) {
                String key = buildKey(e.getCategory(), e.getUnit());
                emissionMap.put(key, e);
            }


            MyJSONParser jsonParser = new MyJSONParser(jsonFile);
            int jsonCount = jsonParser.parseAndMerge(emissionMap);

            System.out.println("Actual emissions (from JSON) records processed: " + jsonCount);

            System.out.println(" All Emissions After Merge (Predicted + Actual)");

            for (Emission e : emissionMap.values()) {
                if(e.getPredictedValue() != 0.00 && e.getActualValue() != 0.00) {
                    emissionDAO.persist(e);
                }
            }

            for(Emission e: emissionDAO.getAllEmissions()) {
                System.out.println(e.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
