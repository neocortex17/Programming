package messages;

import spaceMarine.SpaceMarinesGetter;

public interface Request {
    RequestType getType();
    String getCommand();
    String getArg();
    SpaceMarinesGetter getObject();
}
