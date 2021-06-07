package requests;

import identification.Identification;
import messages.Request;
import spaceMarine.SpaceMarinesGetter;

public interface RequestFactory {
    Request createArgObjectRequest(String command, String arg, SpaceMarinesGetter spaceMarine, Identification identification);
    Request createObjectRequest(String command, SpaceMarinesGetter spaceMarine, Identification identification);
    Request createArgRequest(String command, String arg, Identification identification);
    Request createSimpleRequest(String command, Identification identification);
    Request createIdentRegRequest(String command, Identification newIdentification, Identification identification);
}
