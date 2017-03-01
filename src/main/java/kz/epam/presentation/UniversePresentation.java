package kz.epam.presentation;

import kz.epam.main.*;
import kz.epam.main.System;

/**
 * Created by munar on 2/7/17.
 */
public class UniversePresentation {
    private Universe universe;

    public UniversePresentation(Universe universe){
        this.universe = universe;
    }

    @Override
    public String toString(){
        String universeString;

        universeString = "\nВселенная " + universe.getName() +
                "\nКоличество галактик: " + universe.getGalaxies().size();

        for (Galaxy galaxy: universe.getGalaxies()){
            universeString += "\n\n=========== Галактика " + galaxy.getName() +" ==========="+
                    "\nКоличество систем: " + galaxy.getSystems().size();
            for (System system: galaxy.getSystems()){
                universeString += "\n  *********** Система " + system.getName() +" ***********"+
                        "\n    Количество звезд: " + system.getStars().size()+
                        "\n    Количество планет: " + system.getPlanets().size();
                universeString += "\n    *** Звезды: ";
                for (Star star: system.getStars()){
                    universeString += star.getName() + "| ";
                }

                universeString += "\n    *** Планеты: ";
                for (Planet planet: system.getPlanets()){
                    universeString += planet.getName() + "| ";
                }
                universeString += "\n  **********************";
            }
        }

        return universeString;
    }
}
