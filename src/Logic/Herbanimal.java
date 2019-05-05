package Logic;

import java.awt.image.BufferedImage;

public class Herbanimal implements ICrosser {
    double weight;
    int rank;
    boolean CanS ;
    String label;
    private Herbanimal instance;
    BufferedImage x[];
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
        instance.rank = rank;
        instance.weight = getWeight();
        instance.label = getLabelToBeShown();
        instance.CanS = false;
        instance.getImages();
        return instance;
    }

    public void setLabelToBeShown(String label) {
    this.label = label;
    }

    public String getLabelToBeShown() {
        return "Herbanimal";
    }
}
