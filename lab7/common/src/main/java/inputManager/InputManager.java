package inputManager;

import identification.Identification;
import spaceMarine.SpaceMarinesGetter;

import java.io.IOException;

public interface InputManager {
    boolean ready() throws IOException;
    String readCommand() throws IOException;
    SpaceMarinesGetter readSpaceMarine() throws IOException;
    Identification readIdent() throws IOException;
}
