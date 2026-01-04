package Item;

import Enums.ItemType;

public class Consumable extends Item {

    public Consumable(String name, ItemType type, int effect, int price) {
        super(name, type, effect, price);
    }
}
