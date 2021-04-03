package managers;

import application.IApplication;
import exceptions.InvalidArgumentTypeException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEvnVarException;
import logging.Log;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;
import spaceMarine.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import java.io.FileReader;
import java.util.logging.Level;

/**
 * Implementation of the Reader interface for working with CSV files
 */
public class ReaderFromCSV implements Reader {
    private final String fileName;
    private ValidateField validateField;
    private IApplication application;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for ReaderFromCSV
     * @param fileName file name
     */
    public ReaderFromCSV(String fileName, IApplication application, IConsoleOutputManager consoleOutputManager){
        this.application = application;
        Log.logger.log(Level.INFO,"FileManager init");
        this.fileName = fileName;
        this.validateField = new SpaceMarineValidateField();
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public Collection<SpaceMarine> read (IMessageMessenger messageMessenger) throws NoEvnVarException, InvalidFieldException, InvalidArgumentTypeException, NoDataException {
        if (fileName == null) {
            throw new NoEvnVarException(messageMessenger.getExceptionMess("noEvnVar") + "\n");
        }
        try {
            ArrayList<SpaceMarine> spaceMarines = new ArrayList<>();
            File file = new File(fileName);
            String line = "";
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                spaceMarines.add(createSpaceMarine(messageMessenger,tokens));
            }
            br.close();
            fileReader.close();
            return spaceMarines;
        }catch (FileNotFoundException e){
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            messageMessenger.getExceptionMess("The file not found, creating a new one." + "\n");
            application.exit();
            return null;
        }
        catch (IOException e) {
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            throw new NoDataException(e.getMessage() + "\n");
        }
    }

    /**
     * A method that takes field values and returns a collection object
     * @param messageMessenger message messenger
     * @param argument argument
     * @return element of collection
     * @throws InvalidArgumentTypeException an invalid data type is specified for an argument
     * @throws InvalidFieldException if an invalid object field is specified
     */
    private SpaceMarine createSpaceMarine(IMessageMessenger messageMessenger, String[] argument) throws InvalidArgumentTypeException, InvalidFieldException {
        SpaceMarine newSpaceMarine = new SpaceMarine(messageMessenger);
        try {
            for (int i = 0; i < argument.length; i++) {
                if (validateField.validateId(Integer.valueOf(argument[0].trim()))) {
                    newSpaceMarine.setId(Integer.valueOf(argument[0].trim()));
                } else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidId") + "\n");
                }
                if (validateField.validateName(argument[1].trim())) {
                    newSpaceMarine.setName(argument[1].trim());
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidName") + "\n");
                }
                if (validateField.validateCoordinateX(Float.parseFloat(argument[2].trim().substring(1)))) {
                    newSpaceMarine.setCoordinatesX(Float.parseFloat(argument[2].trim().substring(1)));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidCoordinatesX") + "\n");
                }
                if (validateField.validateCoordinateY(Double.parseDouble(argument[3].trim().substring(0, argument[3].length() - 1)))) {
                    newSpaceMarine.setCoordinatesY(Double.parseDouble(argument[3].trim().substring(0, argument[3].length() - 1)));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidCoordinatesY") + "\n");
                }
                if (validateField.validateZonedDateTimeFormat(ZonedDateTime.parse(argument[4].trim()))) {
                    newSpaceMarine.setCreationDate(ZonedDateTime.parse(argument[4].trim()));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidCreationDate") + "\n");
                }
                if (validateField.validateHealth(Integer.parseInt(argument[5]))) {
                    newSpaceMarine.setHealth(Integer.parseInt(argument[5]));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidHealth") + "\n");
                }
                if (validateField.validateAstartesCategory(AstartesCategory.valueOf(argument[6].trim().toUpperCase()))) {
                    newSpaceMarine.setCategory(AstartesCategory.valueOf(argument[6].trim().toUpperCase()));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidAstartesCategory") + "\n");
                }
                if (validateField.validateWeapon(Weapon.valueOf(argument[7].trim().toUpperCase()))) {
                    newSpaceMarine.setWeaponType(Weapon.valueOf(argument[7].trim().toUpperCase()));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidWeapon") + "\n");
                }
                if (validateField.validateMeleeWeapon(MeleeWeapon.valueOf(argument[8].trim().toUpperCase()))) {
                    newSpaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(argument[8].trim().toUpperCase()));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidMeleeWeapon") + "\n");
                }
                if (validateField.validateChapterName(argument[9].trim().substring(1))) {
                    newSpaceMarine.setChapterName(argument[9].trim().substring(1));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidChapterName") + "\n");
                }
                newSpaceMarine.setChapterWorld(argument[10].trim().substring(0, argument[10].length() - 1));
            }
            return newSpaceMarine.getSpaceMarine();
        } catch (ArrayIndexOutOfBoundsException e){
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            consoleOutputManager.printError(messageMessenger.getExceptionMess("wrongFile") + "\n");
            application.exit();
            return null;
        }
        catch (DateTimeParseException | IllegalArgumentException e) {
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            throw new InvalidFieldException(messageMessenger.getExceptionMess("wrongFieldType") + "\n");
        }
    }
}
