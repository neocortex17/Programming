package inputManager;

import spaceMarine.SpaceMarinesGetter;

import java.io.IOException;

public interface SpaceMarineReader {
    SpaceMarinesGetter readSpaceMarine() throws IOException;
}
