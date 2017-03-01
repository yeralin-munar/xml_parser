package kz.epam.main;

import kz.epam.entity.Entity;

/**
 * Created by munar on 2/3/17.
 */
public class Star extends Entity{

    public Star(String name){
        super(name);
    }

    @Override
    public boolean equals(Object o){
        Star other = (Star) o;

        return this.getName().equals(other.getName());
    }

    @Override
    public String toString(){
        return getName();
    }
}
