package exceptions;

/**
 * An exception class that is thrown if the script calls recursion
 */
public class ScriptRecursionException extends RuntimeException{
    public ScriptRecursionException(String mess) {
        super(mess);
    }
}
