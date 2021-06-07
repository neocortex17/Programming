package messages;

import identification.Identification;
import spaceMarine.SpaceMarinesGetter;

public interface Request {
    RequestType getType();
    String getCommand();
    String getArg();
    SpaceMarinesGetter getObject();
    Identification getIdentification();
}
