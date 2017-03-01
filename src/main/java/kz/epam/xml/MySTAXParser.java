package kz.epam.xml;

import kz.epam.app.Space;
import kz.epam.main.Universe;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 3/1/17
 */
public class MySTAXParser {

    private enum SpaceEnum {UNIVERSES, UNIVERSE, GALAXY, SYSTEM, STAR, PLANET, NAME};

    private static List<Universe> universes = new ArrayList<Universe>();
    private StringBuilder data;
    private int current = 0;
    private String galaxy = "";
    private String system = "";

    private void parse(InputStream inputStream){
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            process(reader);
        } catch (XMLStreamException e){
            e.printStackTrace();
        }
    }

    private void process(XMLStreamReader reader){
        String name;

        try {
            while (reader.hasNext()){
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        SpaceEnum spaceEnum = SpaceEnum.valueOf(name.toUpperCase());
                        spaceStartElement(spaceEnum, reader);
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        charachter(reader.getText());
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        spaceEnum = SpaceEnum.valueOf(reader.getLocalName().toUpperCase());
                        spaceEndElement(spaceEnum);
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void spaceStartElement(SpaceEnum spaceEnum, XMLStreamReader reader){
        switch (spaceEnum) {
            case UNIVERSE:
                universes.add(new Universe(reader.
                        getAttributeValue(null, SpaceEnum.NAME.name().toLowerCase())));
                current = universes.size()-1;
                break;

            case GALAXY:
                universes.get(current).
                        addGalaxy(reader.getAttributeValue(null, SpaceEnum.NAME.name().toLowerCase()));
                break;

            case SYSTEM:
                universes.get(current).
                        getGalaxyByName(reader.
                                getAttributeValue(null, SpaceEnum.GALAXY.name().toLowerCase())).
                        addSystem(reader.getAttributeValue(null, SpaceEnum.NAME.name().toLowerCase()));
                break;

            default:
                galaxy = reader.getAttributeValue(null, SpaceEnum.GALAXY.name().toLowerCase());
                system = reader.getAttributeValue(null, SpaceEnum.SYSTEM.name().toLowerCase());
                break;
        }
    }

    private void charachter(String text){
        data = new StringBuilder();
        data.append(text);
    }

    private void spaceEndElement(SpaceEnum spaceEnum){
        switch (spaceEnum){
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
        try {
            parse(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        space.setUniverses(universes);
        return space;
    }

}
