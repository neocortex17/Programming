package spaceMarine;

import exceptions.InvalidArgumentTypeException;
import exceptions.InvalidFieldException;
import exceptions.NoMessException;
import exceptions.NotUniqueIdException;
import managers.ISpaceMarineIdManager;
import managers.SpaceMarineIdManager;
import managers.SpaceMarineValidateField;
import managers.ValidateField;
import messageManager.IMessageMessenger;

import java.time.ZonedDateTime;
import java.util.Formatter;

/**
 * Space Marine class
 */
public class SpaceMarine implements Comparable <SpaceMarine>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private IMessageMessenger messageMessenger;
    private ISpaceMarineIdManager spaceMarineIdManager;
    private ValidateField validateField;

    public SpaceMarine(){ }

    public SpaceMarine(IMessageMessenger messageMessenger){
        spaceMarineIdManager = SpaceMarineIdManager.getInstance();
        validateField = new SpaceMarineValidateField();
        this.messageMessenger = messageMessenger;
    }

    public SpaceMarine(Integer id, String name, Coordinates coordinates,ZonedDateTime date, int health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id=id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate=date;
        this.health=health;
        this.category=category;
        this.weaponType=weaponType;
        this.meleeWeapon=meleeWeapon;
        this.chapter=chapter;
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

    public int compareTo(SpaceMarine spaceMarine){
        int result = Integer.compare(health, spaceMarine.health);
        if (result != 0){
            return result;
        }
        return Integer.compare(id, spaceMarine.id);
    }

    public void setId() {
        id = spaceMarineIdManager.getFreeId();
    }

    public void setId(Integer id) throws InvalidFieldException, NoMessException {
        if (validateField.validateId(id)) {
            if (spaceMarineIdManager.idIsFree(id)) {
                this.id = id;
            } else {
                throw new NotUniqueIdException(messageMessenger.getExceptionMess("notUniqueId"));
            }
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidId"));
        }
    }


    public void setName(String name) throws InvalidFieldException {
        if (validateField.validateName(name)) {
            this.name = name;
        }else throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidName"));
    }

    public void setCoordinatesX(float x) throws InvalidFieldException {
        if (coordinates == null) {
            coordinates = new Coordinates(messageMessenger);
        }
        coordinates.setX(x);
    }

    public void  setCoordinatesY(double y) throws InvalidFieldException {
        if (coordinates == null) {
            coordinates = new Coordinates(messageMessenger);
        }
        coordinates.setY(y);
    }

    public void setHealth(int health) throws InvalidFieldException {
        if (validateField.validateHealth(health)){
            this.health = health;
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidHealth"));
        }
    }
    public void setCreationDate(ZonedDateTime creationDate) throws InvalidFieldException{
        if (validateField.validateZonedDateTimeFormat(creationDate)) {
            this.creationDate = creationDate;
        }else{
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidCreationDate"));
        }
    }

    public void setCreationDate(){
        creationDate = ZonedDateTime.now();
    }

    public void setCategory (AstartesCategory category) throws InvalidFieldException {
        if (validateField.validateAstartesCategory(category)){
            this.category = category;
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidAstartesCategory"));
        }
    }

    public void setWeaponType(Weapon weaponType) throws InvalidFieldException {
        if (validateField.validateWeapon(weaponType)){
            this.weaponType = weaponType;
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidWeapon"));
        }
    }

    public void setMeleeWeapon (MeleeWeapon meleeWeapon) throws InvalidFieldException {
        if (validateField.validateMeleeWeapon(meleeWeapon)){
            this.meleeWeapon = meleeWeapon;
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidMeleeWeapon"));
        }
    }

    public void setChapterName(String name) throws InvalidArgumentTypeException {
        if (chapter==null){
            chapter = new Chapter();
        }
        chapter.setName(name);
    }

    public void setChapterWorld(String world){
        if (chapter==null){
            chapter = new Chapter();
        }
        chapter.setWorld(world);
    }
    public SpaceMarine getSpaceMarine(){
        if (validateField.validateZonedDateTimeFormat(creationDate)){
            creationDate=ZonedDateTime.now();
        }
        spaceMarineIdManager.addId(id);
        return new SpaceMarine(id, name, coordinates, creationDate, health, category, weaponType, meleeWeapon, chapter);
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        String ch;
        if (chapter == null) {
            ch = "null, null";
        }else {
            ch = chapter.toString();
        }
        formatter.format("%d,%s,%s,%s,%d,%s,%s,%s,\"%s\"",id, name, coordinates.toString(), creationDate.toString(),health,category.toString(),weaponType.toString(),meleeWeapon.toString(),chapter.toString());
        return formatter.toString();
    }
}







