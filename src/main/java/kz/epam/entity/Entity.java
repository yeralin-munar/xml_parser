package kz.epam.entity;

/**
 * Created by munar on 2/3/17.
 */

public abstract class Entity {
    private String name;

    public Entity (String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
