package messageManager;

/**
 * Class interface with command messages
 */
public interface ICommandMessage {
    String getAddMess();
    String getAddIfMaxMess();
    String getClearMess();
    String getCountByWeaponTypeMess();
    String getExecuteScriptMess();
    String getExitMess();
    String getGroupCountingByIdMess();
    String getHelpMess();
    String getHistoryMess();
    String getInfoMess();
    String getPrintFieldDescendingWeaponTypeMess();
    String getRemoveByIdMess();
    String getRemoveGreaterMess();
    String getSaveMess();
    String getShowMess();
    String getUpdateIdMess();
}
