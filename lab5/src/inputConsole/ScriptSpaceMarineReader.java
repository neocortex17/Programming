package inputConsole;

import exceptions.InvalidArgumentTypeException;
import exceptions.InvalidFieldException;
import exceptions.ScriptException;
import logging.Log;
import messageManager.IMessageMessenger;
import spaceMarine.AstartesCategory;
import spaceMarine.MeleeWeapon;
import spaceMarine.SpaceMarine;
import spaceMarine.Weapon;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.logging.Level;

public class ScriptSpaceMarineReader implements ISpaceMarineReader{
    private BufferedReader reader;
    private IMessageMessenger messageMessenger;

    public ScriptSpaceMarineReader(BufferedReader reader, IMessageMessenger messageMessenger){
        this.reader = reader;
        this.messageMessenger = messageMessenger;
    }

    @Override
    public SpaceMarine readSpaceMarine() {
        SpaceMarine spaceMarine = new SpaceMarine(messageMessenger);
        spaceMarine.setId();
        try {
            readFields(spaceMarine);
        }catch (IOException | InvalidFieldException | NumberFormatException | InvalidArgumentTypeException e){
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            throw new ScriptException(messageMessenger.getExceptionMess("script") + "\n");
        }
        return spaceMarine.getSpaceMarine();
    }

    @Override
    public SpaceMarine readSpaceMarine(Integer id, ZonedDateTime creationDate) {
        SpaceMarine spaceMarine = new SpaceMarine(messageMessenger);
        try {
            spaceMarine.setId(id);
            spaceMarine.setCreationDate(creationDate);
            readFields(spaceMarine);
        }catch (IOException | InvalidFieldException | NumberFormatException | InvalidArgumentTypeException e){
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            throw new ScriptException(messageMessenger.getExceptionMess("script") + "\n");
        }
        return spaceMarine.getSpaceMarine();
    }

    private void readFields(SpaceMarine spaceMarine) throws IOException, InvalidFieldException, InvalidArgumentTypeException {
        spaceMarine.setName(reader.readLine().trim());
        spaceMarine.setCoordinatesX(Float.parseFloat(reader.readLine().trim()));
        spaceMarine.setCoordinatesY(Double.parseDouble(reader.readLine().trim()));
        spaceMarine.setHealth(Integer.parseInt(reader.readLine().trim()));
        spaceMarine.setCategory(AstartesCategory.valueOf(reader.readLine().trim().toUpperCase()));
        spaceMarine.setWeaponType(Weapon.valueOf(reader.readLine().trim().toUpperCase()));
        spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(reader.readLine().trim().toUpperCase()));
        spaceMarine.setChapterName(reader.readLine().trim());
        spaceMarine.setChapterWorld(reader.readLine().trim());
    }
}
