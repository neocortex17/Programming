package spaceMarine;

import exceptions.InvalidFieldException;

public interface SpaceMarineBuilder {

    void setName(String name) throws InvalidFieldException;
    void setCoordinatesX(float x) throws InvalidFieldException;
    void setCoordinatesY(double y) throws InvalidFieldException;
    void setHealth(int health) throws InvalidFieldException;
    void setAstartesCategory(AstartesCategory category) throws InvalidFieldException;
    void setWeaponType(Weapon weaponType) throws InvalidFieldException;
    void setMeleeWeapon(MeleeWeapon meleeWeapon) throws InvalidFieldException;
    void setChapterName(String name) throws InvalidFieldException;
    void setChapterWorld(String world);
    SpaceMarinesGetter getSpaceMarineGetter();
}
