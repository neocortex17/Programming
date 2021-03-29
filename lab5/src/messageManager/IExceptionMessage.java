package messageManager;

/**
 * Class interface with exception messages
 */
public interface IExceptionMessage {
    String getInvalidIdMess();
    String getNotUniqueIdMess();
    String getInvalidNameMess();
    String getInvalidCoordinatesMess();
    String getInvalidCoordinatesXMess();
    String getInvalidCoordinatesYMess();
    String getInvalidHealthMess();
    String getInvalidAstartesCategoryMess();
    String getInvalidWeaponMess();
    String getInvalidMeleeWeaponMess();
    String getInvalidChapterNameMess();
    String getInvalidCreationDate();
    String getInvalidChapter();
    String getNoArgumentMess();
    String getNoEnumMess();
    String getNoInteger();
    String getNoFloat();
    String getNoDouble();
    String getNoIdLeftMess();
    String getWrongFieldTypeMess();
    String getNoSuchCommandMess();
    String getNoSuchIdMess();
    String getNoSuchFieldMess();
    String getNoSuchElementMess();
    String getScriptMess();
    String getNoFileMess();
    String getNoMessMess();
    String getNoEvnVar();
    String getScriptRecursionMess();
}
