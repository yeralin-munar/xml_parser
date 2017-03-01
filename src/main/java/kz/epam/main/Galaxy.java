package kz.epam.main;

import kz.epam.entity.Entity;

import java.util.ArrayList;

/**
 * Created by munar on 2/3/17.
 */
public class Galaxy extends Entity{
    private ArrayList<System> systems = new ArrayList<System>();

    public Galaxy(String name){
        super(name);
    }

    public String getName(){
        return super.getName();
    }

    public void addSystem(System system){
        systems.add(system);
    }

    public void addSystem(String name){
        systems.add(new System(name));
    }

    public void addSystems(ArrayList<System> systems){
        this.systems = systems;
    }

    public ArrayList<System> getSystems(){
        return systems;
    }

    public System getSystemByName(String name){
        for (System system: systems){
            if (system.getName().equals(name)){
                return system;
            }
        }
        return null;
    }

    public void removeSystemByName(String name){
        System system = getSystemByName(name);
        if (system != null){
            systems.remove(system);
        }
    }

    @Override
    public boolean equals(Object o){
        Galaxy other = (Galaxy) o;

        if (!this.getName().equals(other.getName()))
            return false;
        if ((this.getSystems() == null && other.getSystems() != null)
                || (this.getSystems() != null && other.getSystems() == null))
            return false;
        if (this.getSystems().size() != other.getSystems().size())
            return false;
        for (int i = 0; i < this.getSystems().size(); i++){
            if (!this.getSystems().equals(other.getSystems()))
                return false;
        }

        return true;
    }

    @Override
    public String toString(){
        return "\n===========Галактика "+getName()+"===========\n"+
                "Количество систем: "+systems.size()+"\n"+
                systems+"\n";
    }
}
