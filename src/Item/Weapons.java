package Item;

import Enums.ClassType;
import Enums.ItemType;

/**
 * Represents a weapon item that can increase a hero's attack
 * and may be restricted to a specific hero class.
 */
public class Weapons extends Item{
    public ClassType ableToUse;

    public Weapons(String name, ItemType type, int effect, int price, ClassType ableToUse) {
        super(name, type, effect, price);
        this.ableToUse = ableToUse;
    }

    public ClassType getAbleToUse() {
        return ableToUse;
    }
}
