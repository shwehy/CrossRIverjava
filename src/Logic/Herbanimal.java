package Logic;

import java.awt.image.BufferedImage;

public class Herbanimal implements ICrosser {
    double weight;
    public Herbanimal (double weight ){
        this.weight = weight;
    }

    public boolean canSail() {
        return false;
    }

    public double getWeight() {
        return this.weight;
    }

    public int eatRank() {
        return 2;
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
        return "Herbanimal";
    }
}
