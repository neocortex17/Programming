package input;

import exceptions.InvalidFieldException;
import inputManager.SpaceMarineReader;
import messages.Message;
import output.OutputManager;
import spaceMarine.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleSpaceMarineReader implements SpaceMarineReader {
    private BufferedReader reader;;
    private Message message;
    private OutputManager outputManager;

    public ConsoleSpaceMarineReader(BufferedReader reader, Message message, OutputManager outputManager) {
        this.reader = reader;
        this.message = message;
        this.outputManager = outputManager;
    }

    @Override
    public SpaceMarinesGetter readSpaceMarine() throws IOException {
        SpaceMarineBuilder spaceMarineBuilder = new SpaceMarineBuilderImpl();
        readFields(spaceMarineBuilder);
        System.out.println("/");
        return spaceMarineBuilder.getSpaceMarineGetter();
    }

    private void readName(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputName") + ": ");
        try{
            spaceMarineBuilder.setName(reader.readLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidName") + "\n");
            readName(spaceMarineBuilder);
        }
    }

    private void readCoordinatesX(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputCoordinatesX") + ": ");
        try{
            spaceMarineBuilder.setCoordinatesX(Float.parseFloat(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(message.getMessage("noFloat") + "\n");
            readCoordinatesX(spaceMarineBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidCoordinatesX") + "\n");
            readCoordinatesX(spaceMarineBuilder);
        }
    }

    private void readCoordinatesY(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputCoordinatesY") + ": ");
        try{
            spaceMarineBuilder.setCoordinatesY(Double.parseDouble(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(message.getMessage("noDouble") + "\n");
            readCoordinatesY(spaceMarineBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidCoordinatesY") + "\n");
            readCoordinatesY(spaceMarineBuilder);
        }
    }

    private void readHealth(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputHealth") + ": ");
        try{
            spaceMarineBuilder.setHealth(Integer.parseInt(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(message.getMessage("noInteger") + "\n");
            readHealth(spaceMarineBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidHealth") + "\n");
            readHealth(spaceMarineBuilder);
        }
    }

    private void readAstatresCategory(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputCategory") + ": ");
        try{
            spaceMarineBuilder.setAstartesCategory(AstartesCategory.valueOf(reader.readLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(message.getMessage("noEnum") + "\n");
            readAstatresCategory(spaceMarineBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidCategory") + "\n");
            readAstatresCategory(spaceMarineBuilder);
        }
    }

    private void readWeapon(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputWeapon") + ": ");
        try{
            spaceMarineBuilder.setWeaponType(Weapon.valueOf(reader.readLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(message.getMessage("noEnum") + "\n");
            readWeapon(spaceMarineBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidWeapon") + "\n");
            readWeapon(spaceMarineBuilder);
        }
    }

    private void readMeleeWeapon(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputMeleeWeapon") + ": ");
        try{
            spaceMarineBuilder.setMeleeWeapon(MeleeWeapon.valueOf(reader.readLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(message.getMessage("noEnum") + "\n");
            readMeleeWeapon(spaceMarineBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidMeleeWeapon") + "\n");
            readMeleeWeapon(spaceMarineBuilder);
        }
    }

    private void readChapterName(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputChapterName") + ": ");
        try{
            spaceMarineBuilder.setChapterName(reader.readLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(message.getMessage("invalidName") + "\n");
            readChapterName(spaceMarineBuilder);
        }
    }

    private void readChapterWorld(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        outputManager.printMsg(message.getMessage("inputChapterWorld") + ": ");
            spaceMarineBuilder.setChapterWorld(reader.readLine().trim());
    }

    private void readFields(SpaceMarineBuilder spaceMarineBuilder) throws IOException {
        readName(spaceMarineBuilder);
        readCoordinatesX(spaceMarineBuilder);
        readCoordinatesY(spaceMarineBuilder);
        readHealth(spaceMarineBuilder);
        readAstatresCategory(spaceMarineBuilder);
        readWeapon(spaceMarineBuilder);
        readMeleeWeapon(spaceMarineBuilder);
        readChapterName(spaceMarineBuilder);
        readChapterWorld(spaceMarineBuilder);
    }
}
