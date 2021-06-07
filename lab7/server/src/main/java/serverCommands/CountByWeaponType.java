package serverCommands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.NoArgumentException;
import messages.Message;
import spaceMarine.Weapon;

import java.util.Locale;

/**
 * The class that implements the command to print the number of elements, the value of the weaponType field of which is equal to the specified
 * @author neocortex
 */
public class  CountByWeaponType implements ServerCommand, RequiringArg<String> {
    private final CollectionManager collectionManager;
    private final Message message;
    private String arg;

    /**
     * Constructor for CountByWeaponType
     */
    public CountByWeaponType (CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getMessage("countByWeaponTypeOutput"))
                .append(":\n");
        stringBuilder.append(collectionManager.getSpaceMarineStream()
                .filter(spaceMarine -> spaceMarine.getWeaponType().compareTo(Weapon.valueOf(arg.toUpperCase(Locale.ROOT))) == 0)
                .count());
        return stringBuilder.toString();
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException {
        commandInvoker.setStringArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
