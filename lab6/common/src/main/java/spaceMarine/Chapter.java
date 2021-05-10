package spaceMarine;

import java.io.Serializable;
import java.util.Objects;

/**
 * Chapter class
 */
public class Chapter implements Serializable {
    private static final long serialVersionUID = -5037484895546167299L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String world; //Поле может быть null

    public Chapter(String name, String world) {
        this.name = name;
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.name) &&
                Objects.equals(world, chapter.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, world);
    }

    @Override
    public String toString() {
        return String.format("Chapter(%s;%s)", name, world);

    }
}