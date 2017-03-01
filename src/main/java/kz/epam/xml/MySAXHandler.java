package kz.epam.xml;


import kz.epam.app.Space;
import kz.epam.main.Universe;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 3/1/17
 */

public class MySAXHandler extends DefaultHandler{
    private final String  UNIVERSE="universe";
    private final String  GALAXY="galaxy";
    private final String  SYSTEM="system";
    private final String  STAR="star";
    private final String  PLANET="planet";

    private static List<Universe> universes = new ArrayList<Universe>();
    private StringBuilder data;
    private int current = 0;
    private String galaxy = "";
    private String system = "";

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        switch (qName) {
            case UNIVERSE:
                universes.add(new Universe(atts.getValue(0)));
                current = universes.size()-1;
                break;

            case GALAXY:
                universes.get(current).
                        addGalaxy(atts.getValue(0));
                break;

            case SYSTEM:
                universes.get(current).
                        getGalaxyByName(atts.getValue(1)).
                        addSystem(atts.getValue(0));
                break;

            default:
                galaxy = atts.getValue(0);
                system = atts.getValue(1);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data = new StringBuilder();
        data.append(ch, start, length);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        switch (qName){
            case STAR:
                universes.get(current).
                        getGalaxyByName(galaxy).
                        getSystemByName(system).addStar(data.toString());
                break;
            case PLANET:
                universes.get(current).
                        getGalaxyByName(galaxy).
                        getSystemByName(system).addPlanet(data.toString());
                break;
        }
    }

    public Space getSpace(String file){
        Space space = new Space();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler handler = new MySAXHandler();

            saxParser.parse(file, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        space.setUniverses(universes);
        return space;
    }
}
