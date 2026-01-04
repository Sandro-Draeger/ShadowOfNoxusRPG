package Item;

import Enums.ClassType;
import Enums.ItemType;

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
