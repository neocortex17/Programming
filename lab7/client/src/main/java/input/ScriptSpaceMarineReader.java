package input;

import exceptions.InvalidFieldException;
import exceptions.ScriptException;
import inputManager.SpaceMarineReader;
import spaceMarine.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptSpaceMarineReader implements SpaceMarineReader {
    private BufferedReader bufferedReader;

    public ScriptSpaceMarineReader (BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    @Override
    public SpaceMarinesGetter readSpaceMarine() throws IOException {
        SpaceMarineBuilder spaceMarineBuilder = new SpaceMarineBuilderImpl();
        try {
            readFields(spaceMarineBuilder);
        }catch (IOException | InvalidFieldException | NumberFormatException e){
            throw new ScriptException();
        }
        return spaceMarineBuilder.getSpaceMarineGetter();
    }

    private void readFields(SpaceMarineBuilder spaceMarineBuilder) throws IOException, InvalidFieldException{
        spaceMarineBuilder.setName(bufferedReader.readLine().trim());
        spaceMarineBuilder.setCoordinatesX(Float.parseFloat(bufferedReader.readLine().trim()));
        spaceMarineBuilder.setCoordinatesY(Double.parseDouble(bufferedReader.readLine().trim()));
        spaceMarineBuilder.setHealth(Integer.parseInt(bufferedReader.readLine().trim()));
        spaceMarineBuilder.setAstartesCategory(AstartesCategory.valueOf(bufferedReader.readLine().trim().toUpperCase()));
        spaceMarineBuilder.setWeaponType(Weapon.valueOf(bufferedReader.readLine().trim().toUpperCase()));
        spaceMarineBuilder.setMeleeWeapon(MeleeWeapon.valueOf(bufferedReader.readLine().trim().toUpperCase()));
        spaceMarineBuilder.setChapterName(bufferedReader.readLine().trim());
        spaceMarineBuilder.setChapterWorld(bufferedReader.readLine().trim());
    }
}
