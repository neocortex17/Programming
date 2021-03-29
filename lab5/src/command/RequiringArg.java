package command;

/**
 * Interface for commands that need an argument
 * @param <T> argument type
 */
public interface RequiringArg<T> {

    /**
     * The method that sets the argument
     * @param arg argument
     */
    void setArg(T arg);
}
