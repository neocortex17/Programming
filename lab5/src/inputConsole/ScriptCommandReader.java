package inputConsole;

import exceptions.ScriptException;
import logging.Log;
import messageManager.IMessageMessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;

public class ScriptCommandReader implements ICommandReader{
    private BufferedReader reader;
    private IMessageMessenger messageMessenger;

    public ScriptCommandReader (BufferedReader reader, IMessageMessenger messageMessenger){
        this.reader = reader;
        this.messageMessenger = messageMessenger;
    }

    @Override
    public String readCommand() {
        try {
            String input = reader.readLine();
            return input.trim().toLowerCase();
        }catch (IOException e){
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            throw new ScriptException(messageMessenger.getExceptionMess("script"));
        }
    }
}
