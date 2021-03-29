package messageManager;

/**
 * A class that implements the ICommandMessage interface
 */
public class CommandMessage implements ICommandMessage{
    private final String addMess;
    private final String addIfMaxMess;
    private final String clearMess;
    private final String countByWeaponTypeMess;
    private final String executeScriptMess;
    private final String exitMess;
    private final String groupCountingByIdMess;
    private final String helpMess;
    private final String historyMess;
    private final String infoMess;
    private final String printFieldDescendingWeaponTypeMess;
    private final String removeByIdMess;
    private final String removeGreaterMess;
    private final String saveMess;
    private final String showMess;
    private final String updateIdMess;

    /**
     * Constructor for CommandMessage
     */
    public CommandMessage (){
        addMess = "add {element} : add new item to collection";
        addIfMaxMess = "add_if_max {element} : add a new item to the collection if its value exceeds the value of the largest item in this collection";
        clearMess = "clear : clear collection";
        countByWeaponTypeMess = "count_by_weapon_type weaponType : display the number of elements whose weaponType field value is equal to the given one";
        executeScriptMess = "execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in witch the user enters them interactively";
        exitMess = "exit : end the program (without their saving to file)";
        groupCountingByIdMess = "group_counting_by_id : group the elements of the collection by the value of the id field, display the number of elements in each group";
        helpMess = "help : display help for available commands";
        historyMess = "history : print the last 8 commands (without their arguments)";
        infoMess = "info: print information about the collection";
        printFieldDescendingWeaponTypeMess = "print_field_descending_weapon_type : display the values of the weaponType field of all elements in descending order";
        removeByIdMess = "remove_by_id id : remove an item from the collection by its id";
        removeGreaterMess = "remove_greater {element} : remove all items from the collection that are greater than the given one";
        saveMess = "save : save collection to file";
        showMess = "show: print to standard output all elements of the collection in string representation";
        updateIdMess = "update_id {element} : update the value of the collection element whose id is equal to the given";
    }

    @Override
    public String getAddIfMaxMess() {
        return addIfMaxMess;
    }

    @Override
    public String getAddMess() {
        return addMess;
    }

    @Override
    public String getClearMess() {
        return clearMess;
    }

    public String getCountByWeaponTypeMess() {
        return countByWeaponTypeMess;
    }

    @Override
    public String getExecuteScriptMess() {
        return executeScriptMess;
    }

    @Override
    public String getExitMess() {
        return exitMess;
    }

    @Override
    public String getGroupCountingByIdMess() {
        return groupCountingByIdMess;
    }

    @Override
    public String getHelpMess() {
        return helpMess;
    }

    @Override
    public String getHistoryMess() {
        return historyMess;
    }

    @Override
    public String getInfoMess() {
        return infoMess;
    }

    @Override
    public String getPrintFieldDescendingWeaponTypeMess() {
        return printFieldDescendingWeaponTypeMess;
    }

    @Override
    public String getRemoveByIdMess() {
        return removeByIdMess;
    }

    @Override
    public String getRemoveGreaterMess() {
        return removeGreaterMess;
    }

    @Override
    public String getSaveMess() {
        return saveMess;
    }

    @Override
    public String getShowMess() {
        return showMess;
    }

    @Override
    public String getUpdateIdMess() {
        return updateIdMess;
    }
}
