package fileManager;

import exceptions.InvalidArgumentTypeException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEvnVarException;
import log.Log;
import spaceMarine.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
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
        this.fileName = fileName;
        this.validateField = new SpaceMarineValidateField();
    }

    @Override
    public Collection<SpaceMarine> read () throws NoEvnVarException, InvalidFieldException, InvalidArgumentTypeException, NoDataException {
        if (fileName == null) {
            throw new NoEvnVarException();
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
                spaceMarines.add(createSpaceMarine(tokens));
            }
            br.close();
            fileReader.close();
            return spaceMarines;
        } catch (IOException e){
            Log.getLogger().error("EXCEPTION: ", e);
            throw new NoDataException();
        }
    }

    /**
     * A method that takes field values and returns a collection object
     * @param argument argument
     * @return element of collection
     * @throws InvalidArgumentTypeException an invalid data type is specified for an argument
     * @throws InvalidFieldException if an invalid object field is specified
     */
    private SpaceMarine createSpaceMarine(String[] argument) throws InvalidArgumentTypeException, InvalidFieldException {
        try {

            SpaceMarineBuilder spaceMarineBuilder = new SpaceMarineBuilderImpl();
            if (!validateField.validateId(Integer.valueOf(argument[0].trim()))) {
                throw new InvalidFieldException();
            }
            Integer id = Integer.valueOf(argument[0].trim());
            if (!validateField.validateZonedDateTimeFormat(ZonedDateTime.parse(argument[4].trim()))) {
                throw new InvalidFieldException();
            }
            ZonedDateTime creationDate = ZonedDateTime.parse(argument[4].trim());

                if (validateField.validateName(argument[1].trim())) {
                    spaceMarineBuilder.setName(argument[1].trim());
                }else {
                    throw new InvalidFieldException();
                }
                if (validateField.validateCoordinateX(Float.parseFloat(argument[2].trim().substring(1)))) {
                    spaceMarineBuilder.setCoordinatesX(Float.parseFloat(argument[2].trim().substring(1)));
                }else {
                    throw new InvalidFieldException();
                }
                if (validateField.validateCoordinateY(Double.parseDouble(argument[3].trim().substring(0, argument[3].length() - 1)))) {
                    spaceMarineBuilder.setCoordinatesY(Double.parseDouble(argument[3].trim().substring(0, argument[3].length() - 1)));
                }else {
                    throw new InvalidFieldException();
                }

                if (validateField.validateHealth(Integer.parseInt(argument[5]))) {
                    spaceMarineBuilder.setHealth(Integer.parseInt(argument[5]));
                }else {
                    throw new InvalidFieldException();
                }
                if (validateField.validateAstartesCategory(AstartesCategory.valueOf(argument[6].trim().toUpperCase()))) {
                    spaceMarineBuilder.setAstartesCategory(AstartesCategory.valueOf(argument[6].trim().toUpperCase()));
                }else {
                    throw new InvalidFieldException();
                }
                if (validateField.validateWeapon(Weapon.valueOf(argument[7].trim().toUpperCase()))) {
                    spaceMarineBuilder.setWeaponType(Weapon.valueOf(argument[7].trim().toUpperCase()));
                }else {
                    throw new InvalidFieldException();
                }
                if (validateField.validateMeleeWeapon(MeleeWeapon.valueOf(argument[8].trim().toUpperCase()))) {
                    spaceMarineBuilder.setMeleeWeapon(MeleeWeapon.valueOf(argument[8].trim().toUpperCase()));
                }else {
                    throw new InvalidFieldException();
                }
                if (validateField.validateChapterName(argument[9].trim().substring(1))) {
                    spaceMarineBuilder.setChapterName(argument[9].trim().substring(1));
                }else {
                    throw new InvalidFieldException();
                }
                spaceMarineBuilder.setChapterWorld(argument[10].trim().substring(0, argument[10].length() - 1));
            return new SpaceMarine(id, spaceMarineBuilder.getSpaceMarineGetter(),creationDate);
        } catch (ArrayIndexOutOfBoundsException e){
            Log.getLogger().error( "EXCEPTION: ", e);
            throw new ArrayIndexOutOfBoundsException();
        }
        catch (DateTimeParseException | IllegalArgumentException e) {
            Log.getLogger().error( "EXCEPTION: ", e);
            throw new InvalidFieldException();
        }
    }
}
