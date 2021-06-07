package responses;

import messages.Response;

public interface ResponseFactory {
    Response createDefaultResponse(String content);
    Response createErrorResponse(String content);
    Response createNeedObjectResponse();
    Response createIdentErrorResponse(String content);
}
