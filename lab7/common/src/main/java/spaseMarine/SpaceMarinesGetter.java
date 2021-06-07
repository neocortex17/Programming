package spaceMarine;

import java.io.Serializable;
import java.util.Objects;

public class SpaceMarinesGetter implements Comparable<SpaceMarinesGetter>, Serializable {
    private static final long serialVersionUID = 5366370939317668205L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private int health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null

    public SpaceMarinesGetter(String name, Coordinates coordinates, int health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter){
        this.name = name;
        this.coordinates = coordinates;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarinesGetter that = (SpaceMarinesGetter) o;
        return health == that.health &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates) &&
                category == that.category &&
                weaponType == that.weaponType &&
                meleeWeapon == that.meleeWeapon &&
                Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, health, category, weaponType, meleeWeapon, chapter);
    }

    @Override
    public String toString() {
        return String.format("SpaceMarinesGetter(name=%s, coordinates=%s, health=%d, category=%s, weaponType=%s," +
                " meleeWeapon=%s, chapter=%s)", name, coordinates, health, category, weaponType, meleeWeapon, chapter);
    }

    @Override
    public int compareTo(SpaceMarinesGetter o) {
        return getHealth()-o.getHealth();
    }
}
