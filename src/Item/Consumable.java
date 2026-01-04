package Item;

import Enums.ItemType;

/**
 * Represents a consumable item that can be used by the hero,
 * such as potions that apply temporary effects.
 */
public class Consumable extends Item {

    public Consumable(String name, ItemType type, int effect, int price) {
        super(name, type, effect, price);
    }
}
