package managers;

import spaceMarine.*;

import java.time.ZonedDateTime;

/**
 * Implementation of the ValidateField interface for validate fields
 */
public class SpaceMarineValidateField implements ValidateField{
    @Override
    public boolean validateName(String name) {
        return name != null || name.equals("");
    }

    @Override
    public boolean validateId(Integer id) {
        return id > 0;
    }

    @Override
    public boolean validateCoordinateX(Float x) {
        return true;
    }

    @Override
    public boolean validateCoordinateY(Double y) {
        return true;
    }

    @Override
    public boolean validateHealth(int health) {
        return health > 0;
    }

    @Override
    public boolean validateAstartesCategory(AstartesCategory category) {
        return category != null;
    }

    @Override
    public boolean validateWeapon(Weapon weaponType) {
        return weaponType != null;
    }

    @Override
    public boolean validateMeleeWeapon(MeleeWeapon meleeWeapon) {
        return meleeWeapon != null;
    }

    @Override
    public boolean validateChapterName(String name) {
        return name != null || name.equals("");
    }

    @Override
    public boolean validateZonedDateTimeFormat(ZonedDateTime dateTime) {
        return dateTime != null;
    }
}
