package command;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import messageManager.IMessageMessenger;

/**
 * The class responsible for calling the command
 */
public class Invoker implements InvokerInterface{
    private String arg;
    private IMessageMessenger messageMessenger;

    /**
     * Constructor for Invoker
     * @param messageMessenger message messenger
     */
    public  Invoker (IMessageMessenger messageMessenger){
        this.messageMessenger = messageMessenger;
    }

    @Override
    public void invokeCommand(Command command) throws NoSuchFieldException {
        command.execute();
    }

    @Override
    public void setIntegerArg(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgumentException {
        if (!arg.equals("")){
            try {
                command.setArg(Integer.parseInt(arg));
            }catch (NumberFormatException e){
                throw new InvalidArgumentTypeException (messageMessenger.getExceptionMess("noInteger"));
            }
        }else {
            throw new NoArgumentException(messageMessenger.getExceptionMess("noArgument"));
        }
    }

    @Override
    public void setStringArg(RequiringArg<String> command) throws NoArgumentException {
        if (!arg.equals("")){
            command.setArg(arg);
        }else {
            throw new NoArgumentException(messageMessenger.getExceptionMess("noArgument"));
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }
}
