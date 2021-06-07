package spaceMarine;

import exceptions.InvalidFieldException;

import java.time.ZonedDateTime;

public class SpaceMarineBuilderImpl implements SpaceMarineBuilder{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesBuilder coordinatesBuilder; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private ChapterBuilder chapterBuilder; //Поле может быть null
    private final ValidateField validateField;

    public SpaceMarineBuilderImpl(){
        validateField = new SpaceMarineValidateField();
        chapterBuilder = new ChapterBuilderImpl();
    }

    @Override
    public void setName(String name) throws InvalidFieldException {
        if (validateField.validateName(name)) {
            this.name = name;
        }else throw new InvalidFieldException();
    }

    @Override
    public void setCoordinatesX(float x) throws InvalidFieldException {
        if (coordinatesBuilder == null){
            coordinatesBuilder = new CoordinatesBuilderImpl();
        }
        coordinatesBuilder.setX(x);
    }

    @Override
    public void setCoordinatesY(double y) throws InvalidFieldException {
        if (coordinatesBuilder == null){
            coordinatesBuilder = new CoordinatesBuilderImpl();
        }
        coordinatesBuilder.setY(y);
    }

    @Override
    public void setHealth(int health) throws InvalidFieldException {
        if (validateField.validateHealth(health)){
            this.health = health;
        }else {
            throw new InvalidFieldException();
        }
    }

    @Override
    public void setAstartesCategory(AstartesCategory category) throws InvalidFieldException {
        if (validateField.validateAstartesCategory(category)){
            this.category = category;
        }else {
            throw new InvalidFieldException();
        }
    }

    @Override
    public void setWeaponType(Weapon weaponType) throws InvalidFieldException {
        if (validateField.validateWeapon(weaponType)){
            this.weaponType = weaponType;
        }else {
            throw new InvalidFieldException();
        }
    }

    @Override
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) throws InvalidFieldException {
        if (validateField.validateMeleeWeapon(meleeWeapon)){
            this.meleeWeapon = meleeWeapon;
        }else {
            throw new InvalidFieldException();
        }
    }

    @Override
    public void setChapterName(String name) throws InvalidFieldException {
        if (validateField.validateChapterName(name)){
            chapterBuilder.setName(name);
        }else {
            throw new InvalidFieldException();
        }
    }

    @Override
    public void setChapterWorld(String world) {
        chapterBuilder.setWorld(world);
    }

    @Override
    public SpaceMarinesGetter getSpaceMarineGetter() {
        return new SpaceMarinesGetter(name, coordinatesBuilder.getCoordinates(), health, category,
                weaponType, meleeWeapon, chapterBuilder.getChapter());
    }
}
