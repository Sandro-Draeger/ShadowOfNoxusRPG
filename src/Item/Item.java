package Item;

import Enums.ItemType;

/**
 * Represents a generic item that can be used or purchased
 * in the game, such as consumables or weapons.
 */
public abstract class Item {
    protected String name;
    protected ItemType type;
    protected int effect;
    protected int price;


    public Item(String name, ItemType type, int effect, int price) {
        this.name = name;
        this.type = type;
        this.effect = effect;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getEffect() {
        return effect;
    }

    public int getPrice() {
        return price;
    }
}
