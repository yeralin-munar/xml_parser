package kz.epam.xml;

import kz.epam.app.Space;
import kz.epam.main.*;
import kz.epam.main.System;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 3/1/17
 */
public class MyDOMParser {

    private static List<Universe> universes = new ArrayList<>();
    private StringBuilder data;
    private int current = 0;
    private String galaxy = "";
    private String system = "";

    private void parse(String file){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(file));

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("universe");

            for (int i = 0; i < nodeList.getLength(); i++){
                Element universe = (Element) nodeList.item(i);

                String universeName = universe.getAttribute("name");
                Universe universeClass = new Universe(universeName);

                NodeList galaxies = universe.getElementsByTagName("galaxy");
                for (int g = 0; g < galaxies.getLength(); g++){
                    Element galaxy = (Element) galaxies.item(g);

                    String galaxyName = galaxy.getAttribute("name");
                    Galaxy galaxyClass = new Galaxy(galaxyName);

                    NodeList systems = galaxy.getElementsByTagName("system");
                    for (int s = 0; s < systems.getLength(); s++){
                        Element system = (Element) systems.item(s);

                        String systemName = system.getAttribute("name");
                        System systemClass = new System(systemName);

                        NodeList stars = system.getElementsByTagName("star");
                        for (int st = 0; st < stars.getLength(); st++){
                            Element star = (Element) stars.item(st);

                            String starName = star.getTextContent();
                            Star starClass = new Star(starName);

                            systemClass.addStar(starClass);
                        }

                        NodeList planets = system.getElementsByTagName("planet");
                        for (int p = 0; p < planets.getLength(); p++){
                            Element planet = (Element) planets.item(p);

                            String planetName = planet.getTextContent();
                            Planet planetClass = new Planet(planetName);

                            systemClass.addPlanet(planetClass);
                        }

                        galaxyClass.addSystem(systemClass);

                    }
                    universeClass.addGalaxy(galaxyClass);
                    universes.add(universeClass);
                }
                //System.out.println(getChild(universe, "system").getElementsByTagName("star").getLength());

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private static Element getChild(Element parent, String childName){
        NodeList nodeList = parent.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);

        return child;
    }

    public Space getSpace(String file){
        parse(file);

        Space space = new Space();
        space.setUniverses(universes);
        return space;
    }


}
