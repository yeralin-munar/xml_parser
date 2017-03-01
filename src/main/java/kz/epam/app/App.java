package kz.epam.app;

import kz.epam.xml.MyDOMParser;
import kz.epam.xml.MySAXHandler;
import kz.epam.xml.MySTAXParser;

/**
 * @author Yeralin Munar
 *         date: 3/1/17
 */
public class App {
    public static void main(String[] args) {
        String fileName = "universe.xml";
        Space saxSpace = new MySAXHandler().getSpace(fileName);

        Space staxSpace = new MySTAXParser().getSpace(fileName);

        Space domSpace = new MyDOMParser().getSpace(fileName);
        System.out.println(domSpace);

        System.out.println(saxSpace.equals(staxSpace));
        System.out.println(staxSpace.equals(domSpace));
        System.out.println(saxSpace.equals(domSpace));
    }
}
