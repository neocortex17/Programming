package messageManager;

import exceptions.NoSuchCommandException;
import spaceMarine.SpaceMarine;

/**
 * Interface of the class that controls the display of messages
 */
public interface IMessageMessenger {
    String getExceptionMess(String mess);
    String getCommandMess(String mess) throws NoSuchCommandException;
    String getCollectionTypeMess();
    String getCollectionDateMess();
    String getCollectionSizeMess();
    String getFieldInputMess(String field) throws NoSuchFieldException;
    String getStartMess();
    String getSpaceMarineString(SpaceMarine spaceMarine);
    String getFinishMess();
}
