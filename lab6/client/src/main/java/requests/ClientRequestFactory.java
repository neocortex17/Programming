package requests;

import messages.Request;
import messages.RequestImpl;
import messages.RequestType;
import spaceMarine.SpaceMarinesGetter;

public class ClientRequestFactory implements RequestFactory{
    @Override
    public Request createArgObjectRequest(String command, String arg, SpaceMarinesGetter spaceMarine) {
        return new RequestImpl(RequestType.ARG_OBJECT_REQUEST, command, arg, spaceMarine);
    }

    @Override
    public Request createObjectRequest(String command, SpaceMarinesGetter spaceMarine) {
        return new RequestImpl(RequestType.OBJECT_REQUEST, command, null, spaceMarine);
    }

    @Override
    public Request createArgRequest(String command, String arg) {
        return new RequestImpl(RequestType.ARG_REQUEST, command, arg, null);
    }

    @Override
    public Request createSimpleRequest(String command) {
        return new RequestImpl(RequestType.SIMPLE_REQUEST, command, null, null);
    }
}
