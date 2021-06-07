package serverCommands;

import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class that implements the command to display the values of the weaponType field of all elements in descending order
 */
public class PrintFieldDescendingWeaponType implements ServerCommand {
    private final CollectionManager collectionManager;
    private final Message message;

    /**
     * Constructor for PrintFieldDescendingWeaponType
     */
    public PrintFieldDescendingWeaponType(CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        collectionManager.getSpaceMarineStream()
                .sorted((first, second) -> second.getWeaponType().getWeaponInInt() - first.getWeaponType().getWeaponInInt())
                .map(spaceMarine -> spaceMarine.getWeaponType().toString().concat("\n"))
                .forEachOrdered(stringBuilder :: append);
        return stringBuilder.toString();
    }
}
