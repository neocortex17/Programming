package serverCommands;

import messages.Request;
import messages.Response;

public class RequestAnalyzer {
    private final ServerCommandManager serverCommandManager;

    public RequestAnalyzer(ServerCommandManager serverCommandManager){
        this.serverCommandManager = serverCommandManager;
    }

    public Response analyzeRequest(Request request){
        return serverCommandManager.executeClientCommand(request.getType(), request.getCommand(), request.getArg(),
                request.getObject(), request.getIdentification());
    }
}
