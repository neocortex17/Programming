package messages;

import spaceMarine.SpaceMarine;

import java.util.HashMap;

public class MessageImpl implements Message{
    private final HashMap<String,String> messages;

    public MessageImpl(){
        messages = new HashMap<>();
        messages.put("start","program started");
        messages.put("finish","program finished");
        messages.put("inputName","Enter the name");
        messages.put("addOutput", "The item has been added to the collection");
        messages.put("showOutput", "Items in collection");
        messages.put("clearOutput","Collection cleared");
        messages.put("historyOutput","History of commands entered");
        messages.put("exitOutput","Exit from application");
        messages.put("inputCoordinatesX","Enter the x coordinate");
        messages.put("inputCoordinatesY", "Enter the y coordinate");
        messages.put("inputHealth", "Enter the health");
        messages.put("collectionType","Collection type");
        messages.put("collectionInitDate", "Collection date");
        messages.put("collectionSize","Collection size");
        messages.put("removeOutput", "Item removed");
        messages.put("notRemoveOutput","Item not removed");
        messages.put("removeGreaterOutput", "All items larger than the specified one have been removed");
        messages.put("saveOutput","Collection saved");
        messages.put("scriptOutput", "The script execution has been completed");
        messages.put("updateOutput", "Object updated");
        messages.put("notUpdateOutput", "Object not updated");
        messages.put("inputCategory", "Enter the category (SCOUT, DREADNOUGHT, AGGRESSOR, SUPPRESSOR, TERMINATOR)");
        messages.put("inputWeapon","Enter the WeaponType (BOLT_PISTOL, COMBI_FLAMER, INFERNO_PISTOL)");
        messages.put("inputMeleeWeapon","Enter the MeleeWeapon (POWER_SWORD, CHAIN_AXE, MANREAPER, LIGHTING_CLAW, POWER_FIST)");
        messages.put("inputChapterName", "Enter the chapter name");
        messages.put("inputChapterWorld","Enter the chapter world");
        messages.put("invalidId", "invalid id value, must be more than 0");
        messages.put("notUniqueId", "not unique id");
        messages.put("invalidName", "invalid name value, must be not null");
        messages.put("invalidCoordinates", "invalid coordinates value, must be not null");
        messages.put("invalidCoordinatesX", "invalid coordinates.x value, must be no more than 882 and not null");
        messages.put("invalidCoordinatesY", "invalid coordinates.y value, must be more than -266");
        messages.put("invalidCreationDate", "invalid creationDate value, must be not null");
        messages.put("invalidHealth", "invalid health value, must be more than 0");
        messages.put("invalidCategory", "invalid category value, must be not null");
        messages.put("invalidWeapon", "invalid weaponType value, must be not null" );
        messages.put("invalidMeleeWeapon", "invalid meleeWeapon value, must be not null");
        messages.put("invalidChapterName", "invalid chapter name value, must be not null");
        messages.put("noInteger", "value must be integer number");
        messages.put("noFloat", "value must be float number");
        messages.put("noDate", "date must be in the specified format");
        messages.put("noEnum", "value must be selected from the suggested options");
        messages.put("noArg", "this command needs 1 argument");
        messages.put("wrongFieldType", "wrong field type");
        messages.put("noSuchCommand", "no such command");
        messages.put("noSuchId", "An element with this id does not exist");
        messages.put("endOfInput", "end of input");
        messages.put("scriptError", "script error, script execution stopped");
        messages.put("noFile", "this file does not exist");
        messages.put("scriptRecursion", "script calls recursion, script execution stopped");
        messages.put("add", "add {element} : add new item to collection");
        messages.put("add_if_max","add_if_max {element} : add a new item to the collection if its value exceeds the value of the largest item in this collection");
        messages.put("clear","clear : clear collection");
        messages.put("count_by_weapontype","count_by_weaponType weaponType : display the number of elements whose weaponType field value is equal to the given one");
        messages.put("execute_script","execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in witch the user enters them interactively");
        messages.put("exit","exit : end the program (without their saving to file)");
        messages.put("group_counting_by_id","group_counting_by_id : group the elements of the collection by the value of the id field, display the number of elements in each group");
        messages.put("help","help : display help for available commands");
        messages.put("history","history : print the last 8 commands (without their arguments)");
        messages.put("info","info: print information about the collection");
        messages.put("print_field_descending_weapontype","print_field_descending_weaponType : display the values of the weaponType field of all elements in descending order");
        messages.put("remove_by_id","remove_by_id id : remove an item from the collection by its id");
        messages.put("remove_greater","remove_greater {element} : remove all items from the collection that are greater than the given one");
        messages.put("save","save : save collection to file");
        messages.put("show","show: print to standard output all elements of the collection in string representation");
        messages.put("update","update {element} : update the value of the collection element whose id is equal to the given");
        messages.put("countByWeaponTypeOutput","");
    }

    @Override
    public String getMessage(String msg) {
        return messages.get(msg);
    }

    @Override
    public String getSpaceMarineString(SpaceMarine spaceMarine) {
        return String.format("id=%d; name=%s; coordinates=(%f, %.2f); creationDate=%s; height=%d; category=%s; " +
                        "weapon=%s; meleeWeapon=%s; chapter=(%s, %s)", spaceMarine.getId(), spaceMarine.getName(),
                spaceMarine.getCoordinates().getX(), spaceMarine.getCoordinates().getY(), spaceMarine.getCreationDate(),
                spaceMarine.getHealth(), spaceMarine.getCategory(), spaceMarine.getWeaponType(), spaceMarine.getMeleeWeapon(),
                spaceMarine.getChapter().getName(), spaceMarine.getChapter().getWorld());
    }
}
