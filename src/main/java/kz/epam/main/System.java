package kz.epam.main;

import kz.epam.entity.Entity;

import java.util.ArrayList;

/**
 * Created by munar on 2/3/17.
 */
public class System extends Entity{
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    private ArrayList<Star> stars = new ArrayList<Star>();

    public System(String name){
        super(name);
    }

    public String getName(){
        return super.getName();
    }

    public void addPlanet(Planet planet){
        planets.add(planet);
    }

    public void addPlanet(String name){
        planets.add(new Planet(name));
    }

    public void addStar(Star star){
        stars.add(star);
    }

    public void addStar(String name){
        stars.add(new Star(name));
    }

    public ArrayList<Planet> getPlanets(){
        return planets;
    }

    public ArrayList<Star> getStars(){
        return stars;
    }

    public Planet getPlanetByName(String name){
        for (Planet planet: planets){
            if (planet.getName().equals(name)){
                return planet;
            }
        }
        return null;
    }

    public Star getStarByName(String name){
        for (Star star: stars){
            if (star.getName().equals(name)){
                return star;
            }
        }
        return null;
    }

    public void removePlanetByName(String name){
        Planet planet = getPlanetByName(name);
        if (planet != null){
            planets.remove(planet);
        }
    }

    public void removeStarByName(String name){
        Star star = getStarByName(name);
        if (star != null){
            stars.remove(star);
        }
    }

    @Override
    public boolean equals(Object o){
        System other = (System) o;

        if (!this.getName().equals(other.getName()))
            return false;
        if ((this.getStars() == null && other.getStars() != null) ||
                (this.getStars() != null && other.getStars() == null) ||
                (this.getPlanets() == null && other.getPlanets() != null) ||
                (this.getPlanets() != null && other.getPlanets() == null)
                )
            return false;

         if (this.getStars().size() != other.getStars().size() &&
                 this.getPlanets().size() != other.getPlanets().size())
            for (int i = 0; i < this.getStars().size(); i ++){
                if (!this.getStars().get(i).equals(other.getStars().get(i)))
                    return false;
            }
            for (int i = 0; i < this.getPlanets().size(); i ++){
                if (!this.getPlanets().get(i).equals(other.getPlanets().get(i)))
                    return false;
            }

        return true;
    }

    @Override
    public String toString(){
        return "" +
                "***********Система "+getName()+"***********"+
                "\n|Количество звезд: "+ stars.size()+
                "\n|Количество планет: "+ planets.size()+
                "\n*** Звезды: "+ stars+
                "\n*** Планеты: "+planets;
    }
}
