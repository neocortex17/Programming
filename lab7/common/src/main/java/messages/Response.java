package messages;

public interface Response {
    ResponseType getType();
    String getContent();
}
