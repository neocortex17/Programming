package spaceMarine;

import java.time.ZonedDateTime;

/**
 * Class interface that performs field validation
 */
public interface ValidateField {

    /**
     * Name validation
     */
    boolean validateName(String str);

    /**
     * Id validation
     */
    boolean validateId(Integer id);


    /**
     * Coordinate X validation
     */
    boolean validateCoordinateX (Float x);

    /**
     * Coordinate Y validation
     */
    boolean validateCoordinateY (Double y);

    /**
     * Health validation
     */
    boolean validateHealth (int health);

    /**
     * AstartesCategory validation
     */
    boolean validateAstartesCategory (AstartesCategory category);

    /**
     * Weapon validation
     */
    boolean validateWeapon (Weapon weaponType);

    /**
     * MeleeWeapon validation
     */
    boolean validateMeleeWeapon (MeleeWeapon meleeWeapon);

    /**
     * Chapter name validation
     */
    boolean validateChapterName (String name);

    /**
     * Date validation
     */
    boolean validateZonedDateTimeFormat(ZonedDateTime dateTime);

}
