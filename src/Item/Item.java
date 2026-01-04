package Item;

import Enums.ItemType;

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
