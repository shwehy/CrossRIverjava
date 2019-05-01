package Logic;

import java.awt.image.BufferedImage;

public class Planet implements ICrosser {

    public boolean canSail() {
        return false;
    }

    public double getWeight() {
        return 0;
    }

    public int eatRank() {
        return 1;
    }

    public BufferedImage[] getImages() {
        return new BufferedImage[0];
    }

    public ICrosser makeCopy() {
        return null;
    }

    public void setLabelToBeShown(String label) {

    }

    public String getLabelToBeShown() {
        return "planet";
    }
}
