package messageManager;

/**
 * A class that implements the IExceptionMessage interface
 */
public class ExceptionMessage implements IExceptionMessage{
    private final String invalidIdMess;
    private final String notUniqueIdMess;
    private final String noIdLeftMess;
    private final String invalidNameMess;
    private final String invalidCoordinatesMess;
    private final String invalidCoordinatesXMess;
    private final String invalidCoordinatesYMess;
    private final String invalidHealthMess;
    private final String invalidAstartesCategoryMess;
    private final String invalidWeaponMess;
    private final String invalidMeleeWeaponMess;
    private final String invalidChapterNameMess;
    private final String invalidCreationDate;
    private final String invalidChapter;
    private final String noArgumentMess;
    private final String noEnumMess;
    private final String noInteger;
    private final String noFloat;
    private final String noDouble;
    private final String wrongFieldTypeMess;
    private final String noSuchCommandMess;
    private final String noSuchIdMess;
    private final String noSuchFieldMess;
    private final String noSuchElementMess;
    private final String scriptMess;
    private final String noFileMess;
    private final String noMessMess;
    private final String noEvnVar;
    private final String scriptRecursionMess;

    /**
     * Constructor for ExceptionMessage
      */
    public ExceptionMessage(){
        invalidIdMess = "Invalid id value. Must be more 0";
        notUniqueIdMess = "Not uniq id";
        noIdLeftMess = "No id left";
        invalidNameMess = "Invalid name value. Must be not null";
        invalidCoordinatesMess = "Invalid coordinates value. Must be not null";
        invalidCoordinatesXMess = "Invalid coordinate x value. Must be float";
        invalidCoordinatesYMess = "Invalid coordinate y value. Must be double";
        invalidHealthMess = "Invalid health value. Must be more 0";
        invalidAstartesCategoryMess = "Invalid AstartesCategory value. Must be not null";
        invalidWeaponMess = "Invalid weaponType value. Must be not null";
        invalidMeleeWeaponMess = "Invalid meleeWeapon value. Must be not null";
        invalidChapterNameMess = "Invalid chapter name value. Must be not null";
        invalidChapter = "Invalid chapter value. Must be not null";
        noArgumentMess = "This is command needs 1 argument";
        noEnumMess = "Value must be selected from the suggested options";
        wrongFieldTypeMess = "Wrong field type";
        noInteger = "Invalid value. Must be integer";
        noFloat = "Invalid value. Must be float";
        noDouble = "Invalid value. Must be double";
        invalidCreationDate = "Invalid creationDate value. Must be not null";
        noSuchCommandMess = "No such command";
        noSuchIdMess = "Element with this id doesn't exist";
        noSuchFieldMess = "This field doesn't exist in class SpaceMarine";
        noSuchElementMess = "End of input";
        scriptMess = "Script error, script execution stopped";
        noFileMess = "This file doesn't exist";
        noEvnVar = "environmental variable doesn't exist";
        noMessMess = "This message doesn't exist";
        scriptRecursionMess = "Script calls recursion, script execution sropped";
    }

    @Override
    public String getInvalidIdMess() {
        return invalidIdMess;
    }

    @Override
    public String getNotUniqueIdMess() {
        return notUniqueIdMess;
    }

    @Override
    public String getNoEvnVar() {
        return noEvnVar;
    }

    @Override
    public String getInvalidNameMess() {
        return invalidNameMess;
    }

    @Override
    public String getInvalidCreationDate() {
        return invalidCreationDate;
    }

    @Override
    public String getInvalidCoordinatesMess() {
        return invalidCoordinatesMess;
    }

    @Override
    public String getInvalidCoordinatesXMess() {
        return invalidCoordinatesXMess;
    }

    @Override
    public String getInvalidCoordinatesYMess() {
        return invalidCoordinatesYMess;
    }

    @Override
    public String getInvalidChapter() {
        return invalidChapter;
    }

    @Override
    public String getNoInteger() { return noInteger; }

    @Override
    public String getNoFloat() {
        return noFloat;
    }

    @Override
    public String getNoDouble() {
        return noDouble;
    }

    @Override
    public String getInvalidHealthMess() {
        return invalidHealthMess;
    }

    @Override
    public String getInvalidAstartesCategoryMess() {
        return invalidAstartesCategoryMess;
    }

    @Override
    public String getInvalidWeaponMess() {
        return invalidWeaponMess;
    }

    @Override
    public String getInvalidMeleeWeaponMess() {
        return invalidMeleeWeaponMess;
    }

    @Override
    public String getInvalidChapterNameMess() {
        return invalidChapterNameMess;
    }

    @Override
    public String getNoArgumentMess() {
        return noArgumentMess;
    }

    @Override
    public String getNoEnumMess() {
        return noEnumMess;
    }

    @Override
    public String getNoIdLeftMess() {
        return noIdLeftMess;
    }

    @Override
    public String getWrongFieldTypeMess() {
        return wrongFieldTypeMess;
    }

    @Override
    public String getNoSuchCommandMess() {
        return noSuchCommandMess;
    }

    @Override
    public String getNoSuchIdMess() {
        return noSuchIdMess;
    }

    @Override
    public String getNoSuchFieldMess() {
        return noSuchFieldMess;
    }

    @Override
    public String getNoSuchElementMess() {
        return noSuchElementMess;
    }

    @Override
    public String getNoMessMess(){return noMessMess;}

    @Override
    public String getScriptMess() {
        return scriptMess;
    }

    @Override
    public String getNoFileMess() {
        return noFileMess;
    }

    @Override
    public String getScriptRecursionMess() {
        return scriptRecursionMess;
    }
}