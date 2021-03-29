package application;

import command.CommandManager;
import command.ICommandManager;
import exceptions.*;
import inputConsole.ConsoleInputManager;
import inputConsole.IConsoleInputManager;
import logging.Log;
import managers.*;
import messageManager.*;
import outputConsole.ConsoleOutputManager;
import outputConsole.IConsoleOutputManager;

import java.lang.NoSuchFieldException;
import java.util.NoSuchElementException;
import java.util.logging.Level;

/**
 * Application class
 * @author necortex
 */

public class Application implements IApplication{
    private IConsoleOutputManager consoleOutputManager;
    private IConsoleInputManager consoleInputManager;
    private IMessageMessenger messageMessenger;
    private ICommandManager commandManager;
    private boolean exit;

    /**
     * Constructor for Application
     */
    public Application(){
        exit = false;
    }

    @Override
    public void start() throws NoSuchFieldException {
        messageMessenger = new MessageMessenger(new CommandMessage(), new ExceptionMessage(), new CollectionMessage());
        consoleOutputManager = new ConsoleOutputManager();
        consoleInputManager = new ConsoleInputManager(messageMessenger, consoleOutputManager);
        String fileName = System.getenv("LAB5");
        Reader reader = new ReaderFromCSV(fileName);
        Writer writer = new WriterToCSV(fileName);
        IFileManager fileManager = new FileManager(reader, writer, messageMessenger);
        ISpaceMarineManager spaceMarineManager = new SpaceMarineManager(fileManager);
        try {
            spaceMarineManager.addSpaceMarines();
        }catch (NoEvnVarException | InvalidFieldException | NoDataException | BrokenDataException | InvalidArgumentTypeException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
        }
        this.commandManager = new CommandManager(spaceMarineManager, this, messageMessenger, consoleOutputManager, consoleInputManager);
        consoleOutputManager.printMess(messageMessenger.getStartMess() + "\n");
        run();
        consoleOutputManager.printMess(messageMessenger.getFinishMess());
    }

    /**
     * Controlling Commands and Arguments Typed
     */
    private void run() throws NoSuchFieldException {
        while (!exit){
            try {
                String inputString = consoleInputManager.readCommand();
                String[] input = inputString.split("\\s+", 2);
                    if (input.length < 2){
                        commandManager.executeCommand(input[0], "");
                    }else {
                        commandManager.executeCommand(input[0], input[1]);
                    }
            }catch (NoSuchCommandException | ScriptRecursionException e) {
                consoleOutputManager.printError(e.getMessage() + "\n");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", e);

            }catch (NoSuchElementException e){
                consoleOutputManager.printError(messageMessenger.getExceptionMess("noSuchElement") + "\n");
                exit();
                Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            }
        }
    }

    @Override
    public void exit() {
        exit = true;
    }
}
