package managers;

import exceptions.InvalidArgumentTypeException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEvnVarException;
import logging.Log;
import messageManager.IMessageMessenger;
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

    /**
     * Constructor for ReaderFromCSV
     * @param fileName file name
     */
    public ReaderFromCSV(String fileName){
        Log.logger.log(Level.INFO,"FileManager init");
        this.fileName = fileName;
        this.validateField = new SpaceMarineValidateField();
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
        } catch (IOException e) {
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
                }
                if (validateField.validateName(argument[1])) {
                    newSpaceMarine.setName(argument[1].trim());
                }
                if (validateField.validateCoordinateX(Float.parseFloat(argument[2].trim().substring(1)))) {
                    newSpaceMarine.setCoordinatesX(Float.parseFloat(argument[2].trim().substring(1)));
                }
                if (validateField.validateCoordinateY(Double.parseDouble(argument[3].trim().substring(0, argument[3].length() - 1)))) {
                    newSpaceMarine.setCoordinatesY(Double.parseDouble(argument[3].trim().substring(0, argument[3].length() - 1)));
                }
                if (validateField.validateZonedDateTimeFormat(ZonedDateTime.parse(argument[4].trim()))) {
                    newSpaceMarine.setCreationDate(ZonedDateTime.parse(argument[4].trim()));
                }
                if (validateField.validateHealth(Integer.parseInt(argument[5]))) {
                    newSpaceMarine.setHealth(Integer.parseInt(argument[5]));
                }
                if (validateField.validateAstartesCategory(AstartesCategory.valueOf(argument[6].trim().toUpperCase()))) {
                    newSpaceMarine.setCategory(AstartesCategory.valueOf(argument[6].trim().toUpperCase()));
                }
                if (validateField.validateWeapon(Weapon.valueOf(argument[7].trim().toUpperCase()))) {
                    newSpaceMarine.setWeaponType(Weapon.valueOf(argument[7].trim().toUpperCase()));
                }
                if (validateField.validateMeleeWeapon(MeleeWeapon.valueOf(argument[8].trim().toUpperCase()))) {
                    newSpaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(argument[8].trim().toUpperCase()));
                }
                if (validateField.validateChapterName(argument[9].trim().substring(1))) {
                    newSpaceMarine.setChapterName(argument[9].trim().substring(1));
                }else {
                    throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidChapter") + "\n");
                }
                newSpaceMarine.setChapterWorld(argument[10].trim().substring(0, argument[10].length() - 1));
            }
            return newSpaceMarine.getSpaceMarine();
        } catch (DateTimeParseException | IllegalArgumentException e) {
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            throw new InvalidFieldException(messageMessenger.getExceptionMess("wrongFieldType") + "\n");
        }
    }
}
