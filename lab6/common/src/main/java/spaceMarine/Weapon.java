package spaceMarine;

/**
 * Weapon enum
 */
public enum Weapon {
    BOLT_PISTOL(1),
    COMBI_FLAMER(2),
    INFERNO_PISTOL(3);

    private final int weaponInInt;

    Weapon(int weaponInInt){
        this.weaponInInt = weaponInInt;
    }

    public int getWeaponInInt(){
        return weaponInInt;
    }

}