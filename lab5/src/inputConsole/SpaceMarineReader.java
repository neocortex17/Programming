package inputConsole;

import exceptions.InvalidArgumentTypeException;
import exceptions.InvalidFieldException;
import logging.Log;
import messageManager.IMessageMessenger;
import spaceMarine.AstartesCategory;
import spaceMarine.MeleeWeapon;
import spaceMarine.SpaceMarine;
import outputConsole.IConsoleOutputManager;
import spaceMarine.Weapon;

import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Implementation of the SpaceMarineReader interface for reading from the console
 */
public class SpaceMarineReader implements  ISpaceMarineReader{
    private Scanner scanner;
    private IMessageMessenger messageMessenger;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for SpaceMarineReader
     * @param scanner object of Scanner
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     */
    public SpaceMarineReader(Scanner scanner, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager){
        this.scanner = scanner;
        this.messageMessenger = messageMessenger;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public SpaceMarine readSpaceMarine() throws NoSuchFieldException {
        SpaceMarine spaceMarine = new SpaceMarine(messageMessenger);
        spaceMarine.setId();
        spaceMarine.setCreationDate();
        readFields(spaceMarine);
        return spaceMarine.getSpaceMarine();
    }

    @Override
    public SpaceMarine readSpaceMarine(Integer id, ZonedDateTime creationDate) throws NoSuchFieldException {
        SpaceMarine spaceMarine = new SpaceMarine(messageMessenger);
        try{
            spaceMarine.setId(id);
            spaceMarine.setCreationDate(creationDate);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
        }
        readFields(spaceMarine);
        return spaceMarine.getSpaceMarine();
    }

    private void readName(SpaceMarine spaceMarine)throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("name") + ": ");
        try {
            spaceMarine.setName(scanner.nextLine().trim());
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readName(spaceMarine);
        }
    }

    private void readCoordinatesX(SpaceMarine spaceMarine)throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("coordinatesX") + ": ");
        try {
            spaceMarine.setCoordinatesX(Float.parseFloat(scanner.nextLine().trim()));
        }catch (NumberFormatException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noFloat") + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readCoordinatesX(spaceMarine);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readCoordinatesX(spaceMarine);
        }
    }

    private void readCoordinatesY(SpaceMarine spaceMarine)throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("coordinatesY") + ": ");
        try {
            spaceMarine.setCoordinatesY(Double.parseDouble(scanner.nextLine().trim()));
        }catch (NumberFormatException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noDouble") + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readCoordinatesY(spaceMarine);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readCoordinatesY(spaceMarine);
        }
    }

    private void readHealth(SpaceMarine spaceMarine)throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("health") + ": ");
        try {
            spaceMarine.setHealth(Integer.parseInt(scanner.nextLine().trim()));
        }catch (NumberFormatException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noInteger") + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readHealth(spaceMarine);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readHealth(spaceMarine);
        }
    }

    private void readCategory(SpaceMarine spaceMarine) throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("category"));
        try {
            spaceMarine.setCategory(AstartesCategory.valueOf(scanner.nextLine().trim().toUpperCase()));
        }catch (IllegalArgumentException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noEnum" + "\n"));
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readCategory(spaceMarine);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readCategory(spaceMarine);
        }
    }

    private void readWeapon(SpaceMarine spaceMarine)throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("weaponType"));
        try {
            spaceMarine.setWeaponType(Weapon.valueOf(scanner.nextLine().trim().toUpperCase()));
        }catch (IllegalArgumentException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noEnum" + "\n"));
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readWeapon(spaceMarine);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readWeapon(spaceMarine);
        }
    }

    private void readMeleeWeapon(SpaceMarine spaceMarine)throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("meleeWeapon"));
        try {
            spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(scanner.nextLine().trim().toUpperCase()));
        }catch (IllegalArgumentException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noEnum" + "\n"));
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readMeleeWeapon(spaceMarine);
        }catch (InvalidFieldException e){
            consoleOutputManager.printError(e.getMessage() + "\n");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readMeleeWeapon(spaceMarine);
        }
    }

    private void readChapterName(SpaceMarine spaceMarine) throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("chapterName") + ": ");
        try {
            spaceMarine.setChapterName(scanner.nextLine().trim());
        }catch (InvalidArgumentTypeException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("invalidArgument"));
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            readChapterName(spaceMarine);
        }
    }

    private void readChapterWorld(SpaceMarine spaceMarine) throws NoSuchFieldException {
        consoleOutputManager.printMess(messageMessenger.getFieldInputMess("chapterWorld") + ": ");
        spaceMarine.setChapterWorld(scanner.nextLine().trim());
    }

    private void readFields(SpaceMarine spaceMarine) throws NoSuchFieldException {
        readName(spaceMarine);
        readCoordinatesX(spaceMarine);
        readCoordinatesY(spaceMarine);
        readHealth(spaceMarine);
        readCategory(spaceMarine);
        readWeapon(spaceMarine);
        readMeleeWeapon(spaceMarine);
        readChapterName(spaceMarine);
        readChapterWorld(spaceMarine);
    }
}
