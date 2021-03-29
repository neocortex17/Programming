package messageManager;

import spaceMarine.SpaceMarine;

/**
 * Class interface with collection messages
 */
public interface ICollectionMessage {
    String getSpaceMarineString(SpaceMarine spaceMarine);
    String getCollectionTypeMess();
    String getCollectionDateMess();
    String getCollectionSizeMess();
    String getInputNameMess();
    String getInputCoordinatesXMess();
    String getInputCoordinatesYMess();
    String getInputHealthMess();
    String getInputAstartesCategoryMess();
    String getInputWeaponTypeMess();
    String getInputMeleeWeaponMess();
    String getInputChapterNameMess();
    String getInputChapterWorldMess();

}
