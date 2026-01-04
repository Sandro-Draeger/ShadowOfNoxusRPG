package Character;

import Enums.ClassType;

public class BloodyMage extends Hero{

    public BloodyMage(){
        super("Bloody Mage", 100, 100,15, ClassType.BLOODY_MAGE,
                "Blood Tide", 70, 0);
    }

    @Override
    public void heroDetails() {
        super.heroDetails();
    }
}
