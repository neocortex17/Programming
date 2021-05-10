package requests;

import messages.Request;
import spaceMarine.SpaceMarinesGetter;

public interface RequestFactory {
    Request createArgObjectRequest(String command, String arg, SpaceMarinesGetter spaceMarine);
    Request createObjectRequest(String command, SpaceMarinesGetter spaceMarine);
    Request createArgRequest(String command, String arg);
    Request createSimpleRequest(String command);
}
