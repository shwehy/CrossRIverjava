package Logic;

import java.awt.image.BufferedImage;

public class Carnianimal implements ICrosser {
    double weight;

    int rank;
    boolean CanS ;
    String label;
    private Carnianimal instance;
    BufferedImage x[];
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
        instance.rank = rank;
        instance.weight = getWeight();
        instance.label = getLabelToBeShown();
        instance.CanS = false;
        instance.getImages();
        return instance;
    }

    public void setLabelToBeShown(String label) {
this.label =label;
    }

    public String getLabelToBeShown() {
        return "Carnianimal";
    }
}
