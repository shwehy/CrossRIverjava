package Logic;

import java.awt.image.BufferedImage;

public class Carnianimal implements ICrosser {
    double weight;
    public Carnianimal (double weight ){
        this.weight = weight;
    }
    public boolean canSail() {
        return false;
    }

    public double getWeight() {
        return weight;
    }

    public int eatRank() {
        return 3;
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
        return "Carnianimal";
    }
}
