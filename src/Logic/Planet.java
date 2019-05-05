package Logic;

import java.awt.image.BufferedImage;

public class Planet implements ICrosser {
    double weight;

    int rank;
    boolean CanS ;
    String label;
    private Planet instance;
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
        return "planet";
    }
}
