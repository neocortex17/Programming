package messageManager;

import spaceMarine.SpaceMarine;

/**
 * A class that implements the ICollectionMessage interface
 */
public class CollectionMessage implements ICollectionMessage{
    private final String collectionTypeMess;
    private final String collectionSizeMess;
    private final String collectionDateMess;
    private final String inputNameMess;
    private final String inputCoordinatesXMess;
    private final String inputCoordinatesYMess;
    private final String inputHealthMess;
    private final String inputAstartesCategoryMess;
    private final String inputWeaponTypeMess;
    private final String inputMeleeWeaponMess;
    private final String inputChapterNameMess;
    private final String inputChapterWorldMess;

    /**
     * Constructor for CollectionMessage
     */
    public CollectionMessage(){
        collectionTypeMess = "collection type";
        collectionDateMess = "initialization date";
        collectionSizeMess = "number of elements in collection";
        inputNameMess = "name";
        inputCoordinatesXMess = "coordinates.x";
        inputCoordinatesYMess = "coordinates.y";
        inputHealthMess = "health";
        inputAstartesCategoryMess = "category (SCOUT, DREADNOUGHT, AGGRESSOR, SUPPRESSOR, TERMINATOR)";
        inputWeaponTypeMess = "weaponType (BOLT_PISTOL, COMBI_FLAMER, INFERNO_PISTOL)";
        inputMeleeWeaponMess = "meleeWeapon (POWER_SWORD, CHAIN_AXE, MANREAPER, LIGHTING_CLAW, POWER_FIST)";
        inputChapterNameMess = "chapter.name";
        inputChapterWorldMess = "chapter.world";
    }

    @Override
    public String getSpaceMarineString(SpaceMarine spaceMarine) {
        return String.format("id=%d; name=%s; coordinates=(%.1f, %.2f); creationDate=%s; health=%d;" +
                " category=%s; weaponType=%s; meleeWeapon=%s; chapter=(%s, %s)",spaceMarine.getId(),spaceMarine.getName(),
                spaceMarine.getCoordinates().getX(),spaceMarine.getCoordinates().getY(),spaceMarine.getCreationDate(),
                spaceMarine.getHealth(), spaceMarine.getCategory(),spaceMarine.getWeaponType(),spaceMarine.getMeleeWeapon(),
                spaceMarine.getChapter().getName(),spaceMarine.getChapter().getWorld());
    }

    @Override
    public String getCollectionTypeMess() {
        return collectionTypeMess;
    }

    @Override
    public String getCollectionDateMess() {
        return collectionDateMess;
    }

    @Override
    public String getCollectionSizeMess() {
        return collectionSizeMess;
    }

    @Override
    public String getInputNameMess() {
        return inputNameMess;
    }

    @Override
    public String getInputCoordinatesXMess() {
        return inputCoordinatesXMess;
    }

    @Override
    public String getInputCoordinatesYMess() {
        return inputCoordinatesYMess;
    }

    @Override
    public String getInputHealthMess() {
        return inputHealthMess;
    }

    @Override
    public String getInputAstartesCategoryMess() {
        return inputAstartesCategoryMess;
    }

    @Override
    public String getInputWeaponTypeMess() {
        return inputWeaponTypeMess;
    }

    @Override
    public String getInputMeleeWeaponMess() {
        return inputMeleeWeaponMess;
    }

    @Override
    public String getInputChapterNameMess() {
        return inputChapterNameMess;
    }

    @Override
    public String getInputChapterWorldMess() {
        return inputChapterWorldMess;
    }
}
