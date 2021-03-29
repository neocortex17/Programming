package spaceMarine;

import exceptions.InvalidArgumentTypeException;

import java.util.Formatter;

/**
 * Chapter class
 */
public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String world; //Поле может быть null

    public Chapter(String name, String world) throws InvalidArgumentTypeException {
        setName(name);
        setWorld(world);
    }

    public Chapter() {
    }

    public void setName(String name) throws InvalidArgumentTypeException {
        if (name!=null && !name.equals("")){
            this.name = name;
        }else {
            throw new InvalidArgumentTypeException("Invalid value entered for the chapter name field!");
        }
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("%s,%s", name, world);
        return formatter.toString();
    }
}