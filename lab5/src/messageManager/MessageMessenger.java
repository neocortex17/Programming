package messageManager;

import exceptions.NoMessException;
import exceptions.NoSuchCommandException;
import spaceMarine.SpaceMarine;

/**
 * A class that implements the IMessageMessenger interface
 */
public class MessageMessenger implements IMessageMessenger{
    private ICommandMessage commandMessage;
    private IExceptionMessage exceptionMessage;
    private ICollectionMessage collectionMessage;
    private String startMess;
    private String finishMess;

    /**
     * Constructor for MessageMessenger
     * @param commandMessage command message
     * @param exceptionMessage exception message
     * @param collectionMessage collection message
     */
    public MessageMessenger(ICommandMessage commandMessage, IExceptionMessage exceptionMessage,ICollectionMessage collectionMessage){
        this.commandMessage = commandMessage;
        this.exceptionMessage = exceptionMessage;
        this.collectionMessage = collectionMessage;
        startMess = "Program started";
        finishMess = "Program finished";
    }

    @Override
    public String getExceptionMess(String mess){
        switch (mess){
            case "invalidId":
                return exceptionMessage.getInvalidIdMess();
            case "notUniqueId":
                return exceptionMessage.getNotUniqueIdMess();
            case "noIdLeft":
                return exceptionMessage.getNoIdLeftMess();
            case "invalidName":
                return exceptionMessage.getInvalidNameMess();
            case "invalidCoordinates":
                return exceptionMessage.getInvalidCoordinatesMess();
            case "invalidCoordinatesX":
                return exceptionMessage.getInvalidCoordinatesXMess();
            case "invalidCoordinatesY":
                return exceptionMessage.getInvalidCoordinatesYMess();
            case "invalidCreationDate":
                return exceptionMessage.getInvalidCreationDate();
            case "invalidHealth":
                return exceptionMessage.getInvalidHealthMess();
            case "invalidAstartesCategory":
                return exceptionMessage.getInvalidAstartesCategoryMess();
            case "invalidWeapon":
                return exceptionMessage.getInvalidWeaponMess();
            case "invalidMeleeWeapon":
                return exceptionMessage.getInvalidMeleeWeaponMess();
            case "invalidChapterName":
                return exceptionMessage.getInvalidChapterNameMess();
            case "invalidChapter":
                return exceptionMessage.getInvalidChapter();
            case "noArgument":
                return exceptionMessage.getNoArgumentMess();
            case "noEnum":
                return exceptionMessage.getNoEnumMess();
            case "noEvnVar":
                return exceptionMessage.getNoEvnVar();
            case "noInteger":
                return exceptionMessage.getNoInteger();
            case "noFloat":
                return exceptionMessage.getNoFloat();
            case "noDouble":
                return exceptionMessage.getNoDouble();
            case "wrongFieldType":
                return exceptionMessage.getWrongFieldTypeMess();
            case "noSuchCommand":
                return exceptionMessage.getNoSuchCommandMess();
            case "noSuchId":
                return exceptionMessage.getNoSuchIdMess();
            case "noSuchField":
                return exceptionMessage.getNoSuchFieldMess();
            case "noSuchElement":
                return exceptionMessage.getNoSuchElementMess();
            case "script":
                return exceptionMessage.getScriptMess();
            case "noFile":
                return exceptionMessage.getNoFileMess();
            case "scriptRecursion":
                return exceptionMessage.getScriptRecursionMess();
            default:
                throw new NoMessException(exceptionMessage.getNoMessMess());
            }
        }

    @Override
    public String getCommandMess(String commandName) throws NoSuchCommandException {
        switch (commandName){
            case "add":
                return commandMessage.getAddMess();
            case "add_if_max":
                return commandMessage.getAddIfMaxMess();
            case "clear":
                return commandMessage.getClearMess();
            case "count_by_weapon_type":
                return commandMessage.getCountByWeaponTypeMess();
            case "execute_script":
                return commandMessage.getExecuteScriptMess();
            case "exit":
                return commandMessage.getExitMess();
            case "group_counting_by_id":
                return commandMessage.getGroupCountingByIdMess();
            case "help":
                return commandMessage.getHelpMess();
            case "history":
                return commandMessage.getHistoryMess();
            case "info":
                return commandMessage.getInfoMess();
            case "print_field_descending_weapon_type":
                return commandMessage.getPrintFieldDescendingWeaponTypeMess();
            case "remove_by_id":
                return commandMessage.getRemoveByIdMess();
            case "remove_greater":
                return commandMessage.getRemoveGreaterMess();
            case "save":
                return commandMessage.getSaveMess();
            case "show":
                return commandMessage.getShowMess();
            case "update_id":
                return commandMessage.getUpdateIdMess();
            default:
                throw new NoSuchCommandException(exceptionMessage.getNoSuchCommandMess());
        }
    }

    @Override
    public String getCollectionTypeMess() {
        return collectionMessage.getCollectionTypeMess();
    }

    @Override
    public String getCollectionDateMess() {
        return collectionMessage.getCollectionDateMess();
    }

    @Override
    public String getCollectionSizeMess() {
        return collectionMessage.getCollectionSizeMess();
    }

    @Override
    public String getFieldInputMess(String field) throws NoSuchFieldException{
        switch (field){
            case "name":
                return collectionMessage.getInputNameMess();
            case "coordinatesX":
                return collectionMessage.getInputCoordinatesXMess();
            case "coordinatesY":
                return collectionMessage.getInputCoordinatesYMess();
            case "health":
                return collectionMessage.getInputHealthMess();
            case "category":
                return collectionMessage.getInputAstartesCategoryMess();
            case "weaponType":
                return collectionMessage.getInputWeaponTypeMess();
            case "meleeWeapon":
                return collectionMessage.getInputMeleeWeaponMess();
            case "chapterName":
                return collectionMessage.getInputChapterNameMess();
            case "chapterWorld":
                return collectionMessage.getInputChapterWorldMess();
            default:
                throw new NoSuchFieldException(exceptionMessage.getNoSuchFieldMess());
        }
    }

    @Override
    public String getSpaceMarineString(SpaceMarine spaceMarine) {
        return collectionMessage.getSpaceMarineString(spaceMarine);
    }

    @Override
    public String getStartMess() {
        return startMess;
    }

    @Override
    public String getFinishMess() {
        return finishMess;
    }
}
