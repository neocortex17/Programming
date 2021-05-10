package serverCommands;

import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class of the command that groups the elements of the collection by the value of the id field
 * and displays the number of elements in each group
 */
public class GroupCountingById implements ServerCommand {
    private final CollectionManager collectionManager;
    private final Message message;
    private String arg;

    /**
     * Constructor for CountByWeaponType
     */
    public GroupCountingById (CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Number of id with remainder of division by 3 equal to 0: ");
        stringBuilder.append(collectionManager.getSpaceMarineStream().filter(spaceMarine -> spaceMarine.getId() % 3 == 0).count())
                .append("\n")
                .append("Number of id with remainder of division by 3 equal to 1: ")
                .append(collectionManager.getSpaceMarineStream().filter(spaceMarine -> spaceMarine.getId() % 3 == 1).count())
                .append("\n")
                .append("Number of id with remainder of division by 3 equal to 2: ")
                .append(collectionManager.getSpaceMarineStream().filter(spaceMarine -> spaceMarine.getId() % 3 == 2).count())
                .append("\n");
        return stringBuilder.toString();
    }
}
