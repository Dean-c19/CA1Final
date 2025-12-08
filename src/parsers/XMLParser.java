package parsers;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import entities.Emission;

public class XMLParser {

    public List<Emission> parse(File xmlFile) throws
            IOException, ParserConfigurationException, SAXException {

        List<Emission> emissionsList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());


        NodeList rows = doc.getElementsByTagName("Row");

        for (int i = 0; i < rows.getLength(); i++) {

            Node rowNode = rows.item(i);

            if (rowNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) rowNode;


                Node categoryNode = elem.getElementsByTagName("Category__1_3").item(0);
                String category = categoryNode != null ? categoryNode.getTextContent().trim() : null;

                Node yearNode = elem.getElementsByTagName("Year").item(0);
                String yearStr = yearNode != null ? yearNode.getTextContent().trim() : null;


                Node scenarioNode = elem.getElementsByTagName("Scenario").item(0);
                String scenario = scenarioNode != null ? scenarioNode.getTextContent().trim() : null;

                Node unitNode = elem.getElementsByTagName("Gas___Units").item(0);
                String unit = unitNode != null ? unitNode.getTextContent().trim() : null;


                Node valueNode = elem.getElementsByTagName("Value").item(0);
                String valueStr = valueNode != null ? valueNode.getTextContent().trim() : null;

                if (category == null || unit == null || valueStr == null) {
                    continue;
                }

                double predictedValue;
                try {
                    predictedValue = Double.parseDouble(valueStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (predictedValue <= 0) {
                    continue;
                }

                if (scenario == null || !scenario.equalsIgnoreCase("WEM")) {
                    continue;
                }


                if (yearStr != null) {
                    try {
                        int year = Integer.parseInt(yearStr);
                        if (year != 2023) {
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } else {
                    continue;
                }

                Emission emission = new Emission();
                emission.setCategory(category);
                emission.setUnit(unit);
                emission.setPredictedValue(predictedValue);
                emission.setActualValue(0.0);

                System.out.println("Parsed XML Emission:");
                System.out.println("  Category: " + category);
                System.out.println("  Unit: " + unit);
                System.out.println("  Predicted: " + predictedValue);
                System.out.println("  Actual: 0");

                emissionsList.add(emission);
            }
        }

        return emissionsList;
    }
}