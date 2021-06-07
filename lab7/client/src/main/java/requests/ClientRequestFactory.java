package requests;

import identification.Identification;
import messages.Request;
import messages.RequestImpl;
import messages.RequestType;
import spaceMarine.SpaceMarinesGetter;

public class ClientRequestFactory implements RequestFactory{
    @Override
    public Request createArgObjectRequest(String command, String arg, SpaceMarinesGetter spaceMarine, Identification identification) {
        return new RequestImpl(RequestType.ARG_OBJECT_REQUEST, command, arg, spaceMarine, identification);
    }

    @Override
    public Request createObjectRequest(String command, SpaceMarinesGetter spaceMarine, Identification identification) {
        return new RequestImpl(RequestType.OBJECT_REQUEST, command, null, spaceMarine, identification);
    }

    @Override
    public Request createArgRequest(String command, String arg, Identification identification) {
        return new RequestImpl(RequestType.ARG_REQUEST, command, arg, null, identification);
    }

    @Override
    public Request createSimpleRequest(String command, Identification identification) {
        return new RequestImpl(RequestType.SIMPLE_REQUEST, command, null, null, identification);
    }

    @Override
    public Request createIdentRegRequest(String command, Identification newIdentification, Identification identification) {
        return new RequestImpl(RequestType.IDENT_REG_COMMAND_REQUEST, command, newIdentification.getLogin()
        + " " + newIdentification.getPassword(), null, identification);
    }
}
