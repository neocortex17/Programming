package command;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;

/**
 * Invoker interface
 * @author neocortex
 */
public interface InvokerInterface {
    /**
     * The method that invokes the command execution
     * @param command command
     */
    void invokeCommand(Command command) throws NoSuchFieldException;

    /**
     * Method that receives a command with an argument of type Integer
     * @param command command
     * @throws InvalidArgumentTypeException if an invalid argument type is passed
     * @throws NoArgumentException if no argument was passed
     */
    void setIntegerArg(RequiringArg<Integer> command) throws InvalidArgumentTypeException,NoArgumentException;

    /**
     * Method that receives a command with an argument of type String
     * @param command command
     * @throws NoArgumentException if no argument was passed
     */
    void setStringArg(RequiringArg<String> command) throws NoArgumentException;

    /**
     * Method that takes an argument of type String
     * @param arg argument
     */
    void setArg(String arg);
}
