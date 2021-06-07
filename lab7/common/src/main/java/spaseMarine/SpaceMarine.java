package spaceMarine;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Space Marine class
 */
public class SpaceMarine implements Comparable <SpaceMarine>, Serializable {
    private static final long serialVersionUID = -5860098109272028496L;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private ValidateField validateField;

    public SpaceMarine(){
        validateField = new SpaceMarineValidateField();

    }

    public SpaceMarine(Integer id, SpaceMarinesGetter spaceMarinesGetter,ZonedDateTime date) {
        this.id=id;
        this.name = spaceMarinesGetter.getName();
        this.coordinates = spaceMarinesGetter.getCoordinates();
        this.creationDate=date;
        this.health = spaceMarinesGetter.getHealth();
        this.category = spaceMarinesGetter.getCategory();
        this.weaponType = spaceMarinesGetter.getWeaponType();
        this.meleeWeapon = spaceMarinesGetter.getMeleeWeapon();
        this.chapter = spaceMarinesGetter.getChapter();
    }

    public Integer getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public ZonedDateTime getCreationDate(){
        return creationDate;
    }
    public int getHealth(){
        return health;
    }
    public AstartesCategory getCategory(){
        return category;
    }
    public Weapon getWeaponType(){
        return weaponType;
    }
    public MeleeWeapon getMeleeWeapon(){
        return meleeWeapon;
    }
    public Chapter getChapter(){
        return chapter;
    }
    public SpaceMarinesGetter getSpaceMarinesGetter(){
        return new SpaceMarinesGetter(getName(), getCoordinates(), getHealth(), getCategory(), getWeaponType(), getMeleeWeapon(), getChapter());
    }

    public int compareTo(SpaceMarine spaceMarine){
        int result = Integer.compare(health, spaceMarine.health);
        if (result != 0){
            return result;
        }
        return Integer.compare(id, spaceMarine.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return health == that.health &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(creationDate, that.creationDate) &&
                category == that.category &&
                weaponType == that.weaponType &&
                meleeWeapon == that.meleeWeapon &&
                Objects.equals(chapter, that.chapter) &&
                Objects.equals(validateField, that.validateField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, category, weaponType, meleeWeapon, chapter, validateField);
    }

    @Override
    public String toString() {
        return String.format("SpaceMarine(%d;%s;%s;%s;%d;%s;%s;%s;%s)", id, name, coordinates, creationDate, health, category, weaponType, meleeWeapon, chapter);
    }
}







